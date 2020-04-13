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
			this.nodes.add(new Node(node));
		for (Edge edge : graph.edges) 
			this.edges.add(edge);
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
		// if node already exists, updatte the weight
		boolean edegeExist = false;
		for(int i = 0; i < edges.size() && !edegeExist; i++){
			if(edges.get(i).getnodeID1() == e.getnodeID1() && edges.get(i).getnodeID2() == e.getnodeID2()){
				edegeExist = true;
				if(edges.get(i).getWeight() > e.getWeight()){
					edges.get(i).setWeight(e.getWeight());
					this.nodes.get(e.getnodeID1().getID() - 1).updateAdjacentWeight(e.getnodeID2().getID(), e.getWeight());
					this.nodes.get(e.getnodeID2().getID() - 1).updateAdjacentWeight(e.getnodeID1().getID(), e.getWeight());
				}
			}
		}
		if(!edegeExist){
			this.edges.add(e);
			//add the edge to the node's adjacent list if it is not a loop edge
			if(!e.getnodeID1().getID().equals(e.getnodeID2().getID())){
				this.nodes.get(e.getnodeID1().getID() - 1).updateAdjacentList(e.getnodeID2(), e.getWeight());
				this.nodes.get(e.getnodeID2().getID() - 1).updateAdjacentList(e.getnodeID1(), e.getWeight());
			}
		}
	}
	
	public Boolean hasCycle() {
		DepthFirstSearch();
		Boolean ret = false;
		for (Edge edge : this.edges) {
			if(edge.getLabel() == Label.BACK_EDGE && 
				edge.getAncestor().equals(edge.getnodeID2().getID())) 
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

	private void DepthFirstSearch() {
		for (Node node : this.nodes) 
			if(!node.isVisited() && !node.getAdjacentList().isEmpty())
				DepthFirstSearch(node.getID());
	}

	private void DepthFirstSearch(Integer start) {
		this.nodes.get(start-1).setVisited();
		for (int i=0; i<this.nodes.get(start-1).getAdjacentList().size(); ++i) {
			if(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i).getID()).getLabel() == null) {
				Integer opposite = start.equals(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i).getID()).getnodeID2().getID()) 
						? Integer.valueOf(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i).getID()).getnodeID1().getID())
								: Integer.valueOf(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i).getID()).getnodeID2().getID());
				if(!this.nodes.get(opposite-1).isVisited()) {
					this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i).getID()).setLabel(Label.DISCOVERY_EDGE);
					this.nodes.get(opposite-1).getIDfather().add(start);
					DepthFirstSearch(opposite);
			    } 
				else {
					this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i).getID()).setLabel(Label.BACK_EDGE);		
					this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i).getID()).setAncestor(Integer.max(start, opposite));
				}
			}
		}
	}

	public boolean Cyclicity (Graph G){
		DepthFirstSearch();
		for(Edge edge : edges)
			if(edge.getLabel() == Label.BACK_EDGE)
				return true;
		
		return false;
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
