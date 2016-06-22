package org.red.adaptor;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.red.observer.Observer;

/**
 * Adaptor for the single callback {@link GLFWCursorPosCallback} interface
 * to an Observable that supports multiple listeners for mouse position changes.
 */
public class CursorAdaptor extends GLFWCursorPosCallback implements Observable<CursorPos> {
	private List<Observer<CursorPos>> observers = new ArrayList<>();

	@Override
	public CursorAdaptor bind(Observer<CursorPos> observer) {
		observers.add(observer);
		return this;
	}

	@Override
	public CursorAdaptor unbind(Observer<CursorPos> observer) {
		observers.remove(observer);
		return this;
	}

	@Override
	public void invoke(long window, double xpos, double ypos) {
		update(new CursorPos(xpos, ypos));
	}

	@Override
	public void update( CursorPos pos ) {
		observers.forEach(o -> o.onEvent(pos));
	}

}
