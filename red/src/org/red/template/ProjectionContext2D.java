package org.red.template;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.red.decorator.MatrixStackDecorator;
import org.red.utils.gfx.Clock;
import org.red.utils.gfx.FrameListener;
import org.red.utils.gfx.Renderable;

/**
 * A graphics context for simple 2D animation.
 */
public class ProjectionContext2D extends GraphicsContext {

	private List<Renderable> models = new ArrayList<>();
	private List<FrameListener> listeners = new ArrayList<>();

	public ProjectionContext2D register(Renderable model) {
		models.add(new MatrixStackDecorator(model));
		return this;
	}

	public ProjectionContext2D register(FrameListener listener) {
		listeners.add(listener);
		return this;
	}

	@Override
	public void beforeWindowDisplay() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 600, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	@Override
	public void tick( Clock clock ) {
		listeners.forEach(listener -> listener.tick(clock));
		models.forEach(Renderable::render);
	}
}
