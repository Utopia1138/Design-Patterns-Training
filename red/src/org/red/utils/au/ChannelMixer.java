package org.red.utils.au;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

/**
 * Mapping from the reasonably complex javax.sound audio output data line
 * to a simpler mixer that audio sources can simply register with and write
 * 32bit floats to.
 *
 * This will hopefully extract all the complexity so that the design pattern
 * examples themselves can be much simpler.
 * 
 * Double buffered multi-channel mixer.
 */
public class ChannelMixer {
	private List<Channel> channels = new ArrayList<Channel>();
	private float[] buffer;
	private ByteBuffer encoded;
	private final SourceDataLine out;
	private float amp;

	/**
	 * A single mixer channel with audio source 'plugged' in.
	 */
	private class Channel {
		float[] buffer = new float[ChannelMixer.this.buffer.length];
		final Source src;

		Channel(Source src) {
			this.src = src;
		}

		int tick() {
			return src.tick(out.getFormat(), buffer);
		}
	}

	/**
	 * Map the javax.sound mixer to a ChannelMixer and ready for playback.
	 *
	 * @param amp global amplitude setting
	 * @param bufferSize internal frame size for audio buffers
	 * @param mix javax.sound mixer representing a physical (or virtual) audio device
	 * @throws LineUnavailableException if no audio lines can be initialised
	 * 	with the audio device
	 */
	public ChannelMixer( float amp, int bufferSize, Mixer mix ) throws LineUnavailableException {
		this.amp = amp;
		mix.open();

		// 44.1k, 16 bit, mono. Sizeable javax.sound internal buffer, as it blocks
		// and it sounded pretty nasty when it was stuttering.
		out = AudioSystem.getSourceDataLine(new AudioFormat(
			Encoding.PCM_SIGNED, 44_100, 16, 1, 2,
			192_000, true ), mix.getMixerInfo());

		encoded = ByteBuffer.allocate(2 * bufferSize);
		buffer = new float[bufferSize];
		out.open();
		out.start();
	}

	/**
	 * Register an audio source with this mixer - this will result in
	 * a new channel being built and the audio source plugged in.
	 *
	 * @param src audio source
	 * @return self
	 */
	public ChannelMixer register( Source src ) {
		this.channels.add( new Channel(src) );
		return this;
	}

	/**
	 * The number of ticks needed to produce the requested duration of playback.
	 *
	 * @param ms duration of playback
	 * @return the number of ticks (this can't be exact, but will improve with smaller
	 * 	buffers).
	 */
	public long ticksPerDuration( long ms ) {
		double fps = out.getFormat().getSampleRate() / buffer.length;
		double fpms = fps / 1000.;
		return (long) (fpms * ms);
	}

	/**
	 * Mix a frame of audio from all sources to the output device.
	 */
	public void tick() {
		encoded.rewind();

		for ( int i = 0; i < buffer.length; buffer[i++] = 0.f );

		for ( Channel chan : channels ) {
			// chan.tick() should probably be done in parallel
			int floats = chan.tick();

			for ( int i = 0; i < floats; ++i ) {
				buffer[i] += chan.buffer[i] * amp;
			}
		}

		for ( float sample : buffer ) {
			encoded.putShort((short)(sample * 32_767));
		}

		out.write(encoded.array(), 0, buffer.length * 2);
	}

	/**
	 * Close the data line to the output device.
	 */
	public void close() {
		out.drain();
		out.close();
	}
}
