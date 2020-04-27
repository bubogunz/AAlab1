package lab1.algorithm;

import java.util.ArrayList;
import java.util.Collections;

import lab1.model.Edge;
import lab1.model.Graph;
import lab1.model.MinHeap;
import lab1.model.Node;
import lab1.model.SortNodesByWeight;
import lab1.model.DisjointSet;
/**
 * The main class implementing Kruskal with union-find, Kruskal with a "naive"
 * approach and Prim using MinHeap structure
 */
public final class MinimumSpanningTreeFinding {

	/**
	 * @param G = graph in witch find the MST
	 * @param start = node of G where the algorithm starts the research
	 * @return cost of MST
	 * 
	 * This algorithm has complexity O(nlogn)
	 */
	public static final int Prim(Graph G, Node start) {
		
		int cost = 0;

		//O(n)
		G.getNodes().stream().forEach(node -> {
			node.setWeight(Integer.MAX_VALUE);
			node.setFather(null);
		});
		start.setWeight(0);
		
		MinHeap<Node> Q = new MinHeap<Node>(G.getNodes().size());
		
		//O(nlog n)
		G.getNodes().stream().forEach(node -> Q.insert(node));
		
		//O(n(3log n)) = O(nlog n)
		while(!Q.isEmpty()) {//O(n)
			Node lightNode = Q.extractMin();//O(log n)
			lightNode.setVisited(true);
			cost += lightNode.getWeight();
			
			for(Edge edge : lightNode.getAdjacentList()){//O(m)
				Node opposite = edge.getOpposite(lightNode);
				if(!opposite.isVisited()
						&& edge.getWeight().compareTo(opposite.getWeight()) < 0) {
					Q.remove(opposite);//O(log n)
					opposite.setFather(lightNode);
					opposite.setWeight(edge.getWeight());
					Q.insert(opposite);//O(log n)
				}
			}
		}
		return cost;
	}

	/**
	 * @param G = graph in witch find the MST
	 * @return cost of MST
	 * 
	 * This algorithm has complexity O(mn)
	 */
	public static final int NaiveKruskal(Graph G) {
		int cost = 0;

		ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
		//O(log n)
		Collections.sort(edges);

		Graph A = new Graph();
		//O(n)
		A.buildNodes(G.getNodes().size());
		
		//O(mn)
		for (Edge edge : edges) {//O(m)
			
			Node node1 = A.getNodeByID(edge.getNode1().getID());
			Node node2 = A.getNodeByID(edge.getNode2().getID());
			
			Edge edgeToInsert = new Edge(node1, node2, edge.getWeight());

			A.addEdge(edgeToInsert);

			//O(n)
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

	/**
	 * @param G = graph in witch find the MST
	 * @return cost of MST
	 * 
	 * This algorithm has complexity O(mn)
	 */
	public static final int Kruskal(Graph G){
		int cost = 0;

		ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
		//O(log n)
		Collections.sort(edges);
		
		Graph A = new Graph();
		//O(n)
		A.buildNodes(G.getNodes().size());

		//O(n)
		DisjointSet ds = new DisjointSet(A.getNodes().size());
		
		//O(mlog n)
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
