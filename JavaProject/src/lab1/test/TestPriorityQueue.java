package lab1.test;

import lab1.model.PriorityQueuee;

public class TestPriorityQueue {
    public static final void test(){
        PriorityQueuee<Integer> Q = new PriorityQueuee<Integer>();
        Q.insert(12);
        System.out.println(Q.pop());
        Q.insert(5);
        Q.insert(20);
        Q.insert(43);
        Q.insert(54);
        Q.insert(1);
        Q.insert(32);
        Q.insert(4);
        Q.print();
        System.out.println();
        Q.remove(1);
        Q.print();
        System.out.println();
        Q.remove(54);
        Q.print();
        System.out.println();
    }
}