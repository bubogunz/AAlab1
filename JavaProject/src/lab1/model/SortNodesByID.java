package lab1.model;

import java.util.Comparator;

public final class SortNodesByID implements Comparator<Node>{
    @Override
    public int compare(Node o1, Node o2) {
	return Integer.compare(o1.getID().intValue(), 
		o2.getID().intValue());
    }
}