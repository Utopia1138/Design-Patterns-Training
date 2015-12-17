package org.red.observer;

/**
 * Mouse state change events. This maintains the reference
 * to the underlying (observed) mouse so that further state
 * can be obtained there.
 */
public class MouseEvent {
	public static enum Type {
		CLICK, RELEASE, MOVE
	}

	private final Type type;
	private final Mouse mouse;
	private final int button;

	public MouseEvent(Type type, Mouse mouse) {
		this(type, mouse, -1);
	}

	public MouseEvent(Type type, Mouse mouse, int button) {
		this.type = type;
		this.mouse = mouse;
		this.button = button;
	}

	public Type getType() {
		return type;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public int getButton() {
		return button;
	}
}
