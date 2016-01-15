package org.red.decorator;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.Renderable;

/**
 * Ensures all operations by the composed renderable type apply to their
 * own GL matrix (and thus don't mess with other renderables).
 */
public class MatrixStackDecorator extends RenderDecorator {

	public MatrixStackDecorator(Renderable target) {
		super(target);
	}

	@Override
	public void render() {
		GL11.glPushMatrix();
		this.getRenderTarget().render();
		GL11.glPopMatrix();
	}
}
