package lab1.model;

import java.util.ArrayList;

public final class Node implements Comparable<Node>{
	private Integer ID = null;
	private Integer weight = null;
	private Node Father = null;
	//ID dei lati adiacenti
	private ArrayList<Edge> adjacentList = new ArrayList<Edge>();
	private Boolean visited = false;

	public Node(Integer id){
		ID = id;
	}
	
	//deep copy
	public Node(Node n) {
		this.ID = n.ID;
		this.weight = n.weight;
		this.Father = n.Father;
		this.adjacentList = new ArrayList<Edge>(n.adjacentList.size());
		for(Edge edgeadj : n.adjacentList) 
			this.adjacentList.add(edgeadj);
		this.visited = n.visited;
	}


	public Node getFather() {
		return Father;
	}

	public void setFather(Node father) {
		this.Father = father;
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

	public void setVisited() {
		this.visited = true;
	}

	public Boolean isVisited() {
		return this.visited;
	}
	
	public void clear() {
		this.Father = null;
		this.visited = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((Father == null) ? 0 : Father.hashCode());
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
		} else if (other.ID == null){
			return false;
		} else if(!ID.equals(other.ID)){
			return false;
		}
		// if (IDfather == null) {
		// 	if (other.IDfather != null)
		// 		return false;
		// } else if(other.IDfather == null)
		// 	return false;
		// else if (!IDfather.equals(other.IDfather))
		// 	return false;
		// if (adjacentList == null) {
		// 	if (other.adjacentList != null)
		// 		return false;
		// } else if (!adjacentList.equals(other.adjacentList))
		// 	return false;
		// if (weight == null) {
		// 	if (other.weight != null)
		// 		return false;
		// } else if (!weight.equals(other.weight))
		// 	return false;
		return true;
	}

	@Override
	public String toString(){
		return "NodeID: " + ID;
	}

	@Override
    public int compareTo(Node n) {
		if(this.weight > n.weight)
			return 1;
		else if(this.weight == n.weight)
			return 0;
		return -1;
	}

}
