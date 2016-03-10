package org.red.command;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

import org.lwjgl.glfw.GLFW;
import org.red.factory.AlienLandscapeMapFactory;
import org.red.factory.Map;
import org.red.factory.MapCache;
import org.red.factory.MapFactory;
import org.red.factory.OverworldMapFactory;
import org.red.factory.PerlinNoiseFactory;
import org.red.factory.VolcanoMapFactory;
import org.red.utils.gfx.GFXContext;

/**
 * Makes use of the command pattern to invoke commands on keyboard presses.
 * 
 *
 */
public class Application {
	private static final int W = 920, H = 920;
	private static final int TILES = 460;

	public static void main( String[] args ) {
		MapFactory[] factories = {
			new OverworldMapFactory(TILES, TILES),
			new VolcanoMapFactory(TILES, TILES),
			new AlienLandscapeMapFactory(TILES, TILES) };
		
		Supplier<MapFactory> randomSelector = () -> {
			float val = new Random().nextFloat();
			int idx = val < 0.4 ? 0 : val < 0.8 ? 1 : 2;
			return factories[idx];
		};

		MapCache cache = new MapCache(W, H,
			new PerlinNoiseFactory()
				.setOctaves(8)
				.setWeighting(0.65),
			randomSelector);

		GFXContext ctx = new GFXContext("Factory Pattern", W, H);
		ctx.register(cache.get());

		Keyboard keyboard = new Keyboard( ctx.getWindowRef() );

		keyboard
			.setCommand( GLFW.GLFW_KEY_SPACE, () -> nextMap(cache, ctx) )
			.setCommand( GLFW.GLFW_KEY_ESCAPE, () -> GLFW.glfwSetWindowShouldClose( ctx.getWindowRef(), GLFW.GLFW_TRUE ) )

			// Allows user control over the map type
			.setCommand( GLFW.GLFW_KEY_R, () -> cache.setMapSource( randomSelector ) )
			.setCommand( GLFW.GLFW_KEY_O, () -> cache.setMapSource( () -> factories[0] ) )
			.setCommand( GLFW.GLFW_KEY_V, () -> cache.setMapSource( () -> factories[1] ) )
			.setCommand( GLFW.GLFW_KEY_A, () -> cache.setMapSource( () -> factories[2] ) )

			// Macros!
			.setCommand( GLFW.GLFW_KEY_EQUAL,
				new Macro(changeLod(factories, TILES * 2))
					.andThen( () -> cache.clearCache() )
					.andThen( () -> nextMap(cache, ctx) )
				)
			.setCommand( GLFW.GLFW_KEY_MINUS,
				new Macro(changeLod(factories, 100))
					.andThen( () -> cache.clearCache() )
					.andThen( () -> nextMap(cache, ctx) )
				);

		ctx.run();
	}

	public static Command changeLod(MapFactory[] factories, int tiles) {
		return () -> 
			Arrays.stream( factories ).forEach( f -> f.setTiles( tiles, tiles ) );
	}

	public static void nextMap(MapCache cache, GFXContext ctx) {
		Map map = cache.get();

		if ( map != null ) {
			ctx
				.remove(0)
				.register(map);
		}
	}
}
