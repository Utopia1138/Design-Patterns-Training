package org.red.decorator;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.Renderable;

public class ColourDecorator extends RenderDecorator {
	private float r, g, b;
	
	public ColourDecorator(float r, float g, float b, Renderable target) {
		super(target);
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void render() {
		GL11.glColor3f(r, g, b);
		this.getRenderTarget().render();
	}
}
