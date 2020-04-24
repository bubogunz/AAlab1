package lab1.model;

import java.util.ArrayList;

import lab1.model.Edge.Label;

public final class Graph {

	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;

	public Graph() {
		super();
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	}
	
	//shallow copy
	public Graph(Graph graph) {
		nodes = new ArrayList<Node>(graph.nodes.size());
		edges = new ArrayList<Edge>(graph.edges.size());
		buildNodes(graph.getNodes().size());
		for(Edge edge : graph.getEdges())
			addEdge(new Edge(getNodeByID(edge.getNode1().getID()), getNodeByID(edge.getNode2().getID()), edge.getWeight()));
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = new ArrayList<Node>(nodes);
	}

	public void buildNodes(Integer n) {
		for (int i = 1; i <= n.intValue(); i++) 
			this.nodes.add(new Node(i));
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = new ArrayList<Edge>(edges);
	}
	
	//Adds edge to graph and update adjacent lists of edge's vertexes
	public void addEdge(Edge e) {
		if(e.getNode1().getID().compareTo(e.getNode2().getID()) > 0) {
			Node tmp = e.getNode1();
			e.setNode1(e.getNode2());
			e.setNode2(tmp);
		}
		this.edges.add(e);
		this.nodes.get(e.getNode1().getID()-1).updateAdjacentList(e);
		if(!e.getNode1().getID().equals(e.getNode2().getID()))
			this.nodes.get(e.getNode2().getID()-1).updateAdjacentList(e);

	}
	
	//O(m + n)
	public Boolean hasCycle() {
		DepthFirstSearch();
		Boolean ret = false;
		for (Edge edge : this.edges) {
			if(edge.getLabel() == Label.BACK_EDGE) 
				ret = true;
			edge.setLabel(null);
			edge.getNode1().setVisited(false);
			edge.getNode2().setVisited(false);
		}
		return ret;
	}

	public Node getNodeByID(int id){
		if(id <= nodes.size())
			return nodes.get(id - 1);

		return null;
	}

	//O(m)
	public Edge findEdge(Integer node1, Integer node2){
		for(Edge edge : edges){
			if((edge.getNode1().getID() == node1 && edge.getNode2().getID() == node2)
			|| (edge.getNode1().getID() == node2 && edge.getNode2().getID() == node1)){
				return edge;
			}
		}
		return null;
	}

	//computes DFS even in non-connected graphs. O(n)
	private void DepthFirstSearch() {
		for (Node node : this.nodes) 
			if(!node.isVisited() && !node.getAdjacentList().isEmpty()){
				this.DepthFirstSearchCore(node);
			}
	}

	private void DepthFirstSearchCore(Node start){
		start.setVisited(true);
		for(Edge edge : start.getAdjacentList()){
			if(edge.getLabel() == null){
				Node opposite = this.nodes.get(edge.getOpposite(start).getID() - 1);
				if(!opposite.isVisited()){
					edge.setLabel(Label.DISCOVERY_EDGE);
					DepthFirstSearchCore(opposite);
				}
				else 
					edge.setLabel(Label.BACK_EDGE);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}

	public int getDimension() {
		return nodes.size();
	}
}
