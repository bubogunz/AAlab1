package lab1.model;

public final class Edge implements Comparable<Edge>{

    private Node nodeID1;
    private Node nodeID2;
    private Integer weight;

    public Edge() {
	super();
    }

    public Edge(Node v1, Node v2, Integer weight) {
	super();
	this.nodeID1 = new Node(v1);
	this.nodeID2 = new Node(v2);
	this.weight = weight;
    }
    
    public Edge(Edge edge) {
	this.nodeID1 = new Node(edge.nodeID1);
	this.nodeID2 = new Node(edge.nodeID2);
	this.weight = Integer.valueOf(edge.weight);
    }

    public Node getnode1() {
	return nodeID1;
    }

    public void setnodeID1(Integer v1) {
	this.nodeID1.setID(v1);
    }

    public Node getnode2() {
	return nodeID2;
    }

    public void setnodeID2(Integer v2) {
	this.nodeID2.setID(v2);
    }

    public Integer getWeight() {
	return weight;
    }

    public void setWeight(Integer weight) {
	this.weight = weight;
	}

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nodeID1 == null) ? 0 : nodeID1.hashCode());
	result = prime * result + ((nodeID2 == null) ? 0 : nodeID2.hashCode());
	result = prime * result + ((weight == null) ? 0 : weight.hashCode());
	return result;
    }

	// For underected graphs
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Edge other = (Edge) obj;
	// Verify that: (a,b) == (a,b) || (a,b) == (b,a)
	if (nodeID1 != null && nodeID2 != null && other.getnode1() != null && other.getnode2() != null
		&& ( (nodeID1.equals(other.getnode1()) && nodeID2.equals(other.getnode2()))
		|| (nodeID1.equals(other.getnode2()) && nodeID2.equals(other.getnode1())) ) ) {
		return true;
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
