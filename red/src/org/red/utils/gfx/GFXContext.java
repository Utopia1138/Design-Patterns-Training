package org.red.utils.gfx;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

/**
 * Simplified GLFW windowing/event looping. At some point this should
 * be extended to allow per-frame drawing.
 */
public class GFXContext implements Runnable {
	private final long window;

	public GFXContext(String title, int w, int h) {
		if ( GLFW.glfwInit() != GLFW.GLFW_TRUE )
			throw new IllegalStateException("Unable to initialise GLFW");
		
		window = GLFW.glfwCreateWindow(w, h, title, MemoryUtil.NULL, MemoryUtil.NULL);
		if ( window == MemoryUtil.NULL )
			throw new IllegalStateException("Failed to create window");

		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(window);
	}

	public long getWindowRef() {
		return window;
	}

	@Override
	public void run() {
		try {
			GL.createCapabilities();
			GL11.glClearColor(0.f, 0.f, 0.f, 0.f);

			while( GLFW.glfwWindowShouldClose(window) == GLFW.GLFW_FALSE ) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT );
				GLFW.glfwSwapBuffers(window);
				GLFW.glfwPollEvents();
			}

			GLFW.glfwDestroyWindow(window);
		} finally {
			GLFW.glfwTerminate();
		}
	}
}
