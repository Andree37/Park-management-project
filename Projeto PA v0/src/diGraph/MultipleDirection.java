package diGraph;

import graph.Edge;
import model.Connection.Type;

public class MultipleDirection<V,E> {

	public boolean eTypeIsUniDirectional(Edge<E,V> edge) {
		if(((model.Connection) edge.element()).getType().equals(Type.BRIDGE.getUnit())) {
			return true;
		}
		return false;
	}
}
