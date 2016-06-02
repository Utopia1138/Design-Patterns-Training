package org.red.state;

import org.lwjgl.glfw.GLFW;
import org.red.command.Keyboard;
import org.red.utils.gfx.GFXContext;

/**
 * Showcases the state design pattern with a controllable hero.
 *
 * State transitions ensure the hero can't do impossible combinations
 * of maneuvers, and allows state transitions to occur at timed intervals
 * (the example here being the hero jumps and must wait to land before
 * being at rest or being able to defend, but may transition to a flying
 * attack state whilst in the air).
 * 
 * Areas of improvement (well, things I'd envisaged at the start but ran out
 * of time on) include actual animation (stubbed console calls just now) and
 * having states update the context with position changes for things like
 * animation and hitboxes.
 *
 */
public class Application {
	public static void main( String[] args ) {
		GFXContext ctx = new GFXContext("State Pattern", 10, 10);
		Keyboard keyboard = new Keyboard( ctx.getWindowRef() );
		
		Hero hero = new Hero();
		
		keyboard
			.setCommand( GLFW.GLFW_KEY_ENTER, hero::attack )
			.setCommand( GLFW.GLFW_KEY_LEFT_SHIFT, hero::defend )
			.setCommand( GLFW.GLFW_KEY_SPACE, hero::jump );
		
		hero.start();
		ctx.run();
		hero.stop();
	}
}
