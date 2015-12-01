package org.red.strategy;

import javax.sound.sampled.AudioFormat;

import org.red.utils.au.Source;

/**
 * A single channel wavetable oscillator that linearly
 * interpolates samples. If we want to double up on the
 * strategy pattern, we could extract the interpolation
 * algorithm to a new type family and allow those to be
 * freely interchangeable, but I don't have all day to
 * come up with this.
 */
public class Oscillator implements Source {
	private WaveformAlgorithm generator = new SineWave();
	private float[] wavetable;
	private float freq = 440.f;
	private float idx = 0.f;

	public Oscillator() {
		setSampleResolution(512);
	}

	/**
	 * Cycle rate in hertz.
	 *
	 * @param freq hz
	 * @return self
	 */
	public Oscillator setFrequency( float freq ) {
		this.freq = freq;
		return this;
	}

	/**
	 * Set the frequency from a midi note (0 - 127).
	 *
	 * @param midi note in the midi range
	 * @return self
	 */
	public Oscillator setMidiNote( int midi ) {
		this.freq = (float) ( 8.175798 * Math.pow(1.0594633, midi) );
		return this;
	}

	/**
	 * Set the algorithm used to populate the wavetable.
	 *
	 * @param generator wave generation algorithm
	 * @return self
	 */
	public Oscillator setWaveform( WaveformAlgorithm generator ) {
		this.generator = generator;
		generator.fill(wavetable);
		return this;
	}

	/**
	 * Set the resolution of the wavetable, this is essentially just the width
	 * of the wavetable - higher will result in a more accurate representation
	 * of the waveform.
	 *
	 * @param samples width of the wavetable in samples
	 * @return self
	 */
	public Oscillator setSampleResolution( int samples ) {
		wavetable = new float[samples];
		generator.fill(wavetable);
		return this;
	}

	/**
	 * Repeatedly scans the wavetable at the frequency of the oscillator
	 * to fill a supplied buffer.
	 */
	@Override
	public int tick(AudioFormat fmt, float[] buffer) {
		double incr = wavetable.length / (double) fmt.getSampleRate();
		incr *= this.freq;

		for(int i = 0; i < buffer.length; ++i) {
			while( idx >= wavetable.length ) idx -= wavetable.length;

			buffer[i] = lerp();
			idx += incr;
		}

		return buffer.length;
	}

	/*
	 * Linearly interpolate samples in the wavetable for smoother
	 * playback. What's that you say? I should be using Cubic interpolation?
	 * I'm not made of CPU cycles you know.
	 */
	private float lerp() {
		int left = (int)idx;
		int right = left + 1 < wavetable.length ? left + 1 : 0;

		float sample = wavetable[left];
		double slope = wavetable[right] - sample;
		return sample += ((idx - (double) left) * slope);
	}
}
