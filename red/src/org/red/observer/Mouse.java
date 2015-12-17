package org.red.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.red.observer.MouseEvent.Type;

/**
 * Awaits mouse updates from GLFW, forms them into events that can
 * be monitored by multiple observers.
 */
public class Mouse implements Observable<MouseEvent> {
	private List<Observer<MouseEvent>> observers = new ArrayList<>();
	private double x, y;
	private Map<Integer, Boolean> buttonState = new HashMap<>();

	public Mouse(long window) {
		// As it happens, the GLFW callbacks are abstract classes, so this can't
		// implement them directly in the outer type
		GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xpos, double ypos) {
				Mouse.this.setCursorPos(xpos, ypos);
			}
		});

		GLFW.glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				Mouse.this.setMouseButton(button, action);
			}
		});
	}
	
	@Override
	public Mouse bind(Observer<MouseEvent> observer) {
		observers.add(observer);
		return this;
	}

	@Override
	public Mouse unbind(Observer<MouseEvent> observer) {
		observers.remove(observer);
		return this;
	}

	public Mouse setCursorPos(double x, double y) {
		this.x = x;
		this.y = y;
		notify( new MouseEvent( Type.MOVE, this ) );
		return this;
	}

	public Mouse setMouseButton(int button, int action) {
		this.buttonState.put( button, action == GLFW.GLFW_PRESS );
		notify( new MouseEvent( action == GLFW.GLFW_PRESS ? Type.CLICK : Type.RELEASE, this, button ) );
		return this;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isHeld(int button) {
		return buttonState.containsKey(button) && buttonState.get(button);
	}

	private void notify(MouseEvent event) {
		observers.forEach(o -> o.onEvent(event));
	}
}
