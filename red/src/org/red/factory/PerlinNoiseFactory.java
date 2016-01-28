package org.red.factory;

import java.util.Random;

public class PerlinNoiseFactory implements NoiseFactory {
	private Random rand = new Random();
	private int noiseOctaves = 8;
	private double weighting = 0.5;

	public PerlinNoiseFactory setOctaves(int oct) {
		this.noiseOctaves = oct;
		return this;
	}

	public PerlinNoiseFactory setWeighting(double weighting) {
		this.weighting = weighting;
		return this;
	}

	@Override
	public double[][] generateNoiseMap(int w, int h) {
		return perlinNoise(noiseOctaves, w, h);
	}

	private double[][] whiteNoise(int w, int h) {
		double[][] noise = new double[h][w];

		for( int i = 0; i < noise.length; ++i )
			for ( int j = 0; j < noise[i].length; ++j )
				noise[i][j] = rand.nextDouble();

		return noise;
	}

	private double[][] sampledNoise(double[][] base, int octave) {
		double[][] noise = new double[base.length][base[0].length];
		
		int period = 1 << octave;
		double freq = 1.0 / period;

		for ( int i = 0; i < noise.length; ++i ) {

			// Vertical sampling indices
			int lih = (i / period) * period;
			int rih = (lih + period) % noise.length;
			double hBlend = (i - lih) * freq;

			for ( int j = 0; j < noise[i].length; ++j ) {

				// Horizontal sampling indices
				int liw = (j / period) * period;
				int riw = (liw + period) % noise[i].length;
				double wBlend = (j - liw) * freq;
				
				// Blend noise
				double top = interpolate( base[lih][liw], base[lih][riw], wBlend );
				double bottom = interpolate( base[rih][liw], base[rih][riw], wBlend );

				noise[i][j] = interpolate(top, bottom, hBlend);
			}
		}

		return noise;
	}

	private double[][] perlinNoise(int octaves, int w, int h) {
		double[][] base = whiteNoise(w, h);
		
		// Build up our octaves
		double[][][] smoothed = new double[octaves][][];
		for ( int i = 0; i < octaves; ++i )
			smoothed[i] = sampledNoise(base, i);
		
		double[][] perlin = new double[h][w];

		double amp = 1.0;
		double total = 0.0;

		// Blend them with weighting
		for ( int oct = octaves - 1; oct >= 0; --oct ) {
			amp *= weighting;
			total += amp;

			for ( int i = 0; i < h; ++i )
				for ( int j = 0; j < w; ++j )
					perlin[i][j] += smoothed[oct][i][j] * amp;
		}

		// Finally normalise
		for ( int i = 0; i < h; ++i )
			for ( int j = 0; j < w; ++j )
				perlin[i][j] /= total;

		return perlin;
	}

	private static double interpolate(double x, double y, double blend) {
		return x * (1 - blend) + blend * y;
	}

}
