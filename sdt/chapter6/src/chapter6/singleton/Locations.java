package chapter6.singleton;

import java.util.ArrayList;
import java.util.List;

import chapter6.Country;
import chapter6.Location;

public class Locations {
	private static List<Location> locations = new ArrayList<>();
	
	private Locations(){
		 // Singleton;
	}
	
	public static List<Location> getLocations(){
		return locations;
	}
	
	public static void addLocation(Location location){
		locations.add(location);
	}

	public static List<Location> getLocationsOwnedByCountry(Country country) {
		List<Location> locationsOwnedbyCountry = new ArrayList<Location>();
		for (Location location : locations) {
			if (location.getOwnedBy() != null && location.getOwnedBy() == country) {
				locationsOwnedbyCountry.add(location);
			}
		}
		return locationsOwnedbyCountry;
	}
	
	public static boolean isAllLocationsOwnedByOneCountry(){
		
		Country lastOwnedBy = null;
		
		for(Location l : locations){
			if( lastOwnedBy != null && lastOwnedBy != l.getOwnedBy()){
				return false;
			}
			lastOwnedBy = l.getOwnedBy();
		}
		return true;
		
	}
}
