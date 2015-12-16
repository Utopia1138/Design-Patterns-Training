package org.red.observer;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.GFXContext;

/**
 * Example of the Observer pattern. Observers respond to mouse updates
 * to play back tones based on the X/Y coordinates of a GLFW window (a bit like a theremin),
 * to alter the colour of the GL display based on the coordinates of the mouse, and
 * report the mouse events to the console.
 */
public class Application {
	private static final int WIDTH = 400, HEIGHT = 400;

	public static void main(String[] args) {
		GFXContext ctx = new GFXContext("Observer Pattern", WIDTH, HEIGHT);
		Mouse mouse = new Mouse(ctx.getWindowRef());

		// Create tones on X/Y coordinates
		Theremin theremin = new Theremin(WIDTH, HEIGHT);
		mouse.bind(theremin);

		// Debug mouse events
		mouse.bind(e -> {
			switch(e.getType()) {
			case CLICK:
				System.out.printf( "Got click event for %d%n", e.getButton() );
				break;
			case MOVE:
				System.out.printf("X [%04f], Y [%04f] %n", e.getMouse().getX(), e.getMouse().getY());
				break;
			case RELEASE:
				System.out.printf( "Got release event for %d%n", e.getButton() );
				break;
			}
		});

		// Change colour of GL background on mouse coord
		mouse.bind(e -> GL11.glClearColor(
			(float) (e.getMouse().getX() / WIDTH),
			0.f,
			(float) (e.getMouse().getY() / HEIGHT),
			0.f ));

		new Thread(theremin).start();
		ctx.run();
		theremin.stop();
	}
}
