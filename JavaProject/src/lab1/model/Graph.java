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
		if(e.getnodeID1() > e.getnodeID2()) {
			Integer tmp = new Integer(e.getnodeID1());
			e.setnodeID1(e.getnodeID2());
			e.setnodeID2(tmp);
		}
		this.edges.add(e);
		//add the edge to the node's adjacent list if it is not a loop edge
//		if(!e.getnodeID1().equals(e.getnodeID2())){
		this.nodes.get(e.getnodeID1()-1).updateAdjacentList(edges.size()-1);
			this.nodes.get(e.getnodeID2()-1).updateAdjacentList(edges.size()-1);
//		}
	}	
	
	public Boolean hasCycle() {
		DepthFirstSearch();
		Boolean ret = false;
		for (Edge edge : this.edges) {
			if(edge.getLabel() == Label.BACK_EDGE && 
				edge.getAncestor().equals(edge.getnodeID2())) 
				ret = true;
			edge.setAncestor(null);
			edge.setLabel(null);
		}
		return ret;
	}

	private void DepthFirstSearch() {
		for (Node node : this.nodes) 
			if(!node.isVisited() && !node.getAdjacentList().isEmpty())
				DepthFirstSearch(node.getID());
	}

	private void DepthFirstSearch(Integer start) {
		this.nodes.get(start-1).setVisited();
		for (int i=0; i<this.nodes.get(start-1).getAdjacentList().size(); ++i) {
			if(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i)).getLabel() == null) {
				Integer opposite = start.equals(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i)).getnodeID2()) 
						? new Integer(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i)).getnodeID1())
								: new Integer(this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i)).getnodeID2());
				if(!this.nodes.get(opposite-1).isVisited()) {
					this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i)).setLabel(Label.DISCOVERY_EDGE);
					this.nodes.get(opposite-1).getIDfather().add(start);
					DepthFirstSearch(opposite);
			    } 
				else {
					this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i)).setLabel(Label.BACK_EDGE);		
					this.edges.get(this.nodes.get(start-1).getAdjacentList().get(i)).setAncestor(Integer.max(start, opposite));
				}
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
}
