package org.red.factory;

import java.util.Random;
import java.util.function.Supplier;

import org.red.observer.Mouse;
import org.red.observer.MouseEvent;
import org.red.utils.gfx.GFXContext;

/**
 * Showcase for the factory pattern. Click to regen maps. This defines two factory types:
 *
 * <ul>
 *   <li>MapFactory: builds maps, which contain tilesets and co-ordinates.</li>
 *   <li>NoiseFactory: generates noise-maps, which can be used to tile maps.</li>
 * </ul>
 * 
 * Fun stuff to try:
 * 
 * <ul>
 *   <li>Change the number of tiles.</li>
 *   <li>
 *     Change the perlin noise octave blend weighting,
 *     higher values result in more complex maps.
 *   </li>
 *   <li>Non-square tile dimensions.</li>
 * </ul>
 *
 * Areas for potential improvement:
 * <ul>
 *   <li>Per tile weighting, so that tiles can be defined as rarer or more common.</li>
 *   <li>More noise-map algorithms.</li>
 *   <li>
 *     Maps are swapped on click; this is done in a GLFW callback -
 *     these are automatically destroyed if they take too long, which
 *     larger maps will, resulting in the Java equivalent of use-after-free.
 *   </li>
 *   <li>Vertex buffer objects rather than manual draw routines on tiles, the performance is terrible.</li>
 * </ul>
 */
public class Application {
	private static final int W = 600, H = 600;
	private static final int TILES = 300;
	private static final Random RAND = new Random();

	public static void main(String[] args) {
		MapFactory[] factories = {
			new OverworldMapFactory(TILES, TILES),
			new VolcanoMapFactory(TILES, TILES),
			new AlienLandscapeMapFactory(TILES, TILES) };

		Supplier<MapFactory> next = () -> {
			float val = RAND.nextFloat();
			int idx = val < 0.4 ? 0 : val < 0.8 ? 1 : 2;
			return factories[idx];
		};

		MapGenerator gen = new MapGenerator(
			next.get(),
			new PerlinNoiseFactory().setWeighting(0.65) );

		// If you want boring, boring maps...
		//gen.setNoiseFactory(new WhiteNoiseFactory());

		GFXContext ctx = new GFXContext("Factory Pattern", W, H);
		ctx.register(gen.getMap(W, H));

		Mouse mouse = new Mouse(ctx.getWindowRef());
		mouse.bind(o -> {
			if (o.getType() == MouseEvent.Type.CLICK) {
				gen.setMapFactory(next.get());
				ctx.remove(0);
				ctx.register(gen.getMap(W, H));
			}
		});

		ctx.run();
	}
}
