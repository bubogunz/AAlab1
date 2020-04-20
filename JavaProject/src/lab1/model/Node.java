package lab1.model;

import java.util.ArrayList;

public final class Node implements Comparable<Node>{
	private Integer ID = null;
	private Integer weight = null;
	private Node father = null;
	//list of references to adjacent edges 
	private ArrayList<Edge> adjacentList = new ArrayList<Edge>();
	private Boolean visited = false;

	public Node(Integer id){
		ID = id;
	}

	//shallow copy
	public Node(Node n) {
		this.ID = n.ID;
		this.weight = n.weight;
		this.father = n.father;
		this.adjacentList = new ArrayList<Edge>(n.adjacentList.size()); //TODO verificare se si può passare direttamente l'array
		for(Edge edgeadj : n.adjacentList) //senza fare...
			this.adjacentList.add(edgeadj); //...questo
		this.visited = n.visited;
	}
	
	public Boolean hasFather() {
		if(father.getID().equals(null))
			return false;
		return true;
	}
	
	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
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

	public void setVisited(Boolean vis) {
		this.visited = vis;
	}

	public Boolean isVisited() {
		return this.visited;
	}

	public void clear() {
		this.father = null;
		this.visited = false;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((adjacentList == null) ? 0 : adjacentList.hashCode());
		result = prime * result + ((visited == null) ? 0 : visited.hashCode());
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
		if (father == null) {
			if (other.father != null)
				return false;
		} else if (other.father != null 
				&& !father.getID().equals(other.father.getID()))
			return false;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (adjacentList == null) {
			if (other.adjacentList != null)
				return false;
		} else {
			if(adjacentList.size() != other.adjacentList.size())
				return false;
			int i = 0;
			Boolean guard = false;
			while(i < adjacentList.size() && !guard) {
				if(!adjacentList.get(i)
						.equals(other.getAdjacentList().get(i)))
					guard = true;
				++i;
			}
			if(guard == true)
				return false;	
		}
		if (visited == null) {
			if (other.visited != null)
				return false;
		} else if (!visited.equals(other.visited))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "NodeID: " + ID + ", weight: " + weight;
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
