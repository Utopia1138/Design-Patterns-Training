package org.red.factory;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Supplier;

public class MapCache {
	private final Random rand = new Random();
	private ConcurrentLinkedDeque<Map> maps = new ConcurrentLinkedDeque<Map>();
	private Supplier<MapFactory> factorySelector;
	private MapGenerator generator;
	private int w, h;

	public MapCache(MapFactory[] factories, int w, int h, NoiseFactory noise) {
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
		new Thread(() -> {
			MapCache.this.pushMap();
		}).start();
	}
}
