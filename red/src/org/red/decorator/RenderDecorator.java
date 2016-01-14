package org.red.decorator;

import org.red.utils.gfx.Renderable;

public abstract class RenderDecorator implements Renderable {
	private Renderable target;
	
	protected RenderDecorator(Renderable target) {
		this.target = target;
	}

	public Renderable getRenderTarget() {
		return target;
	}

}
