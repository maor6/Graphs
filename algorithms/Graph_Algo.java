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
import java.util.List;

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

	public Graph_Algo() {

	}

	@Override
	public void init(graph g) {
		this.gr = g;
	}

	@Override
	public void init(String file_name) {

		try {
			FileInputStream file = new FileInputStream("file_name.txt");
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

			out.writeObject(this.gr.getV());
			for (node_data n : this.gr.getV()) {
				out.writeObject(this.gr.getE(n.getKey()));

			}

			out.close();
			file.close();

			System.out.println("Object has been serialized");
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	@Override
	public boolean isConnected() {

		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph g = new DGraph();
		for (node_data n : this.gr.getV()) {
			if (n != null) {
				node_data n1 = new Node(n.getKey(), n.getLocation());
				g.addNode(n1);
				
			}
			if(this.gr.getE(n.getKey()) != null){
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

}
