package org.red.factory;

import java.util.Random;

import org.red.observer.Mouse;
import org.red.observer.MouseEvent;
import org.red.utils.gfx.GFXContext;

public class Application {
	private static final int W = 600, H = 600;
	private static final int TILES = 600;
	private static final Random RAND = new Random();
	
	public static void main(String[] args) {
		MapFactory[] factories = {new OverworldMapFactory(TILES, TILES), new VolcanoMapFactory(TILES, TILES)};
		MapGenerator gen = new MapGenerator(factories[RAND.nextFloat() < 0.5 ? 0 : 1]);

		GFXContext ctx = new GFXContext("Factory Pattern", W, H);
		ctx.register(gen.getMap(W, H));

		Mouse mouse = new Mouse(ctx.getWindowRef());
		mouse.bind(o -> {
			if (o.getType() == MouseEvent.Type.CLICK) {
				gen.setMapFactory(factories[RAND.nextFloat() < 0.5 ? 0 : 1]);
				ctx.remove(0);
				ctx.register(gen.getMap(W, H));
			}
		});

		ctx.run();
	}
}
