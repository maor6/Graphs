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
 * @param <E>
 *
 */
public class Graph_Algo<E> implements graph_algorithms, Serializable {
	private graph gr;

	public Graph_Algo() { // constructor
		this.gr = new DGraph();
	}

	@Override
	public void init(graph g) {
		this.gr = g;
	}

	@Override
	public void init(String file_name) {
		
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			this.gr = (graph) in.readObject();

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

	@Override
	public void save(String file_name) {
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(this.gr);
			
			out.close();
			file.close();

			System.out.println("Object has been serialized");
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	@Override
	public boolean isConnected() {
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
			if (markVisited(a, normal,a) && markVisited(a, reverse ,a)) {
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
	
	private boolean markVisited(node_data n, graph g , node_data start) {
		if (g.getE(n.getKey()) != null) {
			for (edge_data e : g.getE(n.getKey())) {
				if (!g.getNode(e.getDest()).getInfo().equals("1")) {
					n.setInfo("1");
					g.getNode(e.getDest()).setInfo("1");
					markVisited(g.getNode(e.getDest()), g , start);
				}
			}
		}
		if(n.getKey() == start.getKey()) {
			for (node_data v : g.getV()) {
				if (v.getInfo().equals("")) {
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		graph g2 = this.copy();

		dijekstra(g2, src, dest);
		return g2.getNode(dest).getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		graph g2 = this.copy();
		dijekstra(g2, src, dest);
		List<node_data> l = new LinkedList<node_data>();
		node_data temp = g2.getNode(dest);
		try {
		while (temp.getKey() != src) {//begin from dest-node to src-node
			l.add(temp);
			temp = g2.getNode(temp.getTag());

		}
		l.add(temp);//to add src node
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
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) { // to do
		if( this.isConnected() == false) {
			return null;
		}
		return null;
	}

	@Override
	public graph copy() {
		graph g = new DGraph();
		for (node_data n : this.gr.getV()) {
			if (n != null) {
				node_data n1 = new Node(n.getKey(), n.getLocation()); // creat new node for deep copy
				g.addNode(n1);

			}
			if (this.gr.getE(n.getKey()) != null) {
				for (edge_data e : this.gr.getE(n.getKey())) {
					if (e != null) {
						edge_data e1 = new dataStructure.Edge(e.getSrc(), e.getDest(), e.getWeight());
						g.connect(e1.getSrc(), e1.getDest(), e1.getWeight());
					}
				}
			}
		}

		return g;
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

}
