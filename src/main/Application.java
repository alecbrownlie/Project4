package main;

import java.util.Scanner;
import modes.*;
import structures.*;

public class Application {
	private static String PROJECT_BANNER = "----- Project 04: Knapsack -----";
	private static String INSTRUCTIONS = "Please select task:";
	private static String WELCOME_MSG = "      Welcome! " + INSTRUCTIONS;
	private static String KNAPSACK_MODE = "Knapsack Solutions";
	private static String COMPARISON_MODE = "Knapsack Efficiency Graphs";

	private static KnapsackMode knapsackMode = new KnapsackMode();
	private static ComparisonMode comparisonMode = new ComparisonMode();

	public static void main(String[] args) {
		printMenu();
		processUserInput();
	}

	private static void printMenu() {
		System.out.println(PROJECT_BANNER);
		System.out.println(WELCOME_MSG);
	}

	private static void printChoices() {
		System.out.println("1)    " + KNAPSACK_MODE);
		System.out.println("2)    " + COMPARISON_MODE);
	}

	private static void processUserInput() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				printChoices();
				switch (Integer.parseInt(scanner.nextLine())) {
					case 1:
						System.out.println("Selected: " + KNAPSACK_MODE + "\n");
						knapsackMode.run();
						break;
					case 2: 
						System.out.println("Selected: " + COMPARISON_MODE + "\n");
						comparisonMode.run();
						break;
					default:
						System.out.println("Not a choice. " + INSTRUCTIONS);
				}	
			} catch (java.lang.NumberFormatException e) {
				System.out.println("Not a number. " + INSTRUCTIONS);
			}
		}
	}
}