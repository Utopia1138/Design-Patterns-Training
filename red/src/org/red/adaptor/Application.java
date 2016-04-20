package org.red.adaptor;

import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.GFXContext;

/**
 * Shameless re-implementation of the observer pattern example to
 * show that the Mouse observable is an adaptor. 
 */
public class Application {
	private static final int WIDTH = 400, HEIGHT = 400;

	public static void main(String[] args) {
		CursorAdaptor adaptor = new CursorAdaptor();
		
		GLFWCursorPosCallback callback = adaptor;
		Observable<CursorPos> cursor = adaptor;
		
		GFXContext ctx = new GFXContext("Adaptor Pattern", WIDTH, HEIGHT);
		GLFW.glfwSetCursorPosCallback(ctx.getWindowRef(), callback);

		// With lambdas, we can directly adapt simple functional interfaces
		Consumer<CursorPos> colourMapper = pos -> bg(pos.getX(), pos.getY());
		
		cursor.bind(colourMapper::accept);
		cursor.bind( pos -> System.out.printf("Got position of [%.2f][%.2f]%n", pos.getX(), pos.getY()) );

		ctx.run();
	}

	public static void bg(double x, double y) {
		GL11.glClearColor(
			(float) (x / WIDTH), 0.f,
			(float) (y / HEIGHT), 0.f );
	}
}
