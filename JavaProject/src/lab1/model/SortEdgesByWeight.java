package lab1.model;

import java.util.Comparator;
/**
 * @deprecated
 * class used just for testing purposes
 */
public class SortEdgesByWeight implements Comparator<Edge> {
    @Override
    public int compare(Edge arg0, Edge arg1) {
	return Integer.compare(arg0.getWeight(), 
		arg1.getWeight());
    }
}