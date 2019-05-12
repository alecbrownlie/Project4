package structures;

import java.util.ArrayList; 

public class HashTable {

    private ArrayList<LLNode<Integer, Integer>> bucketArray;  
    private Integer k; 
    private Integer size;
    private Integer capacity; 
  
    public HashTable(Integer c, Integer ksize) { 
        bucketArray = new ArrayList<>(); 
        k = (ksize == null || ksize == 0) ? 10 : ksize;
        size = 0; 
        capacity = c;

        for (int i = 0; i < k; i++) 
            bucketArray.add(null); 
    } 
  
    public Integer hash(Integer i, Integer j) { 
    	int bn = getNumberOfBits(k);
    	int bw = getNumberOfBits(capacity);

        // System.out.println("[DEBUG] i = " + i);
        // System.out.println("[DEBUG] j = " + j);
   
    	String ri = "0" + Integer.toBinaryString(i);
        // System.out.println("[DEBUG] Ri = " + ri);
    	
        String rj = "0" + Integer.toBinaryString(j);
        // System.out.println("[DEBUG] Rj = " + rj);
    	
        String rij = ("1" + (ri + rj));
        // System.out.println("[DEBUG] Rij = " + rij);

        Integer r = Integer.parseInt(rij, 2);
        // System.out.println("[DEBUG] r = " + rij);
        Integer result = r % k;
        // System.out.println("[DEBUG] result = " + rij);

        return result;
    } 

    private int getNumberOfBits(int n) {
    	return (int) (Math.log(n) / Math.log(2) + 1); 
    }
  
    public Integer get(Integer i, Integer j) { 

        int bucketIndex = hash(i, j); 
        LLNode<Integer, Integer> node = bucketArray.get(bucketIndex); 
  
        while (node != null && node.key != null) {
            if (node.key.equals(i)) 
                return node.value; 
            node = node.next; 
        } 
        return 0; 
    } 
  
    public void add(Integer i, Integer j, Integer value) {
        // System.out.println("[DEBUG] calling add(" + key + ", " + value + ")");
        if (value != null) {
            int bucketIndex = hash(i, j); 
            // System.out.println("[DEBUG] index = " + bucketIndex);
            LLNode<Integer, Integer> head = bucketArray.get(bucketIndex); 

            while (head != null && head.key != null) { 
                if (head.key.equals(i)) { 
                    head.value = value; 
                    return; 
                } 
                head = head.next; 
            } 
      
            size++; 
            head = bucketArray.get(bucketIndex); 

            // System.out.println("[DEBUG] key = " + i + ", value = " + j + ", bucketIndex = " + bucketIndex );
            LLNode<Integer, Integer> newNode = new LLNode<Integer, Integer>(i, value);
            // System.out.println("[DEBUG] newNode.key =" + String.valueOf(newNode.key) + ", newNode.value = " + String.valueOf(newNode.value));
 
            newNode.next = head; 
            bucketArray.set(bucketIndex, newNode); 
      		
      		// adjust k when it reaches load factor
            // if ((1.0 * size) / k >= 0.7) { 
            //     ArrayList<LLNode<Integer, Integer>> temp = bucketArray; 
            //     bucketArray = new ArrayList<>(); 
            //     k *= 2; 
            //     size = 0; 
            //     for (int i = 0; i < k; i++) 
            //         bucketArray.add(null); 
      
            //     for (LLNode<Integer, Integer> headNode : temp) { 
            //         while (headNode != null) { 
            //             add(headNode.key, headNode.value); 
            //             headNode = headNode.next; 
            //         } 
            //     } 
            // } 
        }
    } 

    public void print() {
        System.out.println("----- Printing HashTable -----");
        for (int i = 0; i < k; i++) {
            LLNode<Integer, Integer> node = bucketArray.get(i);
            if (node != null) {
                String nodeKey = node.key == null ? "null" : String.valueOf(node.key);
                String nodeValue = node.value == null ? "null" : String.valueOf(node.value);
                
                System.out.println("bucketArray[" + i + "] key = " + nodeKey + ", value = " + nodeValue);
                while (node.next != null) {
                    System.out.println("next -> key = " + node.next.key + ", value = " + node.next.value);
                    node = node.next;
                }
            }
        }
    }

    public int size() { return size; }
     
    public boolean isEmpty() { return size() == 0; } 

    class LLNode<K, V> { 
	    K key; 
	    V value; 
	  
	    LLNode<K, V> next; 
	    
	    public LLNode(K k, V v) { 
	        key = k; 
	        value = v; 
    	} 
	} 
  
}