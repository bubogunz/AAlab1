package lab1.model;

import java.util.ArrayList;

public class DisjointSet{
    private ArrayList<Integer> parents = new ArrayList<Integer>();
    private ArrayList<Integer> ranks = new ArrayList<Integer>();

    public DisjointSet(Integer n){
        parents = new ArrayList<Integer>(n);
        ranks = new ArrayList<Integer>(n);
        for(int i = 0; i < n; i++){
            parents.add(i);
            ranks.add(0);
        }
    }

    public ArrayList<Integer> getParents(){
        return parents;
    }

    public ArrayList<Integer> getRanks(){
        return ranks;
    }

    public Integer find(Integer n){
        if(parents.get(n) != n)
            parents.set(n, find(parents.get(n)));
        return parents.get(n);
    }

    public void union(Integer x, Integer y){
        Integer xRoot = find(x);
        Integer yRoot = find(y);

        if(xRoot == yRoot)
            return;

        if(ranks.get(xRoot) < ranks.get(yRoot)){
            Integer tmp = xRoot;
            xRoot = yRoot;
            yRoot = tmp;
        }

        parents.set(yRoot, xRoot);
        if(ranks.get(xRoot) == ranks.get(yRoot))
            ranks.set(xRoot, xRoot + 1);
    }
}