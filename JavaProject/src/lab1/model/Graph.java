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
	    this.nodes.add(new Node(i));
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
	if(!e.getnodeID1().equals(e.getnodeID2())){
	    this.nodes.get(e.getnodeID1()-1).updateAdjacentList(e);
	    this.nodes.get(e.getnodeID2()-1).updateAdjacentList(e);
	}
    }
    
    public void addNode(Node n) {
	if(!nodes.contains(n))
	    nodes.add(n);
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
