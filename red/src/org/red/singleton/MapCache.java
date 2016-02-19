package org.red.singleton;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Supplier;

import org.red.factory.AlienLandscapeMapFactory;
import org.red.factory.Map;
import org.red.factory.MapFactory;
import org.red.factory.MapGenerator;
import org.red.factory.NoiseFactory;
import org.red.factory.OverworldMapFactory;
import org.red.factory.PerlinNoiseFactory;
import org.red.factory.VolcanoMapFactory;

/**
 * Singleton version of the MapCache used in the previous chapter.
 * Luckily, much of this was already thread safe.
 */
public class MapCache {

	private volatile static MapCache cache;

	public static MapCache getInstance() {
		if ( cache == null ) {
			// Singletons with parameters are problematic - the issue is skirted over here,
			// but it does ruin the loose coupling we were aiming for in the non-singleton version
			// of this type.
			synchronized ( MapCache.class ) {
				final int w = 1200, h = 900;
				final int div = 1;
				if ( cache == null ) {
					cache = new MapCache(new MapFactory[] {
							new OverworldMapFactory(w / div, h / div),
							new VolcanoMapFactory(w / div, h / div),
							new AlienLandscapeMapFactory(w / div, h / div) }, w, h,
						new PerlinNoiseFactory().setWeighting(0.73) );
				}
			}
		}

		return cache;
	}

	private final Random rand = new Random();
	private ConcurrentLinkedDeque<Map> maps = new ConcurrentLinkedDeque<Map>();
	private Supplier<MapFactory> factorySelector;
	private MapGenerator generator;
	private int w, h;

	private MapCache(MapFactory[] factories, int w, int h, NoiseFactory noise) {
		this.w = w;
		this.h = h;
		factorySelector = () -> {
			float val = rand.nextFloat();
			int idx = val < 0.4 ? 0 : val < 0.8 ? 1 : 2;
			return factories[idx];
		};

		generator = new MapGenerator( factorySelector.get(), noise );
		pushMap();
	}

	public Map get() {
		if ( maps.isEmpty() ) {
			asyncMapBuild();
			return null;
		}
		
		Map map = maps.pop();
		asyncMapBuild();
		return map;
	}

	public void setNoiseFactory(NoiseFactory noise) {
		synchronized(generator) {
			generator.setNoiseFactory(noise);
		}
	}

	private void pushMap() {
		synchronized(generator) {
			generator.setMapFactory(factorySelector.get());
			maps.push(generator.getMap(w,  h));
		}
	}

	private void asyncMapBuild() {
		new Thread( this::pushMap ).start();
	}
}
