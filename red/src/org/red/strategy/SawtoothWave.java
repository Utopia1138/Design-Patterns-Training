package org.red.strategy;

/**
 * Sawtooth wave generator using additive synthesis.
 */
public class SawtoothWave implements WaveformAlgorithm {
	private int harmonics;

	/**
	 * Builds a sawtooth generator.
	 *
	 * @param harmonics the number of harmonics to generate - the higher this
	 * 	count is, the more accurate the generator will be, at the cost of being
	 * 	slower to run.
	 */
	public SawtoothWave( int harmonics ) {
		this.harmonics = harmonics;
	}

	@Override
	public void fill(float[] wavetable) {
		for ( int i = 0; i < wavetable.length; wavetable[i++] = 0.f );
		
		double step = (2.0 * Math.PI) / (double) wavetable.length;
		double amp = 1.0;

		for ( int i = 1; i <= harmonics; ++i ) {
			double harmonic = amp / i;
			for ( int j = 0; j < wavetable.length; ++j )
				wavetable[j] += harmonic * Math.sin(step * i * j);
		}

		normalize(wavetable);
	}

}
