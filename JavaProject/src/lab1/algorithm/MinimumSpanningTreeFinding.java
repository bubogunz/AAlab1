package lab1.algorithm;

import java.util.ArrayList;
import java.util.Collections;

import lab1.model.Edge;
import lab1.model.Graph;
import lab1.model.MinHeap;
import lab1.model.Node;
import lab1.model.SortEdgesByWeight;
import lab1.model.SortNodesByWeight;
import lab1.model.DisjointSet;

public final class MinimumSpanningTreeFinding {


	public static final int Prim(Graph G, Node start) {
		
		int cost = 0;

		G.getNodes().stream().forEach(node -> {
			node.setWeight(Integer.MAX_VALUE); //O(n)
			node.setFather(null);
		});
		start.setWeight(0);
		
		MinHeap<Node> Q = new MinHeap<Node>(G.getNodes().size(), new SortNodesByWeight());
		
		G.getNodes().stream().forEach(node -> Q.insert(node));//O(n*log n)
		
		while(!Q.isEmpty()) {//O(n*(3*log n))
			Node lightNode = Q.extractMin();
			lightNode.setVisited(true);
			cost += lightNode.getWeight();
			
			for(Edge edge : lightNode.getAdjacentList()){//O(m)
				Node opposite = edge.getOpposite(lightNode);
				if(!opposite.isVisited()
						&& edge.getWeight().compareTo(opposite.getWeight()) < 0) {
					Q.remove(opposite);
					opposite.setFather(lightNode);
					opposite.setWeight(edge.getWeight());
					Q.insert(opposite);
				}
			}
		}
		return cost;
	}

	public static final int NaiveKruskal(Graph G) {
		int cost = 0;

		ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
		Collections.sort(edges, new SortEdgesByWeight());//O(nlog n)
		
		Graph A = new Graph();
		A.buildNodes(G.getNodes().size());//O(n)
		
		for (Edge edge : edges) {//O(m)
			
			Node node1 = A.getNodeByID(edge.getNode1().getID());
			Node node2 = A.getNodeByID(edge.getNode2().getID());
			
			Edge edgeToInsert = new Edge(node1, node2, edge.getWeight());

			A.addEdge(edgeToInsert);

			if(!A.hasCycle())
				cost += edge.getWeight();
			else{
				A.getEdges().remove(A.getEdges().size() - 1);
				node1.getAdjacentList().remove(node1.getAdjacentList().size() - 1);
				if(!node1.getID().equals(node2.getID()))
					node2.getAdjacentList().remove(node2.getAdjacentList().size() - 1);
			}	
		}
		return cost;
	}

	public static final int Kruskal(Graph G){
		int cost = 0;

		ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
		Collections.sort(edges, new SortEdgesByWeight());//O(log n)
		
		Graph A = new Graph();
		A.buildNodes(G.getNodes().size());//O(n)

		DisjointSet ds = new DisjointSet(A.getNodes().size());
		
		for(Edge edge : edges){//O(m)
			if(ds.find(edge.getNode1().getID() - 1) != ds.find(edge.getNode2().getID() - 1)){//O(log n)
				Edge edgetmp = new Edge(A.getNodeByID(edge.getNode1().getID()), A.getNodeByID(edge.getNode2().getID()), edge.getWeight());
				Node tmp1 = edgetmp.getNode1();
				Node tmp2 = edgetmp.getNode2();

				A.getEdges().add(edgetmp);
				tmp1.updateAdjacentList(edgetmp);
				tmp2.updateAdjacentList(edgetmp);

				ds.union(edge.getNode1().getID() - 1, edge.getNode2().getID() - 1);//O(log n)

				cost += edge.getWeight();
			}
		}
		return cost;
	}
}
