package com.clearscene.Observer.Cells;

import java.util.ArrayList;

import com.clearscene.Observer.Observe.Observer;
import com.clearscene.Observer.Observe.Subject;

public class Cell implements Observer, Subject {

	int cellsCurrentHeat;
	ArrayList<Observer> interestedInMe = new ArrayList();
	ArrayList<Subject> befriendedCells = new ArrayList();

	public Cell() {
		cellsCurrentHeat = 1000;
		
		// Add some variations so we don't see just a lamina population death
		if( Math.random() > 0.5 ) {
			if( Math.random() < 0.3 ) {
				cellsCurrentHeat += 0;
			}
			else if( Math.random() < 0.8 ) {
				cellsCurrentHeat += 20;
			}
			else  {
				cellsCurrentHeat += 50;
			}
		}
		else {
			if( Math.random() < 0.3 ) {
				cellsCurrentHeat -= 0;
			}
			else if( Math.random() < 0.8 ) {
				cellsCurrentHeat -= 20;
			}
			else  {
				cellsCurrentHeat -= 50;
			}
		}
	}
	
	
	//
	// For the 'Subject' interface
	//
	@Override
  public void registerObserver( Observer someoneInterestedInMe ) {
		// Keep a list of people who want to hear about my problems
		if( someoneInterestedInMe instanceof Cell ) {
			interestedInMe.add( someoneInterestedInMe );
		}
  }

	@Override
  public void deregisterObserver( Observer someoneNoLongerInterestedInMe ) {
		// Some has told me they are about to die. No longer tell them about my problems
	  int id = interestedInMe.indexOf( someoneNoLongerInterestedInMe );
	  interestedInMe.remove( id );
  }

	@Override
  public void notifyObservers() {
		// I'm loosing heat, ask for help from all the cells interested in me.
	  for( Observer someoneInterestedInMe : interestedInMe ) {
	  	someoneInterestedInMe.askForHelp( this, cellsCurrentHeat );
	  }

  }
	
	
	
	//
	// For the 'Observer' Interface
	//
	@Override
  public void askForHelp( Subject cellInTrouble, int cellInTroublesHeat ) {
		// A cell that I'm interested in has asked for help.
		// Only give help if they really need it (compared to me)
		if( cellInTroublesHeat*1.20 < cellsCurrentHeat ) {
			sendHeat( (Cell)cellInTrouble, (int)((cellsCurrentHeat-cellInTroublesHeat) * 0.15) );
		}
  }
	
	
	
	// Keep track of who I'm registering with, so I can de-register with them if I die.
	public void registerWith( Subject interestingCell ) {
		interestingCell.registerObserver( this );
		befriendedCells.add( interestingCell );
	}
	
	
	
	//
	// Heat exchange
	//
	public void sendHeat( Cell cellInTrouble, int domateHeat ) {
		if( domateHeat < cellsCurrentHeat ) {
			cellsCurrentHeat -= domateHeat;
			cellInTrouble.receiveHeat( domateHeat );
		}
	}
	
	public void receiveHeat( int receivedHeat ) {
		// Death and taxes
		cellsCurrentHeat += (receivedHeat*0.5);
	}

	
	
	//
	// Handle the raverages of life
	//
	public int ravage( int effect ) {

		int neighburghs = interestedInMe.size();
		int removeHeat = effect - (neighburghs/2); // more neighburgh loose less heat

		cellsCurrentHeat -= removeHeat;
		if ( cellsCurrentHeat > 1 ) {
			notifyObservers(); // I'm getting col - ask for help
			return cellsCurrentHeat; // Tell caller my head for display purpuses.
		}
		
		death(); // If I died, cleanup
		return 0;
	}
	
	// Cleanup
	public void death() {
		// Tell everyone I was friends with, that I'm no longer interested - since I'm dead.
	  for( Subject befriendedCell : befriendedCells ) {
	  	// Nobody will ask me for help now.
	  	befriendedCell.deregisterObserver( this );	  
	  }
	}
	
}
