package org.axp.proxy.rmi.client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Random;

import org.axp.proxy.rmi.Pokédex;
import org.axp.proxy.rmi.Pokémon;

public class PokémonTrainer {
	private final String name;
	private final Appendable out;
	private final Random rand = new Random();
	
	private int encountered = 0;
	private int discoveries = 0;
	
	public static final String[] POKÉMON_NAMES = new String[] {
		"Axpbok", "Biglett", "Redidash", "Mikeachu", "Jumpluff", "Sandyshrew", "Simonsear", "Rpiduck", "Thierrykion", "Waynemer"
	};
	
	public static final String[] POKÉMON_CATEGORIES = new String[] {
		"Fire", "Water", "Punchy", "Green", "Flying", "Awkward", "Limited Edition", "Lazy", "Confusing", "Epic", "Demonic"
	};
	
	public static final String[] POKÉMON_MOVES = new String[] {
		"Scratch", "Peck", "Glare", "Poison Sting", "Rock Climb", "Absorb", "Body Slam", "Coffee Drink", "Flail", "Charm",
		"Yawn", "Defense Perl", "Java Plume", "Clone", "Merge", "Commit", "Rebase"
	};
	
	public PokémonTrainer( String name, Appendable out ) {
		this.name = name;
		this.out = out;
	}

	public PokémonTrainer( String name ) {
		this( name, System.out );
	}
	
	public void say( String message ) {
		say( message, true );
	}
	
	public void say( String message, boolean newline ) {
		try {
			out.append( this.name ).append( ": " ).append( message );
			if ( newline ) out.append( '\n' );
		}
		catch ( IOException e ) {
			e.printStackTrace();
			System.exit( 100 );
		}
	}
	
	public void say( char c ) {
		try {
			out.append( c );
		}
		catch ( IOException e ) {
			e.printStackTrace();
			System.exit( 100 );
		}
	}
	
	public void pause( long millis ) {
		try {
			Thread.sleep( 1000 );
		}
		catch ( InterruptedException e ) {
			// Ignore
		}
	}
	
	public Pokémon randomPokémon() {
		String name = POKÉMON_NAMES[ rand.nextInt( POKÉMON_NAMES.length ) ];
		String category = POKÉMON_CATEGORIES[ rand.nextInt( POKÉMON_CATEGORIES.length ) ];

		int height = rand.nextInt( 1 << ( rand.nextInt( 10 ) + 5 ) );
		int weight = rand.nextInt( 1 << ( rand.nextInt( 10 ) + 10 ) );
		
		Pokémon monster = new Pokémon( name, category, height, weight );
		
		do {
			monster.attacks().add( POKÉMON_MOVES[ rand.nextInt( POKÉMON_MOVES.length ) ] );
		}
		while ( rand.nextInt( 5 ) < 3 );
		
		return monster;
	}
	
	public void doJourney( int duration ) {
		say( "Waiting for Pokédex to come online", false );
		Pokédex deck = null;
		boolean online = false;
		
		while ( !online ) {
			try {
				deck = PokédexClientFactory.createLocalClient();
				deck.checkOnline();
				online = true;
			}
			catch ( Exception e ) {
				say( '.' );
			}
		}
		
		say( '\n' );
		say( "Pokédex online, going exploring" );
		
		try {
			collect( deck, duration );
			say( "All tired out from collecting Pokémon; going home" );
		}
		catch ( RemoteException e ) {
			say( "Ooops, something's up with my Pokédex. Giving up and going home" );
			e.printStackTrace();
		}
		
		say( "Encountered " + encountered + " Pokémon on my journey" );
		say( discoveries + " of them were new species!" );
	}
	
	public void collect( Pokédex deck, int duration ) throws RemoteException {
		for ( int i = 0; i < duration; i++ ) {
			if ( rand.nextInt( 3 ) == 0 ) {
				Pokémon monster = randomPokémon();
				say( '\n' );
				say( "Found a Pokémon!\n" + monster + "\n--------" );
				encountered++;
				pause( 500 );
				
				if ( deck.isKnown( monster ) ) {
					say( "The Pokédex already knows about this one" );
					pause( 500 );
					
					if ( deck.update( monster ) ) {
						say( "Updated details for this Pokémon" );
					}
					else {
						say( "Couldn't update for some reason" );
					}
				}
				else {
					say( "It's a previously unknown species!" );
					pause( 500 );
					
					if ( deck.addNew( monster ) ) {
						say( "Added it to the Pokédex-- great fame awaits!" );
						discoveries++;
					}
					else {
						say( "Drat! Someone else just added this Pokémon!" );
					}
				}
			}
			
			pause( 1000 );
		}
	}
	
	public static void main( String...args ) {
		String name = ( args.length > 0 ) ? args[0] : "Ash";
		PokémonTrainer trainer = new PokémonTrainer( name );
		trainer.doJourney( 100 );
	}
}
