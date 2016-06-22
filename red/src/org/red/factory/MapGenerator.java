package org.red.factory;

public class MapGenerator {
	private MapFactory mapFactory;
	private NoiseFactory noiseGenerator;
	private double[][] noise;

	public MapGenerator( MapFactory mapFactory, NoiseFactory noiseGen ) {
		this.mapFactory = mapFactory;
		this.noiseGenerator = noiseGen;
		regenNoise();
	}

	public MapGenerator setMapFactory( MapFactory mapFactory ) {
		this.mapFactory = mapFactory;
		regenNoise();
		return this;
	}

	public MapGenerator setNoiseFactory(NoiseFactory noiseGen) {
		this.noiseGenerator = noiseGen;
		regenNoise();
		return this;
	}

	private void regenNoise() {
		noise = noiseGenerator.generateNoiseMap(mapFactory.getTilesWide(), mapFactory.getTilesHigh());
	}

	public Map getMap(int width, int height) {
		Map map = mapFactory.buildMap(width, height);

		byte[][] grid = map.getMap();

		for( int h = 0; h < grid.length; ++h ) {
			for( int w = 0; w < grid[h].length; ++w ) {
				double sample = noise[h][w];

				int types = map.getTileCount();
				double incr = 1.0 / types;
				double s = 0.0 + incr;

				for(byte j = 0; j < types; ++j, s += incr) {
					if ( sample < s || j == types - 1 ) {
						grid[h][w] = j;
						break;
					}
				}
			}
		}

		return map;
	}
}
