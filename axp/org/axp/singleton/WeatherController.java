package org.axp.singleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.axp.singleton.event.WeatherAvailableEvent;
import org.axp.singleton.event.WeatherChangeEvent;

/**
 * The controller for the weather app. Note that each instance of the UI has its own controller; this contrasts
 * with {@link WeatherService}, of which only one instance can ever exist (per JVM, anyway).
 */
public class WeatherController implements ItemListener, ActionListener {
	private ChangeListener listener;
	
	public void setChangeListener( ChangeListener listener ) {
		this.listener = listener;
	}
	
	private void notifyListener( ChangeEvent evt ) {
		if ( this.listener != null ) {
			this.listener.stateChanged( evt );
		}
	}

	/**
	 * This fires when the user selects a new city. We ask the weather service for the weather for that
	 * city, and notify the UI that we've got something new for it to display.
	 */
	@Override
	public void itemStateChanged( ItemEvent evt ) {
		if ( evt.getStateChange() == ItemEvent.SELECTED ) {
			String city = evt.getItem().toString();
			Weather weather = WeatherService.getData().getWeather( city );
			System.out.println( "Loaded weather for " + city );
			notifyListener( new WeatherChangeEvent( this, weather ) );
		}
	}

	/**
	 * This fires when the user hits the "load" button. We instantiate the weather service, which will load
	 * up all the weather data, and then notify the UI that we're good again.
	 */
	@Override
	public void actionPerformed( ActionEvent evt ) {
		if ( "load".equals( evt.getActionCommand() ) ) {
			Set<String> locations = WeatherService.getData().getCities();
			notifyListener( new WeatherAvailableEvent( this, locations ) );
		}
	}
}
