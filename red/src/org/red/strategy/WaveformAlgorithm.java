package org.red.strategy;

import java.util.stream.IntStream;

/**
 * Algorithms for generating waveforms for graphing or synthesis.
 */
public interface WaveformAlgorithm {
	/**
	 * Populate the waveform into the supplied wavetable using this
	 * algorithm. The larger the wavetable, the higher the sample rate
	 * of waveform generation and the more accurate the resultant waveform.
	 *
	 * @param wavetable buffer to populate with a single cycle of the waveform
	 */
	void fill( float[] wavetable );

	/**
	 * Normalize a table to the +/- 1.f range.
	 *
	 * @param wavetable table to normalize.
	 */
	default void normalize( float[] wavetable ) {
		// Just lookin' for an excuse to use Java 8 Streams again.
		double max = IntStream.range(0, wavetable.length)
			.mapToDouble(i -> wavetable[i])
			.map(d -> d < 0.0 ? -d : d)
			.max().getAsDouble();

		final double normal = max != 0.0 ? 1.0 / max : 0.0;
		IntStream.range(0, wavetable.length).forEach(i -> wavetable[i] *= normal);
    }
}
