package org.red.template;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.red.utils.gfx.Clock;

/**
 * Template for OpenGL based graphics contexts. This
 * sets up the GLFW window and manages the event loop
 * and timing, deferring drawing to the subtype.
 */
public abstract class GraphicsContext implements Runnable {
	private final long window;
	private Clock clock = new Clock();

	public GraphicsContext() {
		if ( GLFW.glfwInit() != GLFW.GLFW_TRUE )
			throw new IllegalStateException("Unable to initialise GLFW");
		
		window = GLFW.glfwCreateWindow(600, 600, "Template Method Pattern", MemoryUtil.NULL, MemoryUtil.NULL);

		if ( window == MemoryUtil.NULL )
			throw new IllegalStateException("Failed to create window");
	}

	public long getWindowRef() {
		return window;
	}

	public Clock getClock() {
		return clock;
	}

	public void show() {
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();

		beforeWindowDisplay();

		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(window);
	}

	@Override
	public void run() {
		try {
			show();
			GL.createCapabilities();
			GL11.glClearColor(0.f, 0.f, 0.f, 0.f);

			while( GLFW.glfwWindowShouldClose(window) == GLFW.GLFW_FALSE ) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT );
				clock.tick();
				tick( clock );
				GLFW.glfwSwapBuffers(window);
				GLFW.glfwPollEvents();
			}

			beforeWindowClose();
			GLFW.glfwDestroyWindow(window);
		} finally {
			GLFW.glfwTerminate();
		}
	}

	/**
	 * Hook run before the GLFW window is displayed to the user. 
	 */
	public void beforeWindowDisplay() {}

	/**
	 * Hook run prior to closing the GLFW window and cleanup of
	 * OpenGL.
	 */
	public void beforeWindowClose() {}

	/**
	 * Per-frame drawing/animation update routines.
	 *
	 * @param clock contains frame delta timings for animation
	 */
	public abstract void tick( Clock clock );

}
