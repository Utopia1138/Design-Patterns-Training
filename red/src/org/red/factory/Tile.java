package org.red.factory;

import org.lwjgl.opengl.GL11;
import org.red.utils.gfx.Renderable;

public class Tile implements Renderable {
	// Could do something more exciting, like render a texture,
	// but it's late and I just want to see this working.
	private final float r, g, b;

	public Tile(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	@Override
	public void render() {
		GL11.glColor3f(r, g, b);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(-1, -1);
		GL11.glVertex2f( 1, -1);
		GL11.glVertex2f( 1,  1);
		GL11.glVertex2f(-1,  1);
		GL11.glEnd();
	}
}
