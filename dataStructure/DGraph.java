package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class DGraph implements graph, Serializable {
	private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
	private HashMap<Integer, HashMap<Integer, edge_data>> edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	private static int MC = 0;

	public DGraph() {
		this.nodes = new HashMap<Integer, node_data>();
		this.edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	}

	@Override
	public node_data getNode(int key) {
		return this.nodes.get(key); // if not exist its return null?
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		try {
			return this.edges.get(src).get(dest); // if edge not exist should throw exception
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public void addNode(node_data n) {
		try {
			if (this.nodes.get(n.getKey()) == null) {
				this.nodes.put(n.getKey(), n);
				MC++;
			} else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("this key ia already used");

		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		Edge e = new Edge(src, dest, w);
		if (this.edges.get(src) == null) { // add a new hash if there is no any edge from this key yet
			this.edges.put(src, new HashMap<Integer, edge_data>()); // the key is src

		}
		this.edges.get(src).put(dest, e); // add the edge to the hash by key (the key is dest)
		MC++;

	}

	@Override
	public Collection<node_data> getV() {
		return this.nodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) { // ???is key?
		if (this.edges.containsKey(node_id)) {
			return this.edges.get(node_id).values();
		}
		return null;
	}

	@Override
	public node_data removeNode(int key) {// to fix
		node_data removed = this.nodes.get(key);
		LinkedList<node_data> list = new LinkedList<node_data>(this.nodes.values());
		for (node_data n : list) {
			if (this.edges.get(n.getKey()) != null) {
				this.edges.get(n.getKey()).remove(key);
			}
		}
		if (removed != null) { // ask if this node exist
			this.edges.remove(key);
			this.nodes.remove(key);
			MC++;
			return removed;
		}
		return null;

	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data removed = this.edges.get(src).get(dest);
		if (removed != null) {
			this.edges.get(src).remove(dest);
			MC++;
			return removed;
		}
		return null;
	}

	@Override
	public int nodeSize() {
		return this.nodes.size();
	}

	@Override
	public int edgeSize() {
		int count = 0;
		LinkedList<HashMap<Integer, edge_data>> list = new LinkedList<HashMap<Integer, edge_data>>(this.edges.values());
		for (HashMap<Integer, edge_data> h : list) {
			count = count + h.size();
		}
		return count;
	}

	@Override
	public int getMC() {
		return MC;
	}

}
