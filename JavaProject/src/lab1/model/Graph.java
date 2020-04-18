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
	
	//deep copy
	public Graph(Graph graph) {
		nodes = new ArrayList<Node>(graph.nodes.size());
		edges = new ArrayList<Edge>(graph.edges.size());
		for (Node node : graph.nodes) 
			this.nodes.add(new Node(node.getID()));
		for (Edge edge : graph.edges){
			Edge e = new Edge(getNodeByID(edge.getnodeID1().getID()), getNodeByID(edge.getnodeID2().getID()), edge.getWeight());
			this.edges.add(e);
			this.nodes.get(e.getnodeID1().getID() - 1).updateAdjacentList(e);
			this.nodes.get(e.getnodeID2().getID() - 1).updateAdjacentList(e);
		}
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

	public void addEdge(Edge e) {
		if(e.getnodeID1().getID() > e.getnodeID2().getID()) {
			Node tmp = e.getnodeID1();
			e.setnodeID1(e.getnodeID2());
			e.setnodeID2(tmp);
		}
		// if edge already exists, update the weight
		boolean edegeExist = false;
		for(int i = 0; i < edges.size() && !edegeExist; i++){
			if(edges.get(i).getnodeID1() == e.getnodeID1() && edges.get(i).getnodeID2() == e.getnodeID2()){
				edegeExist = true;
				if(edges.get(i).getWeight() > e.getWeight())
					edges.get(i).setWeight(e.getWeight());
			}
		}
		boolean loop = e.getnodeID1().getID().equals(e.getnodeID2().getID());
		if(!edegeExist && !loop){
			this.edges.add(e);
			//add the edge to the node's adjacent list if it is not a loop edge
			this.nodes.get(e.getnodeID1().getID() - 1).updateAdjacentList(e);
			this.nodes.get(e.getnodeID2().getID() - 1).updateAdjacentList(e);
		}
	}
	
	public Boolean hasCycle() {
		DepthFirstSearch();
		Boolean ret = false;
		for (Edge edge : this.edges) {
			if(edge.getLabel() == Label.BACK_EDGE && 
				edge.getAncestor().getID().equals(edge.getnodeID2().getID())) 
				ret = true;
			edge.setAncestor(null);
			edge.setLabel(null);
		}
		return ret;
	}

	public Node getNodeByID(int id){
		if(id <= nodes.size())
			return nodes.get(id - 1);
		return null;
	}

	public Edge findEdge(Integer node1, Integer node2){
		for(Edge edge : edges){
			if((edge.getnodeID1().getID() == node1 && edge.getnodeID2().getID() == node2)
			|| (edge.getnodeID1().getID() == node2 && edge.getnodeID2().getID() == node1)){
				return edge;
			}
		}
		return null;
	}

	//cerca esegue una ricerca anche per i grafi non connessi
	private void DepthFirstSearch() {
		for (Node node : this.nodes) 
			if(!node.isVisited() && !node.getAdjacentList().isEmpty()){
				this.DepthFirstSearchCore(node);
			}
	}

	private void DepthFirstSearchCore(Node start){
		start.setVisited();
		for(Edge edge : start.getAdjacentList()){
			if(edge.getLabel() == null){
				Node w = Graph.getOpposite(edge, start);
				if(!w.isVisited()){
					edge.setLabel(Label.DISCOVERY_EDGE);
					w.setFather(start);
					DepthFirstSearchCore(w);
				}
				else{
					edge.setAncestor(w);
					edge.setLabel(Label.BACK_EDGE);
				}
			}
		}
	}

	public boolean cyclicity (){
		this.DepthFirstSearch();
		boolean ret = false;
		for(Edge edge : this.edges){
			if(edge.getLabel() != null && edge.getLabel() == Label.BACK_EDGE 
				&& ((edge.getAncestor() == edge.getnodeID1()) || (edge.getAncestor() == edge.getnodeID2())))
				ret = true;
		}
		for(Node node : this.nodes)
			node.clear();
		for(Edge edge : this.edges)
			edge.clear();
		return ret;
	}

	public static Node getOpposite(Edge e, Node n){
		if(e.getnodeID1().getID() == n.getID())
			return e.getnodeID2();
		else if(e.getnodeID2().getID() == n.getID())
			return e.getnodeID1();
		return null;
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
