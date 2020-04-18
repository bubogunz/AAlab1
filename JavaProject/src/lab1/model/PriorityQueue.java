package lab1.model;

import java.util.ArrayList;

public class PriorityQueue<T> {
    private ArrayList<Pair<T>> Queue = new ArrayList<Pair<T>>();

    public PriorityQueue(){
        super();
    }

    public PriorityQueue(ArrayList<Pair<T>> list) {
        super();
        for(Pair<T> pair : list){
            insert(pair);
        }
    }

    public ArrayList<Pair<T>> getQueue(){
        return Queue;
    }

    private int parent(int pos) 
    { 
        return (pos - 1) / 2; 
    } 
    
    private int leftChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
    
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 2; 
    } 
    
    private boolean isLeaf(int pos) 
    {
        if (pos >= (Queue.size() / 2) && pos < Queue.size()) { 
            return true; 
        } 
        return false; 
    } 
    
    private void swap(int fpos, int spos) 
    { 
        Pair<T> tmp = Queue.get(fpos); 
        Queue.set(fpos, Queue.get(spos)); 
        Queue.set(spos, tmp); 
    } 
    
    private void minHeapify(int pos) 
    {
        if (!isLeaf(pos)) {
            if (rightChild(pos) < Queue.size()){
                    if(Queue.get(pos).getKey() > Queue.get(leftChild(pos)).getKey() 
                    || Queue.get(pos).getKey() > Queue.get(rightChild(pos)).getKey()) {

                    if (Queue.get(leftChild(pos)).getKey() < Queue.get(rightChild(pos)).getKey()) { 
                        swap(pos, leftChild(pos)); 
                        minHeapify(leftChild(pos)); 
                    }
                    else { 
                        swap(pos, rightChild(pos)); 
                        minHeapify(rightChild(pos)); 
                    }
                } 
            }
            else if(Queue.get(pos).getKey() > Queue.get(leftChild(pos)).getKey())
                swap(pos, leftChild(pos));
        } 
    }

    //O(logn)
    private int indexOfObjCore(T obj, int i){
        if(i >= Queue.size()){
            return -1;
        }
        if(Queue.get(i).getObj().equals(obj)){
            return i;
        }
        int a = indexOfObjCore(obj, (i * 2)+1);
        int b = indexOfObjCore(obj, (i * 2)+2);
        if(a != -1){
            return a;
        }
        return b;
    }

    public int indexOfObj(T obj){
        return indexOfObjCore(obj, 0);
    }

    //O(logn)
    private int indexOfKeyCore(int k, int i){
        if(i >= Queue.size()){
            return -1;
        }
        if(Queue.get(i).getKey() == k){
            return i;
        }
        int a = indexOfKeyCore(k, (i * 2)+1);
        int b = indexOfKeyCore(k, (i * 2)+2);
        if(a != -1){
            return a;
        }
        return b;
    }

    public int indexOfKey(int k){
        return indexOfKeyCore(k, 0);
    }

    //O(logn)
    private Integer findKeyCore(T obj, int i){
        if(i >= Queue.size()){
            return null;
        }
        if(Queue.get(i).getObj() != null && Queue.get(i).getObj().equals(obj)){
            return Integer.valueOf(Queue.get(i).getKey());
        }
        Integer a = findKeyCore(obj, (i * 2)+1);
        Integer b = findKeyCore(obj, (i * 2)+2);
        if(a != null){
            return a;
        }
        return b;
    }

    public Integer findKey(T obj){
        return findKeyCore(obj, 0);
    }

    //O(logn)
    private T findObjCore(int key, int i){
        if(i >= Queue.size()){
            return null;
        }
        if(Queue.get(i).getKey() == key){
            return Queue.get(i).getObj();
        }
        T a = findObjCore(key, (i * 2)+1);
        T b = findObjCore(key, (i * 2)+2);
        if(a != null){
            return a;
        }
        return b;
    }

    public T findObj(int key){
        return findObjCore(key, 0);
    } 
    
    public void removeByKey(int k) {
        if(!isEmpty()){
            int i = indexOfKey(k);
            if(i == -1){
                return;
            }
            int size = Queue.size() - 1;
            if(i != size){
                swap(i, size);
            }
            Queue.remove(size);
            if(i != size){
                minHeapify(i);
            }
        }
    }

    public void removeByIndex(int i){
        if(!isEmpty()){
            if(i >= Queue.size()){
                return;
            }
            int size = Queue.size() - 1;
            if(i != size){
                swap(i, size);
            }
            Queue.remove(size);
            if(i != size){
                minHeapify(i);
            }
        }
    }
    
    public void setByKey(int old, Pair<T> n){
        removeByKey(old);
        insert(n);
    }

    public void setByIndex(int old, Pair<T> n){
        removeByIndex(old);
        insert(n);
    }
    
    public void insert(Pair<T> element) 
    {
        if(Queue.isEmpty()){
            Queue.add(element);
            return;
        }
        Queue.add(element);
        int current = Queue.size() - 1;
    
        while (current != 0 && Queue.get(current).getKey() < Queue.get(parent(current)).getKey()) { 
            swap(current, parent(current)); 
            current = parent(current);
        } 
    }

    public void print() 
	{ 
		for (int i = 0; i < Queue.size() / 2; i++) { 
			System.out.print(" PARENT: (" + Queue.get(i).getKey() + ", " + Queue.get(i).getObj() + ")" 
                            + " LEFT CHILD: (" + Queue.get((i * 2)+1).getKey() + ", " + Queue.get((i * 2)+1).getObj() + ")");
            if((i * 2)+2 < Queue.size()){
                System.out.print(" RIGHT CHILD: (" + Queue.get((i * 2)+2).getKey() + ", " + Queue.get((i * 2)+2).getObj() + ")");
            }	 
			System.out.println(); 
		} 
	}
    
    public void minHeap() 
    { 
        for (int pos = (Queue.size() - 1 / 2); pos >= 0; pos--) { 
            minHeapify(pos); 
        } 
    }

    public Pair<T> pop(){
        Pair<T> popped = Queue.get(0);
        int size  = Queue.size() - 1;
        if(size != 0){
            swap(0, size);
        }
        Queue.remove(size); //rimuovendo l'ultimo elemento è O(1)
        if(size != 0){
            minHeapify(0);
        }
        return popped;
    }

    public boolean isEmpty(){
        return Queue.isEmpty();
    }

    public int size(){
        return Queue.size();
    }

    @Override
    public String toString(){
        return Queue.toString();
    }
}