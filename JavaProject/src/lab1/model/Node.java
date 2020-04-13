package lab1.model;

import java.util.ArrayList;

public final class Node {
	private Integer ID;
	private Integer weight;
	private ArrayList<Integer> IDfather;
	private ArrayList<Node> adjacentList;
	private ArrayList<Integer> adjacentWeights;
	private Boolean visited = false;

	public Node(Integer iD) {
		super();
		ID = iD;
		this.weight = null;
		this.IDfather = new ArrayList<Integer>();
		this.adjacentList = new ArrayList<Node>();
		this.adjacentWeights = new ArrayList<Integer>();
	}
	
	//deep copy
	public Node(Node n) {
		this.ID = n.ID;
		this.weight = n.weight;
		this.IDfather = new ArrayList<Integer>(n.IDfather.size());
		for (Integer idfat : n.IDfather) 
			this.IDfather.add(Integer.valueOf(idfat));
		this.adjacentList = new ArrayList<Node>(n.adjacentList.size());
		for(Node edgeadj : n.adjacentList) 
			this.adjacentList.add(edgeadj);
		this.visited = n.visited;
		this.adjacentWeights = new ArrayList<Integer>(n.adjacentWeights.size());
		for(Integer weight : n.adjacentWeights)
			this.adjacentWeights.add(weight);
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

	public ArrayList<Node> getAdjacentList() {
		return adjacentList;
	}

	public void setAdjacentList(ArrayList<Node> adjacentList) {
		this.adjacentList = adjacentList;
	}

	public void updateAdjacentList(Node node, Integer weight) {
		this.adjacentList.add(node);
		this.adjacentWeights.add(weight);
	}

	public void setAdjacentWeights(ArrayList<Integer> adjacentList) {
		this.adjacentWeights = adjacentList;
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

	public ArrayList<Integer> getAdjacentWeights(){
		return adjacentWeights;
	}

	public void updateAdjacentWeight(Integer ID, Integer weight){
		for(int i = 0; i < adjacentWeights.size(); i++)
			if(adjacentList.get(i).getID() == ID)
				adjacentWeights.set(i, weight);
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

}
