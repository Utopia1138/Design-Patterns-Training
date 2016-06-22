package com.spg.chapter10.state;


public class StateFactory {
	
	public static GameState getState( State state ) {
		
		switch ( state ) {
			case BUILDER:
			return new BuilderState();
			case CAPTAIN:
			return new CaptainState();
			case CRAFTSMAN:
			return new CraftsmanState();
			case MAYOR:
			return new MayorState();
			case PROSPECTOR:
			return new ProspectorState();
			case SELECTION:
			return new SelectionState();
			case SETTLER:
			return new SettlerState();
			case TRADER:
			return new TraderState();
			default:
			return new SelectionState();
			
		}
		
		
	}
	
}
