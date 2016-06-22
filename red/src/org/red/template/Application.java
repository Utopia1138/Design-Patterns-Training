package org.red.template;

import org.red.decorator.ColourDecorator;
import org.red.decorator.RotationDecorator;
import org.red.decorator.Square;
import org.red.decorator.TranslateDecorator;
import org.red.utils.gfx.FrameListener;

/**
 * Showcase for the template method pattern. Here the
 * abstract {@link GraphicsContext} sets up windowing
 * and frame event loops, deferring key stages to the
 * subtype.
 * 
 * In this example the subtype {@link ProjectionContext2D}
 * sets up OpenGL to use a 2D perspective, and manages
 * sets of 2D models and animators.
 *
 * This is basically a re-write of the {@link GFXContext}
 * type these samples have been using up to this point
 * with a better separation of concerns between GLFW
 * boilerplate/event looping, and actual drawing and
 * animation.
 */
public class Application {
	public static void main(String[] args) {
		final float rps = 0.15f;

		RotationDecorator red = new RotationDecorator(0, 0, 1, 0,
				new ColourDecorator(0.75f, 0.33f, 0.25f, new Square()));

		FrameListener animator = clock ->
			red.setAngle((float) (red.getAngle() + 360.0 * rps * clock.getDelta()));  

		GraphicsContext context = new ProjectionContext2D()
			.register(new TranslateDecorator(300, 300, 0, red))
			.register(animator);

		context.run();
	}
}
