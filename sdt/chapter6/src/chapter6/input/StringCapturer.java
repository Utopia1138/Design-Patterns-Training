package chapter6.input;

import java.util.Scanner;

public class StringCapturer {

	public StringCapturer(Scanner scanner) {
		this.scanner = scanner;
	}

	private Scanner scanner;
	
	public String captureString(){
		return scanner.next();		
	}
	
}
