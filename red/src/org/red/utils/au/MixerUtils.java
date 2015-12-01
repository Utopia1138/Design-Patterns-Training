package org.red.utils.au;

import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

/**
 * Utilities for mapping the javax.sound concept of audio devices
 * onto simpler types so that each example isn't needlessly complicated
 * by this cruft. Safe to ignore.
 */
public class MixerUtils {

	/**
	 * Play the supplied channel for the supplied number of milliseconds.
	 *
	 * @param mixer channel
	 * @param ms duration
	 */
	public static void play( ChannelMixer mixer, long ms ) {
		long frames = mixer.ticksPerDuration( ms );

		for ( long frame = 0; frame < frames; ++frame ) {
			mixer.tick();
		}
	}

	/**
	 * CLI hardware mixer selection.
	 * @return the mixer based on the user choice
	 */
	public static Mixer selectMixer() {
		Mixer.Info[] mixers = AudioSystem.getMixerInfo();
		for ( int i = 0; i < mixers.length; ++i ) {
			System.out.printf( "[%d]: %s%n", i, mixers[i] );
		}

		try ( Scanner scan = new Scanner(System.in) ) {
			int idx = Integer.parseInt(scan.nextLine().trim());
			return AudioSystem.getMixer(mixers[idx]);
		}
	}

	/**
	 * System default mixer.
	 *
	 * @return system default mixer
	 */
	public static Mixer defaultMixer() {
		return AudioSystem.getMixer(null);
	}

	/**
	 * Builds a new channel for output of audio data using reasonable settings.
	 * Runtime exception on a lack of lines for output.
	 *
	 * @param mix javax.sound mixer to write audio frames to
	 * @return channel for registering audio writers to
	 */
	public static ChannelMixer channel( Mixer mix ) {
		try {
			return new ChannelMixer(0.2f, 512, mix);

		} catch (LineUnavailableException e) {
			System.err.println("No line available for output");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * All the information you might want on a given mixer. Needed because apparently
	 * a whole bunch of the AudioFormat's I wanted were'nt supported by javax.sound on
	 * my system.
	 *
	 * @param mix mixer to output data line info for
	 */
	public static void diagnostics( Mixer mix ) {
		Line.Info[] infos = mix.getSourceLineInfo();

		for ( Line.Info inf : infos ) {
			if ( inf instanceof DataLine.Info ) {
				DataLine.Info dlInfo = (DataLine.Info) inf;
				AudioFormat[] supported = dlInfo.getFormats();
				System.out.println("Mixer supports: ");

				for ( AudioFormat af : supported ) {
					System.out.println(af);
				}
			}
		}
	}
}
