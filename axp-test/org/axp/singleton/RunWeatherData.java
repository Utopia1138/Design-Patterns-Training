package org.axp.singleton;

import org.axp.singleton.ui.WeatherUI;

/**
 * Run the weather app. Because we want to test a thread-safe version of the singleton pattern,
 * we'll create two instances of the UI. Each has a dedicated {@link WeatherController}, but
 * they share a single {@link WeatherService}. 
 */
public class RunWeatherData {
	public static void main( String... args ) {
		new WeatherUI( new WeatherController() ).setVisible( true );
		new WeatherUI( new WeatherController() ).setVisible( true );
	}
}
