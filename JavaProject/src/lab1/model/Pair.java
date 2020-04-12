package lab1.model;

public class Pair<T>{
    private int key;
    private T obj;

    public Pair(int k, T o){
        key = k;
        obj = o;
    }

    public int getKey(){
        return key;
    }

    public T getObj(){
        return obj;
    }

    public void setKey(int k){
        key = k;
    }

    public void setObj(T o){
        obj = o;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
          return true;
        }
        if (obj == null) {
          return false;
        }
        if (getClass() != obj.getClass()) {
          return false;
        }
        Pair<T> other = (Pair<T>) obj;
        if(key != other.key || !this.obj.equals(other.obj)){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "(k: " + key + ", o: " + obj.toString() + ")"; 
    }
}