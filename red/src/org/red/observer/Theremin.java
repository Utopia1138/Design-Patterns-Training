package org.red.observer;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.Mixer;

import org.lwjgl.glfw.GLFW;
import org.red.strategy.Oscillator;
import org.red.strategy.SineWave;
import org.red.strategy.WaveformAlgorithm;
import org.red.utils.au.ChannelMixer;
import org.red.utils.au.MixerUtils;

/**
 * It's a theremin! Kind of...
 * Updates the tone of oscillators based on the X and Y position
 * of mouse, plays the tone back when the left mouse button is
 * pressed down.
 */
public class Theremin implements Observer<MouseEvent>, Runnable {
	private Oscillator a, b;
	private ChannelMixer channel;
	private final float scaleA, scaleB;
	private AtomicBoolean
		play = new AtomicBoolean(false),
		running = new AtomicBoolean(true);

	public Theremin(float canvasX, float canvasY) {
		Mixer mix = MixerUtils.defaultMixer();
		channel = MixerUtils.channel(mix);

		WaveformAlgorithm wave = new SineWave();
		a = new Oscillator().setWaveform(wave);
		b = new Oscillator().setWaveform(wave);

		channel
			.register(a)
			.register(b);

		scaleA = 1200 / canvasX;
		scaleB = 1200 / canvasY;
	}

	@Override
	public void run() {
		while ( running.get() ) {
			if ( play.get() ) {
				channel.tick();
			}
		}
	}

	public void stop() {
		running.set(false);
	}

	@Override
	public void onEvent(MouseEvent event) {
		switch( event.getType() ) {
		case CLICK:
			if ( event.getButton() == GLFW.GLFW_MOUSE_BUTTON_LEFT )
				play.set(true);
			break;

		case RELEASE:
			if ( event.getButton() == GLFW.GLFW_MOUSE_BUTTON_LEFT )
				play.set(false);
			break;

		case MOVE:
			updateFreqFromCanvas(
				(float) event.getMouse().getX(),
				(float) event.getMouse().getY());
			break;		
		}
	}

	private synchronized void updateFreqFromCanvas(float x, float y) {
		float aFreq = x * scaleA;
		float bFreq = y * scaleB;
		a.setFrequency(aFreq < 0.f ? 0.f : aFreq);
		b.setFrequency(bFreq < 0.f ? 0.f : bFreq);
	}
}
