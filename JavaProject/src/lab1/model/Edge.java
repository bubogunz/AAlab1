package lab1.model;

public final class Edge {

	public enum Label{
		DISCOVERY_EDGE,
		BACK_EDGE
	}

	private Node nodeID1;
	private Node nodeID2;
	private Integer weight;
	private Label label;
	private Integer ancestor;


	public Edge() {
		super();
		this.nodeID1 = null;
		this.nodeID2 = null;
		this.weight = null;
		this.label = null;
		this.ancestor = null;
	}

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
				? null 
						: Integer.valueOf(edge.getAncestor());
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

	public Integer getAncestor() {
		return ancestor;
	}

	public void setAncestor(Integer ancestor) {
		this.ancestor = ancestor;
	}
	// Verify that: (a, null) == (a, null) || (a, null) == (null, a)
	else if ( ((nodeID1 != null && nodeID2 == null) || (nodeID1 == null && nodeID2 != null))
		&& ((other.getnode1() != null && other.getnode2() == null) || (other.getnode1() == null && other.getnode2() != null))
		&& (nodeID1.equals(other.getnode1()) || nodeID1.equals(other.getnode2()))
		|| nodeID2.equals(other.getnode1()) || nodeID2.equals(other.getnode2())) {
		return true;
	}
	// Verify that: (null, null) == (null, null)
	else if (nodeID1 == null && nodeID2 == null && other.getnode1() == null && other.getnode2() == null) {
		return true;
	}

	return false;
	}
	
	@Override
	public int compareTo(Edge edge) {
		if(this.weight > edge.weight) {
			return 1;
		} else if(this.weight < edge.weight) {
			return -1;
		}
		return 0;
	}
}
