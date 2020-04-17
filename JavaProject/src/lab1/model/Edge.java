package lab1.model;

public final class Edge {

	public enum Label{
		DISCOVERY_EDGE,
		BACK_EDGE
	}

	private Node nodeID1 = null;
	private Node nodeID2 = null;
	private Integer weight = null;
	private Label label = null;
	private Node ancestor = null;

	public Edge(Node v1, Node v2, Integer weight) {
		super();
		this.nodeID1 = v1;
		this.nodeID2 = v2;
		this.weight = weight;
		this.label = null;
		this.ancestor = null;
	}

	public Edge(Edge edge) {
		this.nodeID1 = edge.nodeID1;
		this.nodeID2 = edge.nodeID2;
		this.weight = Integer.valueOf(edge.weight);
		this.label = edge.label;
		this.ancestor = edge.ancestor == null 
				? null : edge.getAncestor();
	}

	public Node getnodeID1() {
		return nodeID1;
	}

	public void setnodeID1(Node v1) {
		this.nodeID1 = v1;
	}

	public Node getnodeID2() {
		return nodeID2;
	}

	public void setnodeID2(Node v2) {
		this.nodeID2 = v2;
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

	public Node getAncestor() {
		return ancestor;
	}

	public void setAncestor(Node ancestor) {
		this.ancestor = ancestor;
	}
	
	public void clear() {
		this.label = null;
		this.ancestor = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ancestor == null) ? 0 : ancestor.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((nodeID1 == null) ? 0 : nodeID1.hashCode());
		result = prime * result + ((nodeID2 == null) ? 0 : nodeID2.hashCode());
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
		if (ancestor == null) {
			if (other.ancestor != null)
				return false;
		} else if (!ancestor.equals(other.ancestor))
			return false;
		if (label != other.label)
			return false;
		if (nodeID1 == null) {
			if (other.nodeID1 != null)
				return false;
		} else if (!nodeID1.equals(other.nodeID1))
			return false;
		if (nodeID2 == null) {
			if (other.nodeID2 != null)
				return false;
		} else if (!nodeID2.equals(other.nodeID2))
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
		return "(" + nodeID1 + ", " + nodeID2 + "; " + weight + ")";
	}
}
