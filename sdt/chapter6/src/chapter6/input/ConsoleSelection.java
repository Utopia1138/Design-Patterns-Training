package chapter6.input;

import java.util.List;
import java.util.Scanner;

public class ConsoleSelection<E> {

	public ConsoleSelection(Scanner scanner) {
		this.scanner = scanner;
	}

	private Scanner scanner;
	
	
	public E selectObjectFromList(List<E> input) {

		for (int i = 1; i <= input.size(); i++) {
			System.out.println("[" + i + "] - " + input.get(i-1));
		}

		int i = scanner.nextInt();
		return input.get(i-1);
	}

}
