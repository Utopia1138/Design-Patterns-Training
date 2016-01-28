package org.axp.singleton;

/**
 * A weather report for a single location.
 */
public class Weather {
	public final float currTemp, minTemp, maxTemp, humidity, windSpeed;
	public final String weatherDesc;
	
	public Weather( float currTemp, float minTemp, float maxTemp, float humidity, float windSpeed, String weatherDesc ) {
		this.currTemp = currTemp;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.weatherDesc = weatherDesc;
	}
	
	public float getCurrTemp() {
		return this.currTemp;
	}
	
	public float getMinTemp() {
		return this.minTemp;
	}
	
	public float getMaxTemp() {
		return this.maxTemp;
	}
	
	public float getHumidity() {
		return this.humidity;
	}
	
	public float getWindSpeed() {
		return this.windSpeed;
	}
	
	public String getDescription() {
		return this.weatherDesc;
	}
	
	public String toString() {
		return "Weather:\t" + getDescription() +
				"\nCurr temp:\t" + getCurrTemp() +
				"\nMin temp:\t" + getMinTemp() +
				"\nMax temp:\t" + getMaxTemp() +
				"\nHumidity:\t" + getHumidity() +
				"\nWind speed:\t" + getWindSpeed();
	}
}
