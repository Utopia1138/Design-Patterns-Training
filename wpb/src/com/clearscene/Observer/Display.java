package com.clearscene.Observer;

import com.clearscene.Observer.Observe.DisplayObserver;
import com.clearscene.Observer.Observe.DisplaySubject;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Display implements DisplayObserver {

	public Display( int worldSizex, int worldSizey ) {
		AnsiConsole.systemInstall();
		System.out.print( ansi().eraseScreen() );
		
		// 
		// Draw border
		//
		String hor = "-";
		String ver = "|";
		for ( int x = 0 ; x <= worldSizex ; x++ ) {
			hor += "-";
			ver += " ";
		}
		hor += "-";
		ver += "|";
		
		System.out.print( ansi().cursor( 1, 1 ).render( hor ) );
		for ( int x = 1 ; x <= worldSizey+1 ; x++ ) {
			System.out.print( ansi().cursor( x+1, 1 ).render( ver ) );
		}
		System.out.println( ansi().cursor( worldSizey+3, 1 ).render( hor ) );
	}

	
	//
	// Someone has told me that the display has changed.
	//
	@Override
  public void updateDisplay( int x, int y, Plot.state state, int heat ) {

	  if( state == Plot.state.ALIVE ) {
	  	System.out.print( ansi().cursor( y+2, x+2 ).fg( getColour( heat ) ).render( "O" ) );
	  } 
	  else if (state == Plot.state.DEAD ) {
	  	System.out.print( ansi().cursor( y+2, x+2 ).fg( Color.DEFAULT ).render( "." ) );
	  }
	  else {
	  	System.out.print( ansi().cursor( y+2, x+2 ).fg( Color.DEFAULT ).render( " " ) );
	  }
	  
  }
	
	
	//
	// Add some colour (not color)
	// 
	public Color getColour( int heat ) {
		if ( heat > 900 ) {
			return Color.WHITE;
		}
		else if ( heat > 800 ) {
			return Color.MAGENTA;
		}
		else if ( heat > 600 ) {
			return Color.RED;
		}
		else if ( heat > 400 ) {
			return Color.YELLOW;
		}
		else if ( heat > 200 ) {
			return Color.GREEN;
		}
		return Color.BLUE;
	}
	
	
}
