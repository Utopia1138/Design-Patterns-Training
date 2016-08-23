package com.spg.appendix;

/**
 * The underlying cause of the problem
 *
 */
public enum Cause {

	UNKNOWN, // Default state until it is investigated
	CODE, // Bad code 
	CONFIG, // Support set up the user incorrectly
	REQUIREMENT, // The user expects some functionality that was not properly communicated/implemented
	PEBKAC; // Self-explanatory
	
}
