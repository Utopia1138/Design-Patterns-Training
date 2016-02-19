package org.axp.singleton.event;

import java.util.Set;

import javax.swing.event.ChangeEvent;

import org.axp.singleton.WeatherController;

/**
 * Notification that the weather has been loaded for a list of locations.
 */
public class WeatherAvailableEvent extends ChangeEvent {
	private static final long serialVersionUID = 8621938868901840726L;
	
	private String[] locations;
	
	public WeatherAvailableEvent( WeatherController controller, Set<String> locations ) {
		super( controller );
		this.locations = locations.toArray( new String[] {} );
	}
	
	public String[] getAvailableLocations() {
		return this.locations;
	}
	
	@Override
	public WeatherController getSource() {
		return (WeatherController) super.getSource();
	}
}
