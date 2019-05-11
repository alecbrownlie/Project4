package algorithms;

import structures.KnapsackItem;
import structures.HashTable;
import structures.MaxHeap;
import java.util.Set; 
import java.util.List; 
import java.util.Collections;
import java.util.Comparator; 
import java.util.ArrayList;
import java.util.PriorityQueue;

public class KnapsackGreedy {

	public Integer compute(int capacity, List<Integer> values, List<Integer> weights, Set<Integer> optimalSubset) {
		Integer optimalValue = 0;
		List<KnapsackItem> sack = new ArrayList<KnapsackItem>();
		for (int i = 0; i < values.size(); i++) {
			sack.add(new KnapsackItem(i, values.get(i), weights.get(i)));
		}

		Collections.sort(sack, new Comparator<KnapsackItem>() {
			@Override
			public int compare(KnapsackItem item1, KnapsackItem item2) { 
				return item2.cost.compareTo(item1.cost) ; 
			} 
		});

		for (KnapsackItem item : sack) {
			if (capacity - item.weight >= 0) {
				optimalSubset.add(item.index + 1);
				capacity -= item.weight;
				optimalValue += item.value;
			} else {
				int ratio = capacity / item.weight;
				int value = item.value * ratio;
				optimalValue += value;
				break;
			}
		}
		return optimalValue;
	}

	public Integer computeWithHeap(int capacity, List<Integer> values, List<Integer> weights, Set<Integer> optimalSubset) {
		Integer optimalValue = 0;
		MaxHeap sack = new MaxHeap(values.size());

		for (int i = 0; i < values.size(); i++) {
			sack.insert(new KnapsackItem(i, values.get(i), weights.get(i)));
		}

		while (capacity > 0) {
			KnapsackItem item = sack.deleteMax();
			if (capacity - item.weight >= 0) {
				optimalSubset.add(item.index + 1);
				optimalValue += item.value;
				capacity -= item.weight;
			} else {
				break;
			}
		}
		return optimalValue;
	}
}