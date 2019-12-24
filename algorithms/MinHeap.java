package algorithms;

import dataStructure.Node;
import dataStructure.node_data;

public class MinHeap { 
    private node_data[] Heap; 
    private int size; 
    private int maxsize; 
  
    private static final int FRONT = 0; 
  
    public MinHeap(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = 0; 
        Heap = new node_data[this.maxsize + 1]; 
    } 
  
    // Function to return the position of 
    // the parent for the node currently 
    // at pos 
    private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Function to return the position of the 
    // left child for the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Function to return the position of 
    // the right child for the node currently 
    // at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Function that returns true if the passed 
    // node is a leaf node 
    private boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
  
    // Function to swap two nodes of the heap 
    private void swap(int fpos, int spos) 
    { 
        node_data tmp; 
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
  
    // Function to heapify the node at pos 
    private void minHeapify(int pos) 
    { 
  
        // If the node is a non-leaf node and greater 
        // than any of its child 
        if (!isLeaf(pos)) { 
            if (Heap[pos].getWeight() > Heap[leftChild(pos)].getWeight() 
                || Heap[pos].getWeight() > Heap[rightChild(pos)].getWeight()) { 
  
                // Swap with the left child and heapify 
                // the left child 
                if (Heap[leftChild(pos)].getWeight() < Heap[rightChild(pos)].getWeight()) { 
                    swap(pos, leftChild(pos)); 
                    minHeapify(leftChild(pos)); 
                } 
  
                // Swap with the right child and heapify 
                // the right child 
                else { 
                    swap(pos, rightChild(pos)); 
                    minHeapify(rightChild(pos)); 
                } 
            } 
        } 
    }
    
    public Node getMin() {
    	return (Node) Heap[0];
    }
  
    // Function to insert a node into the heap 
    public void insert(node_data element) 
    { 
        if (size >= maxsize) { 
            return; 
        } 
        
        Heap[size++] = element; 
        int current = size-1; 
  
        while (Heap[current].getWeight() < Heap[parent(current)].getWeight()) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 
  
    // Function to print the contents of the heap 
    public void print() 
    { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + Heap[i] 
                             + " LEFT CHILD : " + Heap[2 * i] 
                             + " RIGHT CHILD :" + Heap[2 * i + 1]); 
            System.out.println(); 
        } 
    } 
  
    // Function to build the min heap using 
    // the minHeapify 
    public void minHeap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            minHeapify(pos); 
        } 
    } 
  
    // Function to remove and return the minimum 
    // element from the heap 
    public node_data remove() 
    { 
        node_data popped = Heap[FRONT]; 
        Heap[FRONT] = Heap[--size];
        minHeapify(FRONT); 
        return popped; 
    }

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	} 
    
    
}