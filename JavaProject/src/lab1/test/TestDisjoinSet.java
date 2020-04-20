package lab1.test;
import lab1.model.DisjointSet;

public class TestDisjoinSet {
    public static void test(){
        DisjointSet ds = new DisjointSet(10);
        ds.union(1, 2);
        System.out.println(ds.find(1) == ds.find(2));
        System.out.println(!(ds.find(1) == ds.find(3)));
    }
    
}