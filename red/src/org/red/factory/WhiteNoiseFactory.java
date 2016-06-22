package org.red.factory;

import java.util.Random;

public class WhiteNoiseFactory implements NoiseFactory {
	private Random rand = new Random();
	
	@Override
	public double[][] generateNoiseMap(int width, int height) {
		double[][] noise = new double[height][width];

		for( int i = 0; i < noise.length; ++i )
			for ( int j = 0; j < noise[i].length; ++j )
				noise[i][j] = rand.nextDouble();

		return noise;
	}

}
