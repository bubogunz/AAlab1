package testprim;

import lab1.model.PriorityQueue;
import lab1.model.Pair;

public class TestPriorityQueue {
    public static final void test(){
        PriorityQueue<Integer> Q = new PriorityQueue<Integer>();
        Q.insert(new Pair<Integer>(13, null));
        Q.insert(new Pair<Integer>(5, null));
        Q.insert(new Pair<Integer>(20, 28));
        Q.insert(new Pair<Integer>(12, null));
        Q.insert(new Pair<Integer>(34, null));
        Q.insert(new Pair<Integer>(6, null));
        Q.insert(new Pair<Integer>(8, null));
        Q.insert(new Pair<Integer>(13, null));
        Q.print();
        System.out.println();

        Q.removeByKey(12);
        Q.print();
        System.out.println();

        Q.pop();
        Q.print();

        System.out.println(Q.findObj(20));

        Q.setByKey(20, new Pair<Integer>(14, 12));
        Q.print();
        System.out.println(Q.findObj(14));
        Q.setByIndex(1, new Pair<Integer>(9, 11));
        Q.print();
        System.out.println(Q.findKey(11));
    }

}