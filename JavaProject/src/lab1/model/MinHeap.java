
package lab1.model;

import java.util.ArrayList;
import java.util.Comparator;
/**
 * The MinHeap structure is a container class that represents a minimum heap.
 */
public class MinHeap<E> {
	private ArrayList<E> heap;
	private final Comparator<? super E> comparator;

	/**
	 * constructor with fixed size and fixed comparator
	 * @param initialCapacity = the number of the nodes 
	 * @param comparator = the comparator class, if provided, will
	 * determine how to sort the items in minheap
	 */
	public MinHeap(int initialCapacity,
			Comparator<? super E> comparator) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException();
		this.heap = new ArrayList<E>(initialCapacity);
		this.comparator = comparator;
	}
	/**
	 * constructor without fixed size
	 * @param comparator = the comparator class, if provided, will
	 * determine how to sort the items in minheap
	 */
	public MinHeap(Comparator<? super E> comparator) {
		this(1, comparator);
	}
	/**
	 * constructor without fixed heap size and comparator.
	 * MinHeap will use function Comparable::compareTo of some super class 
	 * of E (if E is not provided) to sort the items
	 */
	public MinHeap(){
		this(1,null);
	}
	/**
	 * constructor with fixed heap size without comparator.
	 * @param initialCapacity = the number of the nodes 
	 * MinHeap will use function Comparable::compareTo of some super class 
	 * of E (if E is not provided) to sort the items
	 */
	public MinHeap(int size){
		this(size,null);
	}
	/**
	 * insert element and consequent sort of items in the heap.
	 * It uses minHeapify function with complexity O(logn)
	 * @param element = the element to insert.
	 */
	public void insert(E element) { 
		heap.add(element); 
		for(int i = heap.size()/2; i>=0; --i) 
			minHeapify(i);
	} 
	/**
	 * extract minimum element from the heap.
	 * complexity = o(log n)
	 * @return the root of the heap, that is the minimum element
	 */
	public E extractMin() {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Tried to extract an item from an empty MinHeap");
		if(heap.size() == 1) 
			return heap.remove(0);
		E min = heap.get(0);
		heap.set(0, heap.remove(heap.size() - 1));
		minHeapify(0);
		return min;
	}
	/**
	 * decrease a key value of an item in the heap and proceeds
	 * to sort it, to preserve the minheap property
	 * complexity = O(log n)
	 * @param pos = position of the item to modify
	 * @param key = new value 
	 */
	public void decreaseKey(int pos, E key) {
		if(compare(heap.get(pos), key) < 0) 
			throw new IllegalArgumentException("new key is greater than current key");
		heap.set(pos, key);
		while(pos > 0 && compare(heap.get(parent(pos)), heap.get(pos)) > 0) {
			swap(pos, parent(pos));
			pos = parent(pos);
		}
	}
	/**
	 * extract a node from the heap and it sort the new heap according
	 * to the new value
	 * complexity = O(log n)
	 * @param pos = the position of the node to extract
	 * @return the item extracted
	 * @throws IndexOutOfBoundsException if pos is out of heap's bounds
	 */
	public E extract(int pos) {
		int size = heap.size();
		if(pos >= heap.size())
			throw new IndexOutOfBoundsException();
		E item = heap.get(pos);
		if(pos != size - 1) {
			heap.set(pos, heap.remove(size - 1));
			minHeapify(pos);
			return item;
		}
		return heap.remove(pos);
	}
	/**
	 * removes an item without returning it and proceeds to sort the 
	 * new heap 	 * 
	 * complexity = O(log n)
	 * @param item = the item to remove
	 * @return true if the item was succesfully removed, false otherwise
	 */
	public Boolean remove(E item) {
		int index = heap.indexOf(item);
		if(index == -1)
			return false;
		extract(index);
		return true;
	}
	/**
	 * returns the minimum element without extracting it
	 * @return the value of the minimum element
	 */
	public E min() {
		return this.heap.get(0);
	}
	/**
	 * function to get the size of the minheap
	 * @return an integer representing the size of the heap
	 */
	public int size() {
		return this.heap.size();
	}
	/**
	 * @return true if the minheap is empty, false otherwise
	 */
	public Boolean isEmpty() {
		return this.heap.size() == 0;
	}
	/**
	 * @param pos = the position to determine the father
	 * @return the position of pos' father
	 */
	private int parent(int pos) { 
		if (pos % 2 == 1) 
            return pos / 2;
        return (pos - 1) / 2;
	} 
	/**
	 * @param pos = the position to determine the left child
	 * @return the position of pos' left child
	 */
	private int leftChild(int pos) { 
		return (2 * pos) + 1; 
	} 
	/**
	 * @param pos = the position to determine the right child
	 * @return the position of pos' right child
	 */
	private int rightChild(int pos) { 
		return (2 * pos) + 2; 
	} 
	/**
	 * @param pos = the position to determine if it's a leaf
	 * @return true if pos is leaf, false otherwise
	 */
	private boolean isLeaf(int pos) { 
		int size = this.heap.size();
		if (pos >= (size / 2) && pos <= size) 
			return true; 
		return false; 
	} 
	/**
	 * 
	 * @param fpos = first node to swap
	 * @param spos = second node to swap
	 */
	private void swap(int fpos, int spos) { 
		E tmp = heap.get(fpos); 
		heap.set(fpos, heap.get(spos)); 
		heap.set(spos, tmp);
	} 
	/**
	 * the key to mantaining the minheap property
	 * @param pos = the position to start the minheapify procedure
	 */
	private void minHeapify(int pos) { 
		if(isLeaf(pos))
			return;
		int left = leftChild(pos);
		int right = rightChild(pos);
		int minIndex;
		if(left < heap.size() && compare(heap.get(left), heap.get(pos)) < 0) 
			minIndex = left;
		else minIndex = pos;
		if(right < heap.size() && compare(heap.get(right), heap.get(minIndex)) < 0) 
			minIndex = right;
		if(minIndex != pos) {
			swap(pos, minIndex);
			minHeapify(minIndex);
		}
	} 	
	/**
	 * Check if it's a min heap or not. Only used in the testing of this data
	 * structure. 
	 * @param pos = the position to start the procedure
	 * @return true if it's a minheap, false otherwise
	 */
	public Boolean isMinHeap(int pos) {
		if(isLeaf(pos) || pos > this.heap.size()) 
			return true;
		int left = leftChild(pos);
		int right = rightChild(pos);
		
		if((left < heap.size() && compare(heap.get(pos), heap.get(left)) > 0)
				|| (right < heap.size() && compare(heap.get(pos), heap.get(right)) > 0)) 
			return false;
		
		return isMinHeap(left) && isMinHeap(right);
	}
	@SuppressWarnings("unchecked")
	private int compare(E o1, E o2) {
		if(comparator != null) 
			return comparator.compare(o1, o2);
		Comparable<? super E> key1 = (Comparable<? super E>) o1;
		Comparable<? super E> key2 = (Comparable<? super E>) o2;
		return  key1.compareTo((E) key2);	
	}
}


