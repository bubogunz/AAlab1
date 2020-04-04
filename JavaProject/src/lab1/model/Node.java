package lab1.model;

import java.util.ArrayList;

public final class Node {
    private Integer ID;
    private Node IDfather;
    private ArrayList<Edge> adjacentList;

    public Node(Integer iD) {
	super();
	ID = iD;
	this.adjacentList = new ArrayList<Edge>();
    }

    public Node(Node node) {
	super();
	this.ID = Integer.valueOf(node.ID);
	this.adjacentList = new ArrayList<Edge>(node.adjacentList);
    }

    public Integer getID() {
	return ID;
    }

    public void setID(Integer iD) {
	ID = iD;
	}
	
    public ArrayList<Edge> getAdjacentList() {
	return adjacentList;
    }

    public void setAdjacentList(ArrayList<Edge> adjacentList) {
	this.adjacentList = adjacentList;
    }

    public void updateAdjacentList(Edge edge) {
	this.adjacentList.add(edge);
	}
	
	public void removeEdgeFromAdjacentList(Edge edge) {
	this.adjacentList.remove(edge);	
	}

	public ArrayList<Node> getAdjacentNodes() {
		ArrayList<Node> adjacentNodes = new ArrayList<Node>(adjacentList.size());
		for (Edge edge : adjacentList) {
			adjacentNodes.add((ID == edge.getnode1().getID()) ? edge.getnode2() : edge.getnode1());
		}
		return adjacentNodes;
	}

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((ID == null) ? 0 : ID.hashCode());
	result = prime * result + ((IDfather == null) ? 0 : IDfather.hashCode());
	result = prime * result + ((adjacentList == null) ? 0 : adjacentList.hashCode());
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
	Node other = (Node) obj;
	if (ID == null) {
	    if (other.ID != null)
		return false;
	} else if (!ID.equals(other.ID))
	    return false;
	if (IDfather == null) {
	    if (other.IDfather != null)
		return false;
	} else if (!IDfather.equals(other.IDfather))
	    return false;
	if (adjacentList == null) {
	    if (other.adjacentList != null)
		return false;
	} else if (!adjacentList.equals(other.adjacentList))
	    return false;
	return true;
    }

}
