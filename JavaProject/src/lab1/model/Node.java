package lab1.model;

import java.util.ArrayList;

public final class Node {
	private Integer ID;
	private Integer weight;
	private ArrayList<Integer> IDfather;
	private ArrayList<Integer> adjacentList;
	private Boolean visited = false;

	public Node(Integer iD) {
		super();
		ID = iD;
		this.weight = null;
		this.IDfather = new ArrayList<Integer>();
		this.adjacentList = new ArrayList<Integer>();
	}
	
	//deep copy
	public Node(Node n) {
		this.ID = n.ID;
		this.weight = n.weight;
		this.IDfather = new ArrayList<Integer>(n.IDfather.size());
		for (Integer idfat : n.IDfather) 
			this.IDfather.add(new Integer(idfat));
		this.adjacentList = new ArrayList<Integer>(n.adjacentList.size());
		for(Integer edgeadj : n.adjacentList) 
			this.adjacentList.add(new Integer(edgeadj));
		this.visited = n.visited;
	}


	public ArrayList<Integer> getIDfather() {
		return IDfather;
	}

	public void setIDfather(ArrayList<Integer> iDfather) {
		IDfather = new ArrayList<Integer>(iDfather);
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

	public ArrayList<Integer> getAdjacentList() {
		return adjacentList;
	}

	public void setAdjacentList(ArrayList<Integer> adjacentList) {
		this.adjacentList = adjacentList;
	}

	public void updateAdjacentList(Integer edge) {
		this.adjacentList.add(edge);
	}

	public void setVisited() {
		this.visited = true;
	}

	public Boolean isVisited() {
		return this.visited;
	}
	
	public void clear() {
		this.IDfather.clear();
		this.visited = false;
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
