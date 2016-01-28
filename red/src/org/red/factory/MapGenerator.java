package org.red.factory;

import java.util.Random;

/**
 * TODO: turn the map generation algorithm into a factory method, make it actually work.
 */
public class MapGenerator {
	private final Random rand = new Random();
	private MapFactory mapFactory;

	public MapGenerator( MapFactory mapFactory ) {
		this.mapFactory = mapFactory;
	}

	public MapGenerator setMapFactory( MapFactory mapFactory ) {
		this.mapFactory = mapFactory;
		return this;
	}

	public class Noise {
		double[] noise;
		int w, h;

		public Noise(int w, int h, int iters) {
			noise = new double[w * h];
			this.w = w;
			this.h = h;

			gen(iters);
		}

		public void gen(int iters) {
			for(int x = 0; x < h; x += iters) {
				for( int y = 0; y < w; y += iters )
					set(y, x, MapGenerator.this.rand.nextFloat() * 2 - 1);
			}

			int step = iters;
			double scale = 1.0 / w;
			double mod = 1.0;

			do {
				int half = step / 2;

				for(int x = 0; x < h; x += step) {
					for( int y = 0; y < w; y += step ) {
						double a = get(y, x);
						double b = get(y + step, x);
						double c = get(y, x + step);
						double d = get(y + step, x + step);
						double val = (a + b + c + d) / 4.0 + (MapGenerator.this.rand.nextFloat() * 2 - 1) * step * scale;
						
						set(y + step, x + step, val);
					}
				}

				for(int x = 0; x < h; x += step) {
					for( int y = 0; y < w; y += step ) {
						double a = get(y, x);
						double b = get(y + step, x);
						double c = get(y, x + step);
						
						double d = get(y + half, x + half);
						double e = get(y + half, x - half);
						double f = get(y - half, x + half);
						

						double g = (a + b + d + e) / 4.0 + (MapGenerator.this.rand.nextFloat() * 2 - 1) * step * scale * 0.5;
						double h = (a + c + d + f) / 4.0 + (MapGenerator.this.rand.nextFloat() * 2 - 1) * step * scale * 0.5;
						
						set(y + half, x, g);
						set(y, x + half, h);
					}
				}
				
				step /= 2;
				scale *= (mod + 0.8);
				mod *= 0.3;
			} while(step > 1);
		}

		private void set(int x, int y, double v) {
			noise[(x & (w - 1)) + (y & (h - 1)) * w] = v;
		}
		private double get(int x, int y) {
			return noise[(x & (w - 1)) + (y & (h - 1)) * w];
		}
	}

	public Map getMap(int width, int height) {
		Map map = mapFactory.buildMap(width, height);
		byte[][] grid = map.getMap();

		double[] noiseA = new Noise(grid[0].length, grid.length, 32).noise;
		double[] noiseB = new Noise(grid[0].length, grid.length, 32).noise;

		for( int h = 0; h < grid.length; ++h ) {
			byte[] row = grid[h];

			for( int w = 0; w < row.length; ++w ) {
				int i = w + h * row.length;
				double sample = rand.nextDouble() * 3. - 2; //Math.abs(noiseA[i] - noiseB[i]) * 3. - 2.;
				
				double dx = w / (row.length - 1.) * 2. - 1.;
				double dy = h / (grid.length - 1.) * 2. - 1.;
				
				if ( dx < 0.f ) dx = -dx;
				if ( dy < 0.f ) dy = -dy;

				double d = dx >= dy ? dx : dy;
				d = d * d * d * d;
				d = d * d * d * d;
				
				sample = sample + 1 - d * 20;

				int types = map.getTileCount();
				double incr = 8.0 / types;
				double s = -0.5;

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
