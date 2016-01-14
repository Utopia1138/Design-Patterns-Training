package org.red.decorator;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.Renderable;

public class RotationDecorator extends RenderDecorator {
	private float x, y, z, angle;

	public RotationDecorator(float x, float y, float z, float angle, Renderable target) {
		super(target);
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}

	public float getAngle() {
		return angle;
	}

	public RotationDecorator setAngle(float angle) {
		this.angle = angle;
		return this;
	}

	@Override
	public void render() {
		GL11.glRotatef(angle, x, y, z);
		this.getRenderTarget().render();
	}
}
