package lab1.model;

import java.util.Comparator;
/**
 * @deprecated
 * class used just for testing purposes
 */
public final class SortNodesByWeight implements Comparator<Node>{
    @Override
    public int compare(Node o1, Node o2) {
	return Integer.compare(o1.getWeight(), 
		o2.getWeight());
    }
}