package lab1.model;

import java.util.ArrayList;

public final class Graph {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph() {
	super();
	this.nodes = new ArrayList<Node>();
	this.edges = new ArrayList<Edge>();
    }

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
	super();
	this.nodes = nodes;
	this.edges = edges;
    }
    
    public Graph(Graph graph) {
	this.nodes = new ArrayList<Node>(graph.nodes);
	this.edges = new ArrayList<Edge>(graph.edges);
    }

    public ArrayList<Node> getNodes() {
	return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
	this.nodes = nodes;
    }

    public void buildNodes(Integer n) {
	for (int i = 1; i <= n.intValue(); i++) 
	    this.nodes.add(new Node(i + this.nodes.size()));
    }

    public ArrayList<Edge> getEdges() {
	return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
	this.edges = edges;
    }

    public void addEdge(Edge e) {
	this.edges.add(e);
	//add the edge to the node's adjacent list if it is not a loop
	if(!e.getnode1().equals(e.getnode2()) && !this.existsEdge(e)){
	    this.nodes.get(e.getnode1().getID()-1).updateAdjacentList(e);
	    this.nodes.get(e.getnode2().getID()-1).updateAdjacentList(e);
	}
    }
    
    public void addNode(Node n) {
	if(!nodes.contains(n))
	    nodes.add(n);
	}
	
	public void removeNode(Node n) {
	if(this.nodes.contains(n)) {
		//removes all edges connected with selected node
		for (int i = 0; i < this.edges.size(); i++) {
			if (this.edges.get(i).getnode1().getID() == n.getID()
				|| this.edges.get(i).getnode2().getID() == n.getID()) {
					//update AdjacentList of selected node
					n.removeEdgeFromAdjacentList(this.edges.get(i));
					this.edges.remove(i);
					i--;
				}
		}
		this.nodes.remove(n);
	}
	}

	public Integer getDimension() {
		return nodes.size();
	}

	public void addEdge(Edge e) {
		if(e.getnodeID1().getID() > e.getnodeID2().getID()) {
			Node tmp = e.getnodeID1();
			e.setnodeID1(e.getnodeID2());
			e.setnodeID2(tmp);
		}
		// if node already exists, updatte the weight
		boolean nodeExist = false;
		for(int i = 0; i < edges.size() && !nodeExist; i++){
			if(edges.get(i).getnodeID1() == e.getnodeID1() && edges.get(i).getnodeID2() == e.getnodeID2()){
				nodeExist = true;
				if(edges.get(i).getWeight() > e.getWeight()){
					edges.get(i).setWeight(e.getWeight());
				}
			}
		}
		if(!nodeExist){
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
	return false;
	}
}
