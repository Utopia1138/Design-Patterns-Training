package org.red.strategy;

/**
 * Triangle wave generator using additive synthesis.
 */
public class TriangleWave implements WaveformAlgorithm {
	private int harmonics;

	/**
	 * Builds a triangle generator.
	 *
	 * @param harmonics the number of harmonics to generate - the higher this
	 * 	count is, the more accurate the generator will be, at the cost of being
	 * 	slower to run.
	 */
	public TriangleWave( int harmonics ) {
		this.harmonics = harmonics;
	}

	@Override
	public void fill(float[] wavetable) {
		for ( int i = 0; i < wavetable.length; wavetable[i++] = 0.f );

		double step = (2.0 * Math.PI) / (double) wavetable.length;
		double amp = 1.0;

		for ( int i = 1; i <= harmonics * 2; i += 2 ) {
			double harmonic = amp / (i * i);
			for ( int j = 0; j < wavetable.length; ++j )
				wavetable[j] += harmonic * Math.cos(step * i * j);
		}

		normalize(wavetable);
	}

}
