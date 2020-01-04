package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

/**
 * This empty class represents the set of graph-theory algorithms which should
 * be implemented as part of Ex2 - Do edit this class.
 * 
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable {
	private graph gr;

	/**
	 * default constructor - build empty DGraph
	 */
	public Graph_Algo() {
		this.setGr(new DGraph());
	}

	/**
	 * constructor
	 * 
	 * @param g the graph to run the algorithms
	 */
	public Graph_Algo(graph g) {
		this.setGr(g);
	}

	/**
	 * Init this set of algorithms on the parameter - graph.
	 * 
	 * @param g the graph to run the algorithms
	 */
	public void init(graph g) {
		this.setGr(g);
	}

	/**
	 * Compute a deep copy of this graph.
	 * 
	 * @return the copy of this graph
	 */
	public graph copy() {
		graph g = new DGraph();
		for (node_data n : this.getGr().getV()) {
			if (n != null) {
				node_data n1 = new Node(n.getKey(), n.getLocation()); // create new node for deep copy
				g.addNode(n1);

			}

		}
		for (node_data n : this.getGr().getV()) {
			if (this.getGr().getE(n.getKey()) != null) {
				for (edge_data e : this.getGr().getE(n.getKey())) {
					if (e != null) {
						g.connect(e.getSrc(), e.getDest(), e.getWeight());
					}
				}
			}
		}
		return g;
	}

	/**
	 * Init a graph from file
	 * 
	 * @param file_name
	 */
	public void init(String file_name) {

		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			this.setGr((graph) in.readObject());

			in.close();
			file.close();

			System.out.println("Object has been deserialized");
		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}

	}

	/**
	 * Saves the graph to a file.
	 * 
	 * @param file_name
	 */
	public void save(String file_name) {
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(this.getGr());

			out.close();
			file.close();

			System.out.println("Object has been serialized");
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to
	 * each other node. NOTE: assume directional graph - a valid path (a-->b) does
	 * NOT imply a valid path (b-->a).
	 * 
	 * @return true if the graph is strong connected
	 */
	public boolean isConnected() {
		if (this.getGr().nodeSize() == 0) {
			return true;
		}
		graph normal = this.copy();
		graph reverse = turnEdges(new DGraph(), normal);

		node_data a = null;
		for (node_data n : normal.getV()) { // to find the first node
			if (n != null) {
				a = n;
				break;
			}
		}
		if (a != null) {
			if (markVisited(a, normal, a) && markVisited(a, reverse, a)) {
				return true;
			}
		}
		return false; // if there is no nodes in the graph - not connected
	}

	private graph turnEdges(graph reverse, graph copy) { // reverse all the edges
		for (node_data u : copy.getV()) {
			node_data q = new Node(u.getKey(), u.getLocation());
			reverse.addNode(q);
		}
		for (node_data n : copy.getV()) {
			if (copy.getE(n.getKey()) != null) {
				for (edge_data e : copy.getE(n.getKey())) {
					reverse.connect(e.getDest(), e.getSrc(), e.getWeight());
				}
			}
		}

		return reverse;
	}

	private boolean markVisited(node_data n, graph g, node_data start) {
		if (g.getE(n.getKey()) != null) {
			for (edge_data e : g.getE(n.getKey())) {
				if (!g.getNode(e.getDest()).getInfo().equals("1")) {
					n.setInfo("1");
					g.getNode(e.getDest()).setInfo("1");
					markVisited(g.getNode(e.getDest()), g, start);
				}
			}
		}
		if (n.getKey() == start.getKey()) {
			for (node_data v : g.getV()) {
				if (v.getInfo().equals("")) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * returns the length of the shortest path between src to dest
	 * 
	 * @param src  - start node
	 * @param dest - end (target) node
	 * @return the "weight"(length) of the shortest path.
	 */
	public double shortestPathDist(int src, int dest) {
		graph g2 = this.copy();

		dijekstra(g2, src, dest);
		return g2.getNode(dest).getWeight();
	}

	/**
	 * returns the the shortest path between src to dest - as an ordered List of
	 * nodes: src--> n1-->n2-->...dest see:
	 * https://en.wikipedia.org/wiki/Shortest_path_problem
	 * 
	 * @param src  - start node
	 * @param dest - end (target) node
	 * @return the list of the nodes in the shortest path.
	 */
	public List<node_data> shortestPath(int src, int dest) {
		graph g2 = this.copy();
		dijekstra(g2, src, dest);
		List<node_data> l = new LinkedList<node_data>();
		node_data temp = g2.getNode(dest);
		try {
			while (temp.getKey() != src) {// begin from dest-node to src-node
				l.add(temp);
				temp = g2.getNode(temp.getTag());

			}
			l.add(temp);// to add src node
			int i = l.size() - 1;
			List<node_data> ans = new LinkedList<node_data>();
			ans.add(temp);
			i--;
			while (i != 0) {
				ans.add(l.get(i));
				i--;
			}
			ans.add(l.get(0));
			return ans;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * computes a relatively short path which visit each node in the targets List.
	 * 
	 * @param targets- the list to visit
	 * @return the list of the node we pass and visit to visit all the nodes in the
	 *         targets list
	 */
	public List<node_data> TSP(List<Integer> targets) {
		
		graph con = new DGraph();
		if (this.isConnected() == false) {
			graph sub = new DGraph();
			sub = this.copy();
			for (node_data n : this.getGr().getV()) {// remove all the nodes that not in the targets list
				if (!targets.contains(n.getKey())) {
					sub.removeNode(n.getKey());
				}
			}
			Graph_Algo subA = new Graph_Algo(sub);
			if (subA.isConnected()) {
				con = sub;
			} else {
				return null;
			}
		} else {
			con = this.getGr();
		}
		List<node_data> ans = new LinkedList<>();
		int index = 1;
		for (int i = 0; i < targets.size(); i++) {
			while (index < targets.size() && con.getNode(targets.get(index)).getInfo() == "1") {
				index++;
			}
			if (index == targets.size()) {
				break;
			}
			List<node_data> temp = shortestPath(targets.get(i), targets.get(index));
			for (node_data n : temp) {
				if (n.getKey() == targets.get(i) && i != 0) {
					continue;
				}
				ans.add(n);
				if (targets.contains(n.getKey())) {
					con.getNode(n.getKey()).setInfo("1");
				}
			}

		}
		return ans;
	}

	private void dijekstra(graph g2, int src, int dest) {
		Collection<node_data> c = g2.getV();
		MinHeap m = new MinHeap();
		g2.getNode(src).setWeight(0);
		for (node_data n : c) {
			m.insert(n);
		}

		while (m.getList().size() != 0) {

			Node u = (Node) m.extractMin();

			if (g2.getE(u.getKey()) != null) {
				Collection<edge_data> c2 = g2.getE(u.getKey());
				for (edge_data e : c2) {
					Node v = (Node) g2.getNode(e.getDest());
					if (!v.isVisited()) {
						double w = u.getWeight() + e.getWeight();
						if (w < v.getWeight()) {
							v.setWeight(w);
							v.setTag(u.getKey());// to know the previous node with the lowest cost
							m.buildHeap();
						}
					}
				}
				u.setVisited(true);
			}
		}
	}

	public graph getGr() {
		return gr;
	}

	public void setGr(graph gr) {
		this.gr = gr;
	}

}
