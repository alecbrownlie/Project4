package structures;
 
public class KnapsackItem {
	public final int index;
	public final int value;
	public final int weight;

	public final Double cost;

	public KnapsackItem(int i, int v, int w) {
		index = i;
		value = v;
		weight = w;
		cost = new Double((double)value / (double)weight);
	}

	public int compareTo(KnapsackItem item) { 
		return this.cost.compareTo(item.cost) ; 
	} 
}