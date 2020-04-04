package lab1.algorithm;

import java.util.ArrayList;

import lab1.model.Graph;
import lab1.model.Node;
import lab1.model.Edge;

public class NodeSearch {

    static public Graph BFS (Graph G, Node s) {
        ArrayList<Integer> labels = new ArrayList<Integer>(G.getEdges().size());
        boolean[] ID = new boolean[G.getDimension()]; //all IDs are false
        return BFSCore(G, s, labels, ID);
    }

    private static Graph BFSCore (Graph G, Node s, ArrayList<Integer> labels, boolean[] ID) {
        int nodePosition = s.getID() - 1;
        ID[nodePosition] = true;
        Graph g = new Graph();
        
        for(Edge edge : s.getAdjacentList()) {
            int indexEdge = G.getEdges().indexOf(edge); 
            if (labels.get(indexEdge) == null) {
                Node w = G.opposite(s, edge);
                if (ID[nodePosition] == false) {
                    labels.set(indexEdge, 0); //DISCOVERY EDGE
                    g = BFSCore(G, w, labels, ID);
                } else {
                    labels.set(indexEdge, 1); //BACK EDGE
                }
                g.addNode(w);
                g.addEdge(edge);
            }
        }

        return g;
    }

    public static boolean Cyclicity (Graph G) {
        ArrayList<Integer> labels = new ArrayList<Integer>(G.getEdges().size());
        boolean[] ID = new boolean[G.getDimension()];
        BFSCore(G, G.getNodes().get(0), labels, ID);
        if(labels.contains(1)) { //if there is a BACK EDGE
            return true;
        }
        return false;
    }
}