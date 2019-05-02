package modes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.lang.IllegalArgumentException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class KnapsackMode {

	public void run() {
		Integer capacity = getCapacity();
		List<Integer> weights = getWeights();
		List<Integer> values = getValues();

		System.out.println("Knapsack capacity = " + capacity + ". Total number of items = " + values.size() + "\n");
		System.out.println("Values = " + values);
		System.out.println("Weights = " + weights);
		System.exit(0);
	}

	private Integer getCapacity() {
	 	return getValuesFromFile("capacity").get(0);
	}

	private List<Integer> getWeights() {
		return getValuesFromFile("weights");
	}

	private List<Integer> getValues() {
		return getValuesFromFile("values");
	}

	private List<Integer> getValuesFromFile(String var) {
		Scanner scanner = new Scanner(System.in);
		BufferedReader reader = null;
		List<Integer> result = new ArrayList<Integer>();
		while (true) {
			try {
				System.out.print("Enter file containing the " + var + ": ");
				String fileName = "../" + scanner.next();
				File file = new File(fileName);
				reader = new BufferedReader(new FileReader(file));
				String text;
				while ((text = reader.readLine()) != null) {
					text = text.replaceAll("\t", "");
					text = text.replaceAll("\n", "");
					text = text.replaceAll(" ", "");
			        result.add(Integer.parseInt(text));
			    }
			    break;
			} catch (FileNotFoundException e) {
			    System.out.println("[ERROR]: file not found.");
			} catch (IOException e) {
			    System.out.println("Error: IOException.");
			} finally {
			    try {
			        if (reader != null) {
			            reader.close();
			        }
			    } catch (IOException e) {}
			}
		}
		return result;
	}
}