package lab1.model;

import java.util.ArrayList;

public final class Node {
    private Integer ID;
    private Integer weight;
    private Integer IDfather;
    private ArrayList<Edge> adjacentList;

    public Node(Integer iD) {
	super();
	ID = iD;
	this.weight = null;
	this.IDfather = null;
	this.adjacentList = new ArrayList<Edge>();
    }

    public Node(Node node) {
	super();
	this.ID = new Integer(node.ID);
	this.weight = new Integer(node.weight);
	this.IDfather = new Integer(node.IDfather);
	this.adjacentList = new ArrayList<Edge>(node.adjacentList);
    }

    public Integer getIDfather() {
	return IDfather;
    }

    public void setIDfather(Integer iDfather) {
	IDfather = iDfather;
    }

    public Integer getID() {
	return ID;
    }

    public void setID(Integer iD) {
	ID = iD;
    }

    public Integer getWeight() {
	return weight;
    }

    public void setWeight(Integer weight) {
	this.weight = weight;
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((ID == null) ? 0 : ID.hashCode());
	result = prime * result + ((IDfather == null) ? 0 : IDfather.hashCode());
	result = prime * result + ((adjacentList == null) ? 0 : adjacentList.hashCode());
	result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
	if (weight == null) {
	    if (other.weight != null)
		return false;
	} else if (!weight.equals(other.weight))
	    return false;
	return true;
    }

}
