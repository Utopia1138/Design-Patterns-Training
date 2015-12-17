package org.axp.observer;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Observable;

import org.axp.observer.display.TextDisplay;
import org.axp.observer.display.WebDisplay;
import org.axp.observer.post.BlogPost;
import org.axp.observer.post.TwitterPost;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ObserverTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	private static final BlogPost[] AXP_BLOG = new BlogPost[] {
		new BlogPost( "Alex P", "152 reasons I like cats",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore "
				+ "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut "
				+ "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum "
				+ "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui "
				+ "officia deserunt mollit anim id est laborum.", "http://axpblog/152-reasons-I-l" ),
		new BlogPost( "Alex P", "38 hilarious cat GIFs",
				"In est metus, imperdiet a finibus vel, blandit nec mi. Morbi id augue vitae leo rutrum tempus. Nullam "
				+ "ullamcorper nisl dui, non posuere nisi porta et. Interdum et malesuada fames ac ante ipsum primis in "
				+ "faucibus. Phasellus accumsan lacinia urna, imperdiet placerat nulla mollis at. Vivamus ac mauris velit. "
				+ "Mauris varius vitae massa in tincidunt. Nulla facilisis pellentesque finibus. Praesent lobortis auctor "
				+ "urna ut sodales.", "http://axpblog/38-hilarious-ca" ),
	}; 
	
	private static final TwitterPost[] AXP_TWITTER = new TwitterPost[] {
		new TwitterPost( "alex_p", "Cats are amazing!  :3", "http://twittah/asd98y32fa" ),
		new TwitterPost( "alex_p", "Actually, I think I like dogs more than cats. Haha, April Fools!",
				"http://twittah/234ny0qn1a" )
	};
	
	public static void assertTextEquals( String errorMessage, String actualText, String...expectedLines ) {
		StringBuilder expected = new StringBuilder();
		
		for ( String line : expectedLines ) {
			expected.append( line ).append( System.getProperty( "line.separator" ) );
		}
		
		assertEquals( errorMessage, expected.toString(), actualText );
	}

	@Test
	public void testFeeds() {
		WebDisplay web = new WebDisplay();
		ByteArrayOutputStream w = new ByteArrayOutputStream();
		web.setPrintStream( new PrintStream( w ) );
		
		TextDisplay text = new TextDisplay();
		ByteArrayOutputStream t = new ByteArrayOutputStream();
		text.setPrintStream( new PrintStream( t ) );
		
		Feed<BlogPost> myBlog = new Feed<BlogPost>();
		Feed<TwitterPost> myTwitter = new Feed<TwitterPost>();
		
		myBlog.addObserver( web );
		myBlog.publish( AXP_BLOG[0] );
		
		myBlog.addObserver( text );
		myTwitter.addObserver( web );
		myTwitter.addObserver( text );
		
		myTwitter.publish( AXP_TWITTER[0] );
		myBlog.publish( AXP_BLOG[1] );
		
		myTwitter.deleteObserver( web );
		myTwitter.publish( AXP_TWITTER[1] );
		
		assertTextEquals( "Blog output mismatch", w.toString(),
				"<div class='post'>",
				"<h3>Alex P: <a href='http://axpblog/152-reasons-I-l'>152 reasons I like cats</a></h3>",
				"<p>" + AXP_BLOG[0].getSummary() + "</p>",
				"</div>",
				"<div class='post'>",
				"<h3>alex_p <a href='http://twittah/asd98y32fa'>writes</a>:</h3>",
				"<p>" + AXP_TWITTER[0].getFullText() + "</p>",
				"</div>",
				"<div class='post'>",
				"<h3>Alex P: <a href='http://axpblog/38-hilarious-ca'>38 hilarious cat GIFs</a></h3>",
				"<p>" + AXP_BLOG[1].getSummary() + "</p>",
				"</div>" );
		
		assertTextEquals( "Text output mismatch", t.toString(),
				AXP_TWITTER[0].getFullText(), "    -- alex_p",
				"---",
				"= 38 hilarious cat GIFs =", AXP_BLOG[1].getFullText(), "    -- Alex P",
				"---",
				AXP_TWITTER[1].getFullText(), "    -- alex_p",
				"---" );
	}

	@Test
	public void testNullObject() {
		expectedEx.expect( IllegalArgumentException.class );
		expectedEx.expectMessage( "Got a null Post" );
		
		WebDisplay web = new WebDisplay();
		Feed<TwitterPost> myTwitter = new Feed<TwitterPost>();
		myTwitter.addObserver( web );
		myTwitter.publish( null );
	}

	@Test
	public void testNonPostObject() {
		expectedEx.expect( IllegalArgumentException.class );
		expectedEx.expectMessage( "Can only display Post objects, not java.lang.String" );
		
		final class MyObservable extends Observable {
			@Override // LOL, hack around the shortcoming described in the chapter
			public void setChanged() {
				super.setChanged();
			}
		}
		
		WebDisplay web = new WebDisplay();
		MyObservable subject = new MyObservable();
		subject.addObserver( web );
		subject.setChanged();
		subject.notifyObservers( "Wibble wobble splat" );
	}
}