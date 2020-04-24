package lab1.model;

import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<E> {
	private ArrayList<E> heap;
	private final Comparator<? super E> comparator;

	public MinHeap(int initialCapacity,
			Comparator<? super E> comparator) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException();

		this.heap = new ArrayList<E>(initialCapacity);
		this.comparator = comparator;
	}

	public MinHeap(Comparator<? super E> comparator) {
		this(1, comparator);
	}
	
	//O(log n)
	public void insert(E element) { 
		heap.add(element); 
		for(int i = heap.size()/2; i>=0; --i) 
			minHeapify(i);
	} 

	//O(log n)
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
	
	public void decreaseKey(int pos, E key) {
		if(compare(heap.get(pos), key) < 0) 
			throw new IllegalArgumentException("new key is greater than current key");
		heap.set(pos, key);
		while(pos > 0 && compare(heap.get(parent(pos)), heap.get(pos)) > 0) {
			swap(pos, parent(pos));
			pos = parent(pos);
		}
	}
	
	//O(log n)
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
	
	//O(log n)
	public Boolean remove(E item) {
		int index = heap.indexOf(item);
		if(index == -1)
			return false;
		extract(index);
		return true;
	}

	public E min() {
		return this.heap.get(0);
	}

	public int size() {
		return this.heap.size();
	}

	public Boolean isEmpty() {
		return this.heap.size() == 0;
	}
	
	private int parent(int pos) { 
		if (pos % 2 == 1) 
            return pos / 2;
        return (pos - 1) / 2;
	} 

	private int leftChild(int pos) { 
		return (2 * pos) + 1; 
	} 

	private int rightChild(int pos) { 
		return (2 * pos) + 2; 
	} 

	private boolean isLeaf(int pos) { 
		int size = this.heap.size();
		if (pos >= (size / 2) && pos <= size) 
			return true; 
		return false; 
	} 
	
	private void swap(int fpos, int spos) { 
		E tmp = heap.get(fpos); 
		heap.set(fpos, heap.get(spos)); 
		heap.set(spos, tmp);
	} 

	//O(log n)
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
	
	//Check if it's a min heap or not.
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
		Comparable<? super E> key2 = (Comparable<? super E>) o1;
		return key1.compareTo((E) key2);	
	}
}


