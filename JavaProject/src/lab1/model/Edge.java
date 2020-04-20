package lab1.model;

import java.util.Comparator;

public final class Edge implements Comparable<Edge> {

	public enum Label{
		DISCOVERY_EDGE,
		BACK_EDGE
	}

	private Node node1 = null;
	private Node node2 = null;
	private Integer weight = null;
	private Label label = null;
	
	public Edge() {
		super();
	}
	
	public Edge(Node v1, Node v2, Integer weight) {
		super();
		this.node1 = v1;
		this.node2 = v2;
		this.weight = weight;
		this.label = null;
	}

	public Edge(Edge edge) {
		this.node1 = edge.node1;
		this.node2 = edge.node2;
		this.weight = Integer.valueOf(edge.weight);
		this.label = edge.label;
	}
	
	public Node getOpposite(Node start) {
		return start.getID().equals(this.node1.getID()) 
				? this.node2 
						: this.node1;
	}
	
	public Node getNode1() {
		return node1;
	}

	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	public Node getNode2() {
		return node2;
	}

	public void setNode2(Node node2) {
		this.node2 = node2;

	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
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
		Edge other = (Edge) obj;
		if (label != other.label)
			return false;
		if (node1 == null) {
			if (other.node1 != null)
				return false;
		} else if (!node1.getID().equals(other.node1.getID()))
			return false;
		if (node2 == null) {
			if (other.node2 != null)
				return false;
		} else if (!node2.getID().equals(other.node2.getID()))

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
		return "(" + node1.getID() + ", " + node2.getID() + "; " + weight + ")";
	}

	@Override
	public int compareTo(Edge e) {
		if(weight > e.weight)
			return 1;
		else if(weight == e.weight)
			return 0;
		return -1;

	}
}
