package org.red.decorator;

import org.red.utils.gfx.GFXContext;

/**
 * Shows the decorator pattern as a way to manage OpenGL
 * transformations.
 */
public class Application {
	public static void main(String[] args) {
		GFXContext ctx = new GFXContext("Decorator Pattern", 600, 600);

		RotationDecorator red, blue;

		// There has to be a better way! (cue builder pattern)
		ctx.register(
			new TranslateDecorator(300, 300, 0,
				red = new RotationDecorator(0, 0, 1, 0,
					new ColourDecorator(0.75f, 0.33f, 0.25f, new Square()))
						));

		ctx.register(
			new TranslateDecorator(300, 300, 0,
				blue = new RotationDecorator(0, 0, 1, 0,
					new ColourDecorator(0.33f, 0.33f, 0.75f, new Square()))
						));

		// Animate a little so that it's a tiny bit less boring.
		final float rps = 0.15f;

		ctx.register(clock ->
			red.setAngle((float) (red.getAngle() + 360.0 * rps * clock.getDelta())) );

		ctx.register(clock ->
			blue.setAngle((float) (blue.getAngle() - 360.0 * rps * clock.getDelta())) );

		ctx.run();
	}
}
