package structures;

public class MaxHeap {
	private KnapsackItem[] heap;
	public int size;

	public MaxHeap(int n) {
		size = 0;
		heap = new KnapsackItem[n + 1];
	}

	public void insert(KnapsackItem element) {
		int current = ++size;

		while (current != 1 && heap[current / 2].compareTo(element) < 0) {
	        heap[current] = heap[current / 2]; 
	        current /= 2;                          
	    }
	   
	    heap[current] = element;
	}

	public KnapsackItem top() {
		return heap[1];
	}

	public KnapsackItem deleteMax() { 
		if (size == 0) 
			return null;

		KnapsackItem max = heap[1];
		KnapsackItem lastElement = heap[size--];
		int current = 1;
		int child = 2;
		while (child <= size) {
			if (child < size && heap[child].compareTo(heap[child + 1]) < 0) 
				child++;
			if (lastElement.compareTo(heap[child]) >= 0)
				break;
			heap[current] = heap[child];
			current = child;
			child *= 2;
		}
        heap[current] = lastElement;
        return max; 
    } 
}