package org.red.utils.gfx;

import org.lwjgl.glfw.GLFW;

public class Clock {
	double last, delta;

	public Clock() {
		last = GLFW.glfwGetTime();
		delta = 0.0;
	}

	public void tick() {
		double time = GLFW.glfwGetTime();
		delta = time - last;
		last = time;
	}

	public double getDelta() {
		return delta;
	}
}
