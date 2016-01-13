package com.clearscene.Decorator;

import com.clearscene.Decorator.People.Male;
import com.clearscene.Decorator.People.Female;
import com.clearscene.Decorator.People.Person;
import com.clearscene.Decorator.People.Modifiers.Developer;
import com.clearscene.Decorator.People.Modifiers.Drinker;
import com.clearscene.Decorator.People.Modifiers.JunkFood;
import com.clearscene.Decorator.People.Modifiers.Runner;
import com.clearscene.Decorator.People.Modifiers.Smoker;

import java.util.Scanner;

public class DeathClock {

	public static void main( String[] args ) {
		
		Person subject_x;
		
		System.out.println("Legal: For fun only"); 
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Enter your current age: ");
		int age = Integer.parseInt( reader.nextLine() );
		
		System.out.println("Enter your gender (m|f): ");
		if( "m".equals( reader.nextLine() ) ) { 
			subject_x = new Male( age );
		}
		else {
			subject_x = new Female( age );
		}

		System.out.println("Are you a runner (y|n): ");
		if( "y".equals( reader.nextLine() ) ) { subject_x = new Runner( subject_x ); }
		
		System.out.println("Are you a developer (y|n): ");
		if( "y".equals( reader.nextLine() ) ) { subject_x = new Developer( subject_x ); }
		
		System.out.println("Do you eat junk food (y|n): ");
		if( "y".equals( reader.nextLine() ) ) { subject_x = new JunkFood( subject_x ); }
		
		System.out.println("Do you take a drink (y|n): ");
		if( "y".equals( reader.nextLine() ) ) { subject_x = new Drinker( subject_x ); }
		
		System.out.println("Are you a smoker (y|n): ");
		if( "y".equals( reader.nextLine() ) ) { subject_x = new Smoker( subject_x ); }
		
		int years = subject_x.getDeathDate();
		if( years > 20 ) {
			System.out.println( "Youth is waisted on the young. You have " + years + " years of good life to go." );
		}
		else if ( years > 10 ) {
			System.out.println( "There is still time to enjoy your retierment. You have " + years + " years of life to go." );
		}
		else if ( years > 1 ) {
			System.out.println( "Time to get your affairs in order. You have " + years + " years to say your goodbies." );
		}
		else if ( years == 0 ) {
			System.out.println( "Time is up! Check your palm for signs of flashing." );
		}
		else {
			System.out.println( "The artful dodger. You have overstayed your welcome by " + Math.abs(years) + " years." );
		}
	}
	
}
