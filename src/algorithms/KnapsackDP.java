package algorithms;

import structures.HashTable;
import java.util.Set; 
import java.util.List; 
import java.util.Arrays; 

public class KnapsackDP {

	private int max(int a, int b) { return (a > b) ? a : b; } 

	public Integer compute(int capacity, List<Integer> values, List<Integer> weights, Set<Integer> optimalSubset) {
	    int n = values.size() + 1;
	    int cap = capacity + 1;
	    int[][] table = new int[n][cap]; 
	    // System.out.println("[DEBUG] capcity = " + capacity + ", cap = " + cap);

	    for (int item = 1; item < n; item++) {
	        for (int weight = 1; weight < cap; weight++) {
	            if (weights.get(item - 1) <= weight) {
	            	// System.out.println("[DEBUG11] item = " + item + ", weight = " + weight);

	            	// System.out.println("[DEBUG11] weight = " + weight + ", weights.get(item-1) = " + weights.get(item-1));
	            	// System.out.println("[DEBUG11] weightDiff = " + (weight - weights.get(item-1)) + ", table[item-1][weightDiff] = " + table[item - 1][weight - weights.get(item-1)]);
	                
	            	// System.out.println("[DEBUG11] tableEntry1 = " + table[item - 1][weight] + ", tableEntry2 = " + table[item - 1][weight - weights.get(item-1)]);
	            	// System.out.println("[DEBUG11] values.get(item - 1) = " + values.get(item - 1) + ", max = " + max(table[item - 1][weight], values.get(item - 1) + table[item - 1][weight - weights.get(item-1)]));

	                table[item][weight] = max(table[item - 1][weight], 
	                	values.get(item - 1) + table[item - 1][weight - weights.get(item-1)]);
	            }
	            else {
	            	// System.out.println("[DEBUG12] table[item][weight] = " + table[item][weight] + ", table[item - 1][weight] = " + table[item - 1][weight]);
	            	// System.out.println("[DEBUG12] weight = " + weight + ", weights.get(item-1) = " + weights.get(item-1));
	                // System.out.println("[DEBUG12] item = " + item + ", weight = " + weight + ", tableEntry1 = " + table[item - 1][weight]);

	                table[item][weight] = table[item - 1][weight];
	            }
	        }
	    }

	    // System.out.println(Arrays.deepToString(table).replace("], ", "]\n"));

	    int currentItem = n - 1;
	    int currentWeight = capacity;
	    while(currentItem > 0) {
	        if(table[currentItem][currentWeight] > table[currentItem - 1][currentWeight]) {
	            optimalSubset.add(currentItem);
	            currentWeight -= weights.get(currentItem - 1);
	            currentItem--;
	        }
	        else {
	            currentItem--;
	        }
	    }

	    return table[n - 1][capacity];
	}

	public Integer MFKnapsack(int i, int j, HashTable table, List<Integer> weights, List<Integer> values) {
		if (i < 1) return 0;
		Integer current = table.get(i,j);

		if (current == 0) {
			Integer value = 0;
			if (j < weights.get(i-1)) {
				value = MFKnapsack(i - 1, j, table, weights, values);
			} else {
				value = max(MFKnapsack(i - 1, j, table, weights, values), values.get(i - 1) + MFKnapsack(i - 1, j - weights.get(i - 1), table, weights, values));
			}
			table.add(i, j, value);
		}
		return current;
	}

	public Integer computeWithHash(int capacity, List<Integer> values, List<Integer> weights, Set<Integer> optimalSubset, Integer k) {
	    int n = values.size() + 1;
	    int cap = capacity + 1;
	    // System.out.println("[DEBUG] capcity = " + capacity + ", cap = " + cap);
	    HashTable table = new HashTable(capacity, k);

	    Integer result = 0;

	    for (int item = 1; item < n; item++) {
	        for (int weight = 1; weight < cap; weight++) {
	        	result = MFKnapsack(item, weight, table, weights, values);
	        }
	    }

	    int currentItem = n - 1;
	    int currentWeight = capacity;
	    while(currentItem > 0) {
	    	try {
	    		Integer entry1 = table.get(currentItem, currentWeight);
	 			Integer entry2 = table.get((currentItem - 1), currentWeight);
		        if (entry1 > entry2) {		        	
		            optimalSubset.add(currentItem);
		            currentWeight -= weights.get(currentItem - 1);
		            currentItem--;
		        }
		        else {
		            currentItem--;
		        }
		    } catch(java.lang.NumberFormatException e) {
		    	System.out.println("[ERROR] currentItem = " + currentItem + ", currentWeight = " + currentWeight + ", entry1 = " + table.get(currentItem, currentWeight));
		    	break;
		    }
	    }

	    return table.get(n-1, capacity);
	}
}