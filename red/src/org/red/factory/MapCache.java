package org.red.factory;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Supplier;

public class MapCache {
	private ConcurrentLinkedDeque<Map> maps = new ConcurrentLinkedDeque<Map>();
	private Supplier<MapFactory> factorySelector;
	private MapGenerator generator;
	private int w, h;

	/**
	 * Creates a cache that randomly cycles between different MapFactory instances.
	 *
	 * @param factories map factories to select from
	 * @param w screen width
	 * @param h screen height
	 * @param noise generator for noise
	 */
	public MapCache(MapFactory[] factories, int w, int h, NoiseFactory noise) {
		this( w, h, noise, () -> {
			float val = new Random().nextFloat();
			int idx = val < 0.4 ? 0 : val < 0.8 ? 1 : 2;
			return factories[idx];
		} );
	}

	public MapCache(int w, int h, NoiseFactory noise, Supplier<MapFactory> mapSource) {
		factorySelector = mapSource;
		this.w = w;
		this.h = h;
		generator = new MapGenerator( factorySelector.get(), noise );
		pushMap();
	}

	public MapCache clearCache() {
		synchronized(generator) {
			while(!maps.isEmpty())
				maps.pop();
		}

		return this;
	}

	public MapCache setMapSource(Supplier<MapFactory> mapSource) {
		this.factorySelector = mapSource;
		clearCache();
		asyncMapBuild();
		return this;
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
		new Thread(this::pushMap).start();
	}
}
