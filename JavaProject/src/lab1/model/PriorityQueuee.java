package lab1.model;

import java.util.ArrayList;

public class PriorityQueuee<T extends Comparable<T>> {
    private ArrayList<T> Queue = new ArrayList<T>();

    public PriorityQueuee(){
        super();
    }

    public PriorityQueuee(ArrayList<T> q){
        for(T obj : q)
            insert(obj);
    }

    private static int parent(int pos){ 
        return (pos - 1) / 2; 
    } 
    
    private static int leftChild(int pos){ 
        return (2 * pos) + 1; 
    } 
    
    private static int rightChild(int pos){ 
        return (2 * pos) + 2; 
    }

    private boolean isLeaf(int pos) {
        if (pos >= (Queue.size() / 2) && pos < Queue.size())
            return true;
        return false; 
    }

    private void swap(int fpos, int spos){ 
        T tmp = Queue.get(fpos); 
        Queue.set(fpos, Queue.get(spos)); 
        Queue.set(spos, tmp); 
    }

    private void minHeapify(int pos){
        if (!isLeaf(pos)) {
            if (rightChild(pos) < Queue.size()){
                    if(Queue.get(pos).compareTo(Queue.get(leftChild(pos))) == 1 
                    || Queue.get(pos).compareTo(Queue.get(rightChild(pos))) == 1) {
                        if (Queue.get(leftChild(pos)).compareTo(Queue.get(rightChild(pos))) == -1) { 
                            swap(pos, leftChild(pos)); 
                            minHeapify(leftChild(pos)); 
                        }
                        else { 
                            swap(pos, rightChild(pos)); 
                            minHeapify(rightChild(pos)); 
                        }
                    } 
            }
            else if(Queue.get(pos).compareTo(Queue.get(leftChild(pos))) == 1)
                swap(pos, leftChild(pos));
        } 
    }

    private Integer findIndexCore(T obj, int i){
        if(i >= Queue.size())
            return null;
        if(Queue.get(i) != null && Queue.get(i).equals(obj))
            return i;
        Integer a = findIndexCore(obj, leftChild(i));
        Integer b = findIndexCore(obj, rightChild(i));
        if(a != null)
            return a;
        return b;
    }

    private Integer findIndex(T obj){
        return findIndexCore(obj, 0);
    }

    public void insert(T element) {
        if(Queue.isEmpty()){
            Queue.add(element);
            return;
        }
        Queue.add(element);
        int current = Queue.size() - 1;
    
        while (current != 0 && Queue.get(current).compareTo(Queue.get(parent(current))) == -1) { 
            swap(current, parent(current)); 
            current = parent(current);
        } 
    }

    public void remove(T element){
        if(Queue.isEmpty())
            return;
        Integer pos = findIndex(element);
        if(pos == null)
            return;
        int size  = Queue.size() - 1;
        if(pos != size)
            swap(pos, size);
        Queue.remove(size);
        if(Queue.size() != 0 && pos != size)
            minHeapify(pos);
    }

    public T pop(){
        int size  = Queue.size() - 1;
        if(size != 0)
            swap(0, size);
        T popped = Queue.remove(size); //rimuovendo l'ultimo elemento è O(1)
        if(Queue.size() != 0)
            minHeapify(0);
        return popped;
    }

    public boolean isEmpty(){
        return Queue.isEmpty();
    }

    public int size(){
        return Queue.size();
    }

    public void print() { 
		for (int i = 0; i < Queue.size() / 2; i++) { 
			System.out.print(" PARENT: " + Queue.get(i) 
                            + " LEFT CHILD: " + Queue.get((i * 2)+1));
            if((i * 2)+2 < Queue.size()){
                System.out.print(" RIGHT CHILD: " + Queue.get((i * 2)+2));            }	 
			System.out.println(); 
		} 
    }
}