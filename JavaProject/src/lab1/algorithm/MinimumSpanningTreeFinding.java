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
			node.getIDfather().clear();
			node.setWeight(Integer.MAX_VALUE);
		});
		
//		G.getNodes().get(startID-1).setWeight(0);
		G.getNodes().get(startID-1).setWeight(Integer.MIN_VALUE);

		PriorityQueue<Node> Q = new PriorityQueue<Node>(G.getNodes().size(), new SortNodesByWeight());

		G.getNodes().stream().forEach(node -> Q.offer(node));

		int[] polledNodes = new int[Q.size()];

		while(!Q.isEmpty()) {
			Node lightNode = Q.poll();
			polledNodes[lightNode.getID().intValue()-1] = 1;

			if(!lightNode.getIDfather().isEmpty()) 
				cost += lightNode.getWeight();

			for (int i=0; i<lightNode.getAdjacentList().size(); ++i) {				

				Integer otherSideOfTheEdgeNodeID = lightNode.getID().equals(
						G.getEdges().get(lightNode.getAdjacentList().get(i)).getnodeID1())
						? new Integer(G.getEdges().get(lightNode.getAdjacentList().get(i)).getnodeID2()) 
								: new Integer(G.getEdges().get(lightNode.getAdjacentList().get(i)).getnodeID1());

				Node otherSideOfTheEdgeNode = G.getNodes().get(otherSideOfTheEdgeNodeID-1);

				if(polledNodes[otherSideOfTheEdgeNode.getID().intValue()-1] == 0 
						&& G.getEdges().get(lightNode.getAdjacentList().get(i)).getWeight()
						.compareTo(otherSideOfTheEdgeNode.getWeight()) < 0 ) {
					Q.remove(otherSideOfTheEdgeNode);
					otherSideOfTheEdgeNode.getIDfather().add(lightNode.getID());
					otherSideOfTheEdgeNode.setWeight(
							G.getEdges().get(lightNode.getAdjacentList().get(i)).getWeight());
					Q.offer(otherSideOfTheEdgeNode);
				}
			}
		}
		return cost;
	}

	public static final int NaiveKruskal(Graph G) {
		int cost = 0;

		ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
		Collections.sort(edges, new SortEdgesByWeight());

		Graph A = new Graph();
		A.buildNodes(G.getNodes().size());

		for (Edge edge : edges) {
			Graph B = new Graph(A);
			B.addEdge(edge);
			if(!B.hasCycle()) {
				A.addEdge(edge);
				cost += edge.getWeight();
			}	
		}
//		System.out.println("MST cost: " + cost);
		return cost;
	}      
}
