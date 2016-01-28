package org.axp.singleton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Weather Service is a class that looks up and then stores the current weather for a list of Scottish
 * cities.
 * 
 * It follows a thread-safe Singleton pattern which gives us the following advantages;
 * <ul>
 *   <li>The weather data is not loaded until the first time {@link getData} is called, saving us both
 *     memory space and startup time.</li>
 *   <li>No matter how many instances of the UI are running, we only ever make the call once.</li>
 *   <li>Two instances of the UI will always show consistent data.</li>
 * </ul>
 *  
 * TODO: this could be extended to periodically reload data rather than just loading at the start and
 * never again. 
 *
 */
public class WeatherService {
	private static final String API_URL =
			"http://api.openweathermap.org/data/2.5/box/city"
			+ "?bbox=-5,60,-1.35,55,15&cluster=yes&APPID=06b2aef44f439f756c751587d1f86eba";
	
	private volatile static WeatherService dataInstance;
	
	private boolean loadedOK = false;
	private TreeMap<String, Weather> data;
	
	/**
	 * The constructor is private because it's a singleton; use {@link #getData()} to get the
	 * instance.
	 */
	private WeatherService() {
		System.out.println( "Fetching from " + API_URL + "..." );
		
		URL url = null;
		
		try {
			url = new URL( API_URL );
		}
		catch ( MalformedURLException e ) {
			e.printStackTrace();
			System.exit( 1 );
		}
		
		JSONParser parser = new JSONParser();
		
		try ( BufferedReader reader = new BufferedReader( new InputStreamReader( url.openStream() ) ) ) {
			JSONObject json = (JSONObject) parser.parse( reader );
			JSONArray cityList = (JSONArray) json.get( "list" );
			System.out.println( "Read " + cityList.size() + " cities" );
			
			this.data = new TreeMap<>();
			
			for ( Object city : cityList ) {
				JSONObject cityData = (JSONObject) city;
				String cityName = (String) cityData.get( "name" );
				
				JSONObject main = (JSONObject) cityData.get( "main" );
				JSONObject wind = (JSONObject) cityData.get( "wind" );
				JSONArray weatherArr = (JSONArray) cityData.get( "weather" ); // Not sure why it's an array
				JSONObject weatherDesc = (JSONObject) weatherArr.get( 0 );
				
				Weather weather = new Weather(
						( (Number) main.get( "temp" ) ).floatValue(),
						( (Number) main.get( "temp_min" ) ).floatValue(),
						( (Number) main.get( "temp_max" ) ).floatValue(),
						( (Number) main.get( "humidity" ) ).floatValue(),
						( (Number) wind.get( "speed" ) ).floatValue(),
						(String) weatherDesc.get( "main" ) );
				
				this.data.put( cityName, weather );
			}
			
			loadedOK = true;
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		
		System.out.println( "Done!" );
	}
	
	/**
	 * Get a single static instance of this class.
	 * @return the WeatherData singleton
	 */
	public static WeatherService getData() {
		if ( WeatherService.dataInstance == null ) {
			synchronized ( WeatherService.class ) {
				if ( WeatherService.dataInstance == null ) {
					WeatherService.dataInstance = new WeatherService();
				}
			}
		}
		
		return WeatherService.dataInstance;
	}
	
	public boolean loadedOK() {
		return this.loadedOK;
	}
	
	public Set<String> getCities() {
		return this.data.keySet();
	}
	
	public Weather getWeather( String city ) {
		return this.data.get( city );
	}
}
