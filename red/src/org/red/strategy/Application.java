package org.red.strategy;

import javax.sound.sampled.Mixer;

import org.red.utils.au.MixerUtils;
import org.red.utils.au.ChannelMixer;

/**
 * Showcase for the strategy pattern. The family of algorithms used to
 * build and rebuild a waveform for audio synthesis can vary independently
 * from the client type (here an oscillator). We don't have separate
 * SawtoothOscillator, SineOscillator types.
 *
 */
public class Application {
	public static void main(String[] args) {
		Mixer mix = MixerUtils.defaultMixer();
		ChannelMixer channel = MixerUtils.channel(mix);

		// Build a simple oscillator and play it back.
		Oscillator osc = new Oscillator()
			.setMidiNote(56);

		channel.register(osc);
		MixerUtils.play(channel, 2_000);

		// Oh, snap, we can swap out the waveform generation during
		// runtime without needing to build a whole new oscillator instance
		osc.setWaveform(new SawtoothWave(60));

		Oscillator harmonicOsc = new Oscillator()
			.setWaveform(new SawtoothWave(60))
			.setMidiNote(63);

		channel.register( harmonicOsc );
		
		// Some kind of melody
		Note note = (midi, ms) -> {
			osc.setMidiNote(midi);
			harmonicOsc.setMidiNote(midi + 7);
			MixerUtils.play(channel, ms);
		};
	
		note.from(56, 1000);
		note.from(52, 250);
		note.from(59, 250);
		note.from(61, 500);
		note.from(52, 1000);

		// Some kind of pitch bending
		for (int i = 52; i < 71; i += 3)
			note.from(i, 30);

		for (int i = 71; i > 30; i -= 2)
			note.from(i, 30);
		note.from(37, 500);
		note.from(39, 750);

		channel.close();
	}

	public static interface Note {
		void from(int midi, int ms);
	}
}