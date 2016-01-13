package org.red.decorator;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.Renderable;

public class Square implements Renderable {

	@Override
	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(-100, -100);
		GL11.glVertex2f( 100, -100);
		GL11.glVertex2f( 100,  100);
		GL11.glVertex2f(-100,  100);
		GL11.glEnd();
	}
}
