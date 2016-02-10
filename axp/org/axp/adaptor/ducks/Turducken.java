package org.axp.adaptor.ducks;

/**
 * The Head First Design Patterns book says:
 * 
 * 	"So what’s a class adapter and why haven’t we told you about it? Because you need multiple
 * 	 inheritance to implement it, which isn’t possible in Java."
 * 
 * If I'm correct, the following is a class adaptor. We don't need multiple inheritence, because
 * {@link Duck} is an interface, and Java allows you to implement as many interfaces as you want.
 * Now, if it was an abstract class, then we'd have a problem.
 */
public class Turducken extends Turkey implements Duck {
	/**
	 * Translate a `quack` call into a `gobble`
	 */
	@Override
	public String quack() {
		return gobble();
	}
	
	/**
	 * Support `fly` by calling Turkey's `fly` method three times in succession. (Similar to the
	 * chapter example for object adaptor.)
	 */
	@Override
	public String fly() {
		StringBuilder sb = new StringBuilder();
		
		for ( int i = 0; i < 3; i++ ) {
			sb.append( '\n' ).append( super.fly() ); // Heh, super.fly
		}
		
		return sb.substring( 1 );
	}
}
