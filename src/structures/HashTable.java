package structures;

import java.util.ArrayList; 

public class HashTable {

    private ArrayList<LLNode<Integer, Integer>> bucketArray;  
    private Integer k; 
    private Integer size;
    private Integer capacity; 
  
    public HashTable(Integer c) { 
        bucketArray = new ArrayList<>(); 
        k = 10; 
        size = 0; 
        capacity = c;

        for (int i = 0; i < k; i++) 
            bucketArray.add(null); 
    } 
  
    private int hash(Integer i, Integer j) { 
    	int bn = getNumberOfBits(k);
    	int bw = getNumberOfBits(capacity);

        // System.out.println("[DEBUG] i = " + i);
        // System.out.println("[DEBUG] j = " + j);
   
    	String ri = "0" + Integer.toBinaryString(i);
        // System.out.println("[DEBUG] Ri = " + ri);
    	
        String rj = "0" + Integer.toBinaryString(j);
        // System.out.println("[DEBUG] Rj = " + rj);
    	
        Integer rij = Integer.parseInt("1" + ri + rj);
        // System.out.println("[DEBUG] Rij = " + rij);

        return rij % k;
    } 

    private int getNumberOfBits(int n) {
    	return (int) (Math.log(n) / Math.log(2) + 1); 
    }
  
    public Integer get(Integer key, Integer value) { 
        key = key == null ? 0 : key;
        value = value == null ? 0 : value; 
        System.out.println("[DEBUG] calling get(" + key + ", " + value + ")");

        int bucketIndex = hash(key, value); 
        System.out.println("[DEBUG] index = " + bucketIndex);
        LLNode<Integer, Integer> head = bucketArray.get(bucketIndex); 
  
        while (head != null) { 
            if (head.key != null && head.key.equals(key)) 
                return head.value; 
            head = head.next; 
        } 
        return null; 
    } 
  
    public void add(Integer key, Integer value) {
        key = key == null ? 0 : key;
        value = value == null ? 0 : value; 
        System.out.println("[DEBUG] calling add(" + key + ", " + value + ")");

        int bucketIndex = hash(key, value); 
        System.out.println("[DEBUG] index = " + bucketIndex);
        LLNode<Integer, Integer> head = bucketArray.get(bucketIndex); 
  
        while (head != null) { 
            if (head.key != null && head.key.equals(key)) { 
                head.value = value; 
                return; 
            } 
            head = head.next; 
        } 
  
        size++; 
        head = bucketArray.get(bucketIndex); 
        LLNode<Integer, Integer> newNode = new LLNode<Integer, Integer>(key, value); 
        newNode.next = head; 
        bucketArray.set(bucketIndex, newNode); 
  		
  		// adjust k when it reaches load factor
        if ((1.0 * size) / k >= 0.7) { 
            ArrayList<LLNode<Integer, Integer>> temp = bucketArray; 
            bucketArray = new ArrayList<>(); 
            k *= 2; 
            size = 0; 
            for (int i = 0; i < k; i++) 
                bucketArray.add(null); 
  
            for (LLNode<Integer, Integer> headNode : temp) { 
                while (headNode != null) { 
                    add(headNode.key, headNode.value); 
                    headNode = headNode.next; 
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
	    
	    public LLNode(K key, V value) { 
	        key = key; 
	        value = value; 
    	} 
	} 
  
}