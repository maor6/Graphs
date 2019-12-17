package dataStructure;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

public class DGraph implements graph{
	private Hashtable<Integer, Node > nodes = new Hashtable<Integer, Node>();
	
	public DGraph() {
		this.nodes = new Hashtable<Integer, Node>();
		
	}
	
	@Override
	public node_data getNode(int key) {
		return this.nodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return this.nodes.get(src).getEdges().get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.nodes.put(n.getKey(),(Node)n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		Edge e = new Edge(src , dest , w);
		this.nodes.get(src).getEdges().put(dest, e);
		
	}

	@Override
	public Collection<node_data> getV() { // need to fix
		
		Collection c = (Collection) this.nodes;
		return c;
	}

	@Override
	public Collection<edge_data> getE(int node_id) { // need to fix
		Collection c = (Collection) this.nodes.get(node_id).getEdges();
		return c;
	}

	@Override
	public node_data removeNode(int key) {// to fix
		Iterator it = this.nodes.entrySet().iterator();

	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
