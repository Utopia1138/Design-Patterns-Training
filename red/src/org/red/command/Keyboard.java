package org.red.command;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard {
	private Map<Long, Command> keyMap = new HashMap<>();

	// Need to maintain this here to avoid potential cb garbage collection
	private GLFWKeyCallback onKey;

	public Keyboard(long windowRef) {
		onKey = GLFWKeyCallback.create(
			(window, key, scancode, action, mods) -> this.invoke( key, action ) );

		GLFW.glfwSetKeyCallback( windowRef, onKey );
	}

	public Keyboard setCommand( long key, Command command ) {
		keyMap.put( key, command );
		return this;
	}

	public void invoke(long key, long action) {
		if ( action == GLFW.GLFW_PRESS && keyMap.containsKey( key ) ) {
			keyMap.get( key ).execute();
		}
	}
}
