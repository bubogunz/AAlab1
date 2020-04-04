package lab1.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import lab1.model.Edge;
import lab1.model.Graph;
import lab1.model.Node;

public final class MinimumSpanningTreeFinding {

    public static final int Prim(Graph G, Integer startID) {
	int cost = 0;
	ArrayList<Integer> pi = new ArrayList<Integer>(G.getDimension());
	ArrayList<Integer> keys = new ArrayList<Integer>(G.getDimension());
	G.getNodes().stream().forEach(node -> {
		if(node.getID() == startID) {
			keys.add(0);
		} else {
			keys.add(Integer.MAX_VALUE);
		} 
	});

	PriorityQueue<Integer> Q = new PriorityQueue<Integer>(keys);

	// all values have as value false
	boolean[] polledNodes = new boolean[Q.size()];

	while(!Q.isEmpty()) {
		Integer nodeID = keys.indexOf(Q.poll());
		Node lightNode = G.getNodeByID(nodeID);
	    polledNodes[lightNode.getID().intValue()-1] = true;

	    if(pi.get(nodeID - 1) != 0) {
		//		System.out.println("Da nodo " + lightNode.getIDfather() + " a nodo " + lightNode.getID()
		//		+ " con costo " + lightNode.getWeight());
		cost += keys.get(nodeID);
		}

	    for (Edge edge : lightNode.getAdjacentList()) {
			Integer otherSideOfTheEdgeNode = G.opposite(lightNode, edge).getID();

			if(polledNodes[otherSideOfTheEdgeNode.intValue() - 1] == false 
				&& edge.getWeight() < keys.get(otherSideOfTheEdgeNode - 1)) {

				pi.set(nodeID, otherSideOfTheEdgeNode - 1);
				Q.remove(keys.get(otherSideOfTheEdgeNode - 1));
				keys.set(otherSideOfTheEdgeNode, edge.getWeight());
			    Q.offer(otherSideOfTheEdgeNode);
			}
	    }
	}
	return cost;
    }

    public static final int NaiveKruskal(Graph G) {
	int cost = 0;

	ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
	Collections.sort(edges);

	Graph A = new Graph();

	for(int i=0; i < G.getDimension() - 1; i++) {
		Graph tmpGraph = new Graph(A);
		Edge currentEdge = edges.get(i);
		tmpGraph.addEdge(currentEdge);
		tmpGraph.addNode(currentEdge.getnode1());
		tmpGraph.addNode(currentEdge.getnode2());
		if(!NodeSearch.Cyclicity(tmpGraph)) {
			A = tmpGraph;
			cost += currentEdge.getWeight();
		}
	}

	return cost;
    }
}
