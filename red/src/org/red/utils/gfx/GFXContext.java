package org.red.utils.gfx;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.red.decorator.MatrixStackDecorator;

/**
 * Simplified GLFW windowing/event looping. At some point this should
 * be extended to allow per-frame drawing.
 */
public class GFXContext implements Runnable {
	private final long window;
	private Clock clock = new Clock();
	private List<Renderable> models = new ArrayList<>();
	private List<FrameListener> listeners = new ArrayList<>();

	public GFXContext(String title, int w, int h) {
		if ( GLFW.glfwInit() != GLFW.GLFW_TRUE )
			throw new IllegalStateException("Unable to initialise GLFW");
		
		window = GLFW.glfwCreateWindow(w, h, title, MemoryUtil.NULL, MemoryUtil.NULL);
		if ( window == MemoryUtil.NULL )
			throw new IllegalStateException("Failed to create window");

		GLFW.glfwMakeContextCurrent(window);

		GL.createCapabilities();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, w, 0, h, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(window);
	}

	public long getWindowRef() {
		return window;
	}

	public Clock getClock() {
		return clock;
	}

	public GFXContext register(Renderable model) {
		// Wrapping these as such ensures modifications to the GL
		// transformation matrices (translation, scaling, rotation etc.)
		// don't have an impact on other renderable targets
		models.add(new MatrixStackDecorator(model));
		return this;
	}

	public GFXContext remove(int index) {
		models.remove(index);
		return this;
	}

	public GFXContext register(FrameListener listener) {
		listeners.add(listener);
		return this;
	}

	@Override
	public void run() {
		try {
			GL.createCapabilities();
			GL11.glClearColor(0.f, 0.f, 0.f, 0.f);

			while( GLFW.glfwWindowShouldClose(window) == GLFW.GLFW_FALSE ) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT );
				clock.tick();
				listeners.forEach(listener -> listener.tick(clock));
				models.forEach(model -> model.render());
				GLFW.glfwSwapBuffers(window);
				GLFW.glfwPollEvents();
			}

			GLFW.glfwDestroyWindow(window);
		} finally {
			GLFW.glfwTerminate();
		}
	}
}
