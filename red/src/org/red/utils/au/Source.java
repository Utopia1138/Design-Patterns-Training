package org.red.utils.au;

import javax.sound.sampled.AudioFormat;

/**
 * An audio source.
 */
public interface Source {

	/**
	 * Populate a frame's worth of audio into the output buffer based on
	 * the format of the underlying stream to the device.
	 *
	 * @param fmt format of the underlying audio channel to the device.
	 * @param buffer populate with audio goodness
	 * @return the number of samples populated.
	 */
	int tick( AudioFormat fmt, float[] buffer );
}
