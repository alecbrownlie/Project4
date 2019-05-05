package modes;

import algorithms.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.lang.IllegalArgumentException;
import java.util.*; 

public class KnapsackMode {
	private KnapsackDP knapsackDP = new KnapsackDP();
	private KnapsackGreedy knapsackGreedy = new KnapsackGreedy();

	public void run() {
		Integer capacity = getCapacity();
		List<Integer> weights = getWeights();
		List<Integer> values = getValues();
		System.out.println("\nKnapsack capacity = " + capacity + ". Total number of items = " + values.size() + "\n");
		runTask1A(capacity, values, weights);
		// runTask1B(capacity, values, weights);
		runTask2A(capacity, values, weights);
		runTask2B(capacity, values, weights);
		System.exit(0);
	}

	private void runTask1A(Integer capacity, List<Integer> values, List<Integer> weights) {
		Set<Integer> optimalSubset = new HashSet<Integer>();

		long startTime = System.nanoTime();
		Integer optimalValue = knapsackDP.compute(capacity, values, weights, optimalSubset);
		long endTime = System.nanoTime();

		System.out.println("Traditional Dynamic Programming Optimal value: " + optimalValue);
		System.out.println("Traditional Dynamic Programming Optimal subset: " + optimalSubset.toString().replace("[","{").replace("]", "}").trim());
		System.out.println("Traditional Dynamic Programming Time Taken: " + (endTime - startTime) + "\n");
	}

	private void runTask1B(Integer capacity, List<Integer> values, List<Integer> weights) {
		Set<Integer> optimalSubset = new HashSet<Integer>();

		long startTime = System.nanoTime();
		Integer optimalValue = knapsackDP.computeWithHash(capacity, values, weights, optimalSubset);
		long endTime = System.nanoTime();

		System.out.println("Space-efficient Dynamic Programming Optimal value: " + optimalValue);
		System.out.println("Space-efficient Dynamic Programming Optimal subset: " + optimalSubset.toString().replace("[","{").replace("]", "}").trim());
		System.out.println("Space-efficient Dynamic Programming Time Taken: " + (endTime - startTime) + "\n");
	}

	private void runTask2A(Integer capacity, List<Integer> values, List<Integer> weights) {
		Set<Integer> optimalSubset = new HashSet<Integer>();

		long startTime = System.nanoTime();
		Integer optimalValue = knapsackGreedy.compute(capacity, values, weights, optimalSubset);
		long endTime = System.nanoTime();

		System.out.println("Greedy Approach Optimal value: " + optimalValue);
		System.out.println("Greedy Approach Optimal subset: " + optimalSubset.toString().replace("[","{").replace("]", "}").trim());
		System.out.println("Greedy Approach Time Taken: " + (endTime - startTime) + "\n");
	}

	private void runTask2B(Integer capacity, List<Integer> values, List<Integer> weights) {
		Set<Integer> optimalSubset = new HashSet<Integer>();

		long startTime = System.nanoTime();
		Integer optimalValue = knapsackGreedy.computeWithHeap(capacity, values, weights, optimalSubset);
		long endTime = System.nanoTime();

		System.out.println("Heap-based Greedy Approach Optimal value: " + optimalValue);
		System.out.println("Heap-based Greedy Approach Optimal subset: " + optimalSubset.toString().replace("[", "{").replace("]", "}").trim());
		System.out.println("Heap-based Greedy Approach Time Taken: " + (endTime - startTime) + "\n");
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