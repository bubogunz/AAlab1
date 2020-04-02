package lab1.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import lab1.model.Edge;
import lab1.model.Graph;
import lab1.model.Node;
import lab1.model.SortEdgesByWeight;
import lab1.model.SortNodesByWeight;

public final class MinimumSpanningTreeFinding {

    public static final int Prim(Graph G, Integer startID) {
	int cost = 0;
	G.getNodes().stream().forEach(node -> {
	    node.setIDfather(null);
	    node.setWeight(Integer.MAX_VALUE);
	});

	G.getNodes().get(startID-1).setWeight(Integer.MIN_VALUE);

	PriorityQueue<Node> Q = new PriorityQueue<Node>(G.getNodes().size(), new SortNodesByWeight());

	G.getNodes().stream().forEach(node -> Q.offer(node));

	int[] polledNodes = new int[Q.size()];

	while(!Q.isEmpty()) {
	    Node lightNode = Q.poll();
	    polledNodes[lightNode.getID().intValue()-1] = 1;

	    if(lightNode.getIDfather() != null) {
		//		System.out.println("Da nodo " + lightNode.getIDfather() + " a nodo " + lightNode.getID()
		//		+ " con costo " + lightNode.getWeight());
		cost += lightNode.getWeight();
	    }

	    for (Edge edge : lightNode.getAdjacentList()) {				

		Integer otherSideOfTheEdgeNodeID = lightNode.getID().equals(edge.getnodeID1())
			? new Integer(edge.getnodeID2()) 
				: new Integer(edge.getnodeID1());

			Node otherSideOfTheEdgeNode = G.getNodes().get(otherSideOfTheEdgeNodeID-1);

			if(polledNodes[otherSideOfTheEdgeNode.getID().intValue()-1] == 0 
				&& edge.getWeight().compareTo(otherSideOfTheEdgeNode.getWeight()) < 0 ) {

			    Q.remove(otherSideOfTheEdgeNode);
			    otherSideOfTheEdgeNode.setIDfather(lightNode.getID());
			    otherSideOfTheEdgeNode.setWeight(edge.getWeight());
			    Q.offer(otherSideOfTheEdgeNode);
			}
	    }
	}
	return cost;
    }

    public static final int NaiveKruskal(Graph G) {
	int cost = 0;

	ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
	SortEdgesByWeight sortEdgesByWeight = new SortEdgesByWeight();
	Collections.sort(edges, sortEdgesByWeight);

	Graph A = new Graph();
	ArrayList<Integer> removedNodesID = new ArrayList<Integer>();
	
	while(A.getEdges().size() < G.getNodes().size() - 1) {		
	    Edge edge = new Edge(edges.get(0));
	    if(!(removedNodesID.contains(edge.getnodeID1()) && removedNodesID.contains(edge.getnodeID2()))) {
		Node node1 = G.getNodes().get(edge.getnodeID1()-1);
		removedNodesID.add(node1.getID());
		Node node2 = G.getNodes().get(edge.getnodeID2()-1);
		removedNodesID.add(node2.getID());
		A.addEdge(edge);
		A.addNode(node1);
		A.addNode(node2);
		edges.remove(0);
		cost += edge.getWeight();
	    }
	}

	return cost;
    }
}
