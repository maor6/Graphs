package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import utils.Range;

/**
 * This class create a directional weighted graph. The class has a road-system
 * or communication network in mind - and should support a large number of nodes
 * (over 100,000). The implementation based on two HashMaps to sure compact
 * representation with a relatively short run time.
 * 
 * first HashMap to save all the nodes. seconds HashMap to save all the edges
 * for each node, and thats why every cell in the edges-HashMap contain new
 * HashMap. the key for every cell in the outer HashMap is the key of the source
 * node in the edge. the key for every cell in every inner HashMap is the key of
 * the destination node in the edge.
 * 
 * @author Gofna and Maor
 */

public class DGraph implements graph, Serializable {
	private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
	private HashMap<Integer, HashMap<Integer, edge_data>> edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	private static int MC = 0;

	public DGraph() {
		this.nodes = new HashMap<Integer, node_data>();
		this.edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	}

	/**
	 * return the node_data by the node_id,
	 * 
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	public node_data getNode(int key) {
		return this.nodes.get(key); // if not exist its return null
	}

	/**
	 * return the data of the edge (src,dest), null if none. this method run in O(1)
	 * time.
	 * 
	 * @param src
	 * @param dest
	 * @return the edge_data by the source and destination nodes, null if none.
	 */
	public edge_data getEdge(int src, int dest) {
		try {
			return this.edges.get(src).get(dest); // if edge not exist should throw NullPointerException
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * add a new node to the graph with the given node_data. this method run in O(1)
	 * time.
	 * if the key is already exist, should throw exception and not replace the previous one.
	 * 
	 * @param n the node to add
	 */
	public void addNode(node_data n) {
		try {
			if (this.nodes.get(n.getKey()) == null) {
				this.nodes.put(n.getKey(), n);
				MC++;
			} else {
				throw new RuntimeException(); // if the node already exist, should throw exception and not replace
												// previous one.
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("this key ia already used");
		}
	}

	/**
	 * Connect an edge with weight w between node src to node dest. this method run
	 * in O(1) time. if one or more of the nodes doesn't exist-don't do anything.
	 * 
	 * @param src  - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w    - positive weight representing the cost (aka time, price, etc)
	 *             between src-->dest.
	 */
	public void connect(int src, int dest, double w) {
		if (this.nodes.get(src) != null && this.nodes.get(dest) != null) { // if one or more of the nodes doesn't exist-
																			// don't do anything.

			Edge e = new Edge(src, dest, w);
			if (this.edges.get(src) == null) { // add a new hash if there is no any edge from this key yet
				this.edges.put(src, new HashMap<Integer, edge_data>()); // the key is the source key
			}
			this.edges.get(src).put(dest, e); // add the edge to the hash by key (the key is destination key)
			MC++;
		}
	}

	/**
	 * This method return a pointer (shallow copy) for the collection representing
	 * all the nodes in the graph.
	 * 
	 * @return Collection<node_data>
	 */
	public Collection<node_data> getV() {
		return this.nodes.values();
	}

	/**
	 * This method return a pointer (shallow copy) for the collection representing
	 * all the edges getting out of the given node (all the edges starting (source)
	 * at the given node).
	 * 
	 * @return Collection<edge_data>
	 */
	public Collection<edge_data> getE(int node_id) {
		if (this.edges.get(node_id) != null) { // if there is edges to this node
			return this.edges.get(node_id).values();
		}
		return null;
	}

	/**
	 * Delete the node (with the given ID) from the graph - and removes all edges
	 * which starts or ends at this node. This method run in O(n), |V|=n, as all the
	 * edges should be removed.
	 * 
	 * @param key, the node key
	 * @return the data of the removed node (null if none).
	 */
	public node_data removeNode(int key) {

		node_data removed = this.nodes.get(key);

		if (removed != null) { // ask if this node exist.
			LinkedList<node_data> list = new LinkedList<node_data>(this.nodes.values());
			for (node_data n : list) {
				if (this.edges.get(n.getKey()) != null) {
					this.edges.get(n.getKey()).remove(key); // delete the edges that this node is the destination.
				}
			}
			this.edges.remove(key); // delete the edges that this node is the source.
			this.nodes.remove(key); // delete the node.
			MC++;
			return removed;
		}
		return null;

	}

	/**
	 * Delete the edge from the graph, this method run in O(1) time.
	 * 
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	public edge_data removeEdge(int src, int dest) {
		if (this.edges.get(src) != null) {
			edge_data removed = this.edges.get(src).get(dest);
			if (removed != null) {
				this.edges.get(src).remove(dest);
				MC++;
				return removed;
			}
		}
		return null;
	}

	/**
	 * @return the number of vertices (nodes) in the graph.
	 */
	public int nodeSize() {
		return this.nodes.size();
	}

	/**
	 * @return the number of edges (assume directional graph).
	 */
	public int edgeSize() {
		int count = 0;
		LinkedList<HashMap<Integer, edge_data>> list = new LinkedList<HashMap<Integer, edge_data>>(this.edges.values());
		for (HashMap<Integer, edge_data> h : list) {
			count = count + h.size();
		}
		return count;
	}

	/**
	 * @return the Mode Count - for testing changes in the graph.
	 */
	public int getMC() {
		return MC;
	}

}
