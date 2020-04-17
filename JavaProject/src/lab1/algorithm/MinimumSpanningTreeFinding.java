package lab1.algorithm;

import java.util.ArrayList;
import java.util.Collections;

import lab1.model.Edge;
import lab1.model.Graph;
import lab1.model.Node;
import lab1.model.SortEdgesByWeight;
import lab1.model.Pair;
import lab1.model.PriorityQueue;
import lab1.model.DisjointSet;

public final class MinimumSpanningTreeFinding {

	public static final int Prim(Graph G, Node startID) {
		int cost = 0;
		PriorityQueue<Node> Q = new PriorityQueue<Node>();
		G.getNodes().stream().forEach(node -> Q.insert(new Pair<Node>(Integer.MAX_VALUE, node)));//O(n)
		Q.setByIndex(Q.indexOfObj(startID), new Pair<Node>(0, startID));//O(log n)
		ArrayList<Integer> pi = new ArrayList<Integer>(G.getNodes().size());
		for(int i = 0; i < G.getNodes().size(); i++)//O(n)
			pi.add(null);
		
		boolean[] polledNodes = new boolean[Q.size()];
		
		while(!Q.isEmpty()) {//O(n)
			Pair<Node> q = Q.pop();//O(log n)
			Node lightNode = q.getObj();
			polledNodes[lightNode.getID() - 1] = true;
			cost += q.getKey();

			for(Edge edge : lightNode.getAdjacentList()){//O(m)
				Integer edgeWeight = edge.getWeight();
				Node v = Graph.getOpposite(edge, lightNode);
				if(polledNodes[v.getID() - 1] == false && edgeWeight < Q.findKey(v)){//O(log n)
					pi.set(v.getID() - 1, lightNode.getID());
					Q.setByIndex(Q.indexOfObj(v), new Pair<Node>(edgeWeight, v));//O(log n)
				}
			}
		}
		return cost;
	}

	public static final int NaiveKruskal(Graph G) {
		int cost = 0;

		ArrayList<Edge> edges = new ArrayList<Edge>(G.getEdges());
		Collections.sort(edges, new SortEdgesByWeight());//O(log n)
		
		Graph A = new Graph();
		A.buildNodes(G.getNodes().size());//O(n)
		
		for (Edge edge : edges) {//O(m)

			Edge edgetmp = new Edge(A.getNodeByID(edge.getnodeID1().getID()), A.getNodeByID(edge.getnodeID2().getID()), edge.getWeight());
			Node tmp1 = edgetmp.getnodeID1();
			Node tmp2 = edgetmp.getnodeID2();

			A.getEdges().add(edgetmp);
			tmp1.updateAdjacentList(edgetmp);
			tmp2.updateAdjacentList(edgetmp);

			if(!A.cyclicity())//O(m)
				cost += edge.getWeight();
			else{
				A.getEdges().remove(A.getEdges().size() - 1);
				tmp1.getAdjacentList().remove(tmp1.getAdjacentList().size() - 1);
				tmp2.getAdjacentList().remove(tmp2.getAdjacentList().size() - 1);
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
		
		for(Edge edge : edges){
			if(ds.find(edge.getnodeID1().getID() - 1) != ds.find(edge.getnodeID2().getID() - 1)){
				Edge edgetmp = new Edge(A.getNodeByID(edge.getnodeID1().getID()), A.getNodeByID(edge.getnodeID2().getID()), edge.getWeight());
				Node tmp1 = edgetmp.getnodeID1();
				Node tmp2 = edgetmp.getnodeID2();

				A.getEdges().add(edgetmp);
				tmp1.updateAdjacentList(edgetmp);
				tmp2.updateAdjacentList(edgetmp);

				ds.union(edge.getnodeID1().getID() - 1, edge.getnodeID2().getID() - 1);

				cost += edge.getWeight();
			}
		}
		return cost;
	}
}
