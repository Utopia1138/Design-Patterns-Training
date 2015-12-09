package com.clearscene.Observer;

import java.util.ArrayList;

import com.clearscene.Observer.Cells.Cell;
import com.clearscene.Observer.Observe.DisplayObserver;
import com.clearscene.Observer.Observe.DisplaySubject;
import com.clearscene.Observer.Observe.WorldObserver;

//
// A place to put cells
//
public class Plot implements DisplaySubject, WorldObserver {

	public enum state { ALIVE, DEAD, EMPTY }
	
	ArrayList<DisplayObserver> interestedDisplays = new ArrayList();

	state currentState = state.EMPTY;
	public Cell thisPlotsCell = null;
	int cellsHeat = 1000;
	int x;
	int y;
	
	public Plot( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	
	
	//
	// Used in 'game initalise' to setup the environment
	//
	public void infestWithCell( Cell resident ) {
		this.thisPlotsCell = resident;
		currentState = state.ALIVE;
	}
	
	public void killCell() {
		thisPlotsCell.death();
		currentState = state.DEAD;
	}
	
	public void askToKeepUp2DateAboutSomeCell( Cell interestingNeighburgh ) {
		if( interestingNeighburgh != null ) {
			thisPlotsCell.registerWith( interestingNeighburgh );
		}
	}
	
	public Cell getCell() {
		return thisPlotsCell;
	}

	
	
	//
	// Used in Play() to see if we have a cell to work on
	//
	public state getState() {
		return currentState;
	}
	
	
	
	//
	// For the 'WorldObserver' Interface
	//
	public void ravage() {
		cellsHeat = thisPlotsCell.ravage( 7 );
		if ( cellsHeat < 1  ) {
			thisPlotsCell = null;
			currentState = state.DEAD;
		}
		notifyDisplayers();
	}
	
	
	//
	// For of the 'DisplaySubject' interface
	//
	@Override
  public void registerDisplayer( DisplayObserver displayer ) {
		if( displayer instanceof Display ) {
			interestedDisplays.add( displayer );
		}
  }

	@Override
  public void deregisterDisplayer( DisplayObserver displayer ) {
	  int id = interestedDisplays.indexOf( displayer );
	  interestedDisplays.remove( id );
  }

	@Override
  public void notifyDisplayers() {
	  for( DisplayObserver displayer : interestedDisplays ) {
	  	displayer.updateDisplay( x, y, currentState, cellsHeat );
	  }
  }

}
