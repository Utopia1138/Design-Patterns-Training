package org.red.decorator;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.Renderable;

public class TranslateDecorator extends RenderDecorator {
	private float x, y, z;

	public TranslateDecorator(float x, float y, float z, Renderable target) {
		super(target);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void render() {
		GL11.glTranslatef(x, y, z);
		this.getRenderTarget().render();
	}
}
