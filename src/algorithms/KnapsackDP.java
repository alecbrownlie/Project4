package algorithms;

import structures.HashTable;
import java.util.Set; 
import java.util.List; 

public class KnapsackDP {

	private int max(int a, int b) { return (a > b) ? a : b; } 

	public Integer compute(int capacity, List<Integer> values, List<Integer> weights, Set<Integer> optimalSubset) {
	    int n = values.size() + 1;
	    int cap = capacity + 1;
	    int[][] table = new int[n][cap]; 

	    for (int item = 1; item < n; item++) {
	        for (int weight = 1; weight < cap; weight++) {
	            if (weights.get(item - 1) <= weight) {
	                table[item][weight] = max(table[item - 1][weight], 
	                	values.get(item - 1) + table[item - 1][weight - weights.get(item-1)]);
	            }
	            else {
	                table[item][weight] = table[item - 1][weight];
	            }
	        }
	    }
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

	public Integer computeWithHash(int capacity, List<Integer> values, List<Integer> weights, Set<Integer> optimalSubset) {
	    int n = values.size() + 1;
	    int cap = capacity + 1;
	    System.out.println("[DEBUG] capcity = " + capacity + ", cap = " + cap);
	    HashTable table = new HashTable(capacity);

	    for (int item = 1; item < n; item++) {
	        for (int weight = 1; weight < cap; weight++) {
	            if (weights.get(item - 1) <= weight) {
	            	System.out.println("[DEBUG] item = " + item + ", weight = " + weight);
	                table.add(item, max(table.get(item - 1, weight), 
	                	values.get(item - 1) + table.get(item - 1, weight - weights.get(item-1))));
	            }
	            else {
	            	System.out.println("[DEBUG] item = " + item + ", weight = " + weight);
	                table.add(item, table.get(item - 1, weight));
	            }
	        }
	    }

	    int currentItem = n - 1;
	    int currentWeight = capacity;
	    while(currentItem > 0) {
	        if(table.get(currentItem, currentWeight) > table.get(currentItem - 1, currentWeight)) {
	            optimalSubset.add(currentItem);
	            currentWeight -= weights.get(currentItem - 1);
	            currentItem--;
	        }
	        else {
	            currentItem--;
	        }
	    }

	    return table.get(n - 1, capacity);
	}
}