package org.axp.singleton.event;

import javax.swing.event.ChangeEvent;

import org.axp.singleton.Weather;
import org.axp.singleton.WeatherController;

/**
 * Notification that a new weather forecast has been loaded.
 */
public class WeatherChangeEvent extends ChangeEvent {
	private static final long serialVersionUID = 8621938868901840726L;
	
	private Weather weather;
	
	public WeatherChangeEvent( WeatherController controller, Weather weather ) {
		super( controller );
		this.weather = weather;
	}

	public Weather getNewWeather() {
		return this.weather;
	}
	
	@Override
	public WeatherController getSource() {
		return (WeatherController) super.getSource();
	}
}
