package org.red.strategy;

/**
 * Nice simple sine wave generator.
 */
public class SineWave implements WaveformAlgorithm {
	@Override
	public void fill(float[] wavetable) {
		double step = (2.0 * Math.PI) / wavetable.length;
		for ( int i = 0; i < wavetable.length; ++i )
			wavetable[i] = (float) Math.sin( step * i );

		normalize(wavetable);
	}
}
