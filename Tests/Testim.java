package Tests;

import java.util.LinkedList;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

public class Testim {

	public static void main(String[] args) {
		// test1();
		test2();

	}

	public static void test1() {
		graph g = new DGraph();

		for (int i = 0; i < 10; i++) {
			Point3D p = new Point3D((Math.random() * (90 - (-100) + 1) + (-100)),
					(Math.random() * (90 - (-100) + 1) + (-100)));
			Node n = new Node(i, p);
			g.addNode(n);

		}

		for (int i = 0; i < 10; i++) {
			g.connect((int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10));
		}
		Graph_GUI g3 = new Graph_GUI(g);
		Graph_Algo ga = new Graph_Algo();
		ga.init(g);
		System.out.println(ga.isConnected());
		int src = (int) (Math.random() * 10);
		int dest = (int) (Math.random() * 10);
		System.out.println(ga.shortestPathDist(src, dest));
		System.out.println("src  : " + src + "dest   : " + dest);

	}

	public static void test2() {
		graph g = new DGraph();

		g.addNode(new Node(0, new Point3D(0, -40)));
		g.addNode(new Node(1, new Point3D(10, 30)));
		g.addNode(new Node(2, new Point3D(30, -10)));
		g.addNode(new Node(3, new Point3D(40, 20)));
		g.addNode(new Node(4, new Point3D(50, 1)));
		g.addNode(new Node(5, new Point3D(60, 70)));
		g.addNode(new Node(6, new Point3D(65, -12)));

		g.connect(0, 1, 20);
		g.connect(1, 2, 20);
		g.connect(0,4, 20);
		g.connect(4, 1, 20);
		g.connect(2, 0, 20);
		g.connect(1, 5, 20);
		g.connect(5, 6, 20);
		g.connect(6, 3, 20);
		g.connect(3, 0, 20);
		g.connect(3, 5, 20);

		Graph_Algo ga = new Graph_Algo(g);

		Graph_GUI gu = new Graph_GUI(g);
		List<Integer> l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(0);
		l.add(4);
		l.add(5);
		List<node_data> ln = ga.TSP(l);
		if (ln != null) {
			for (node_data n : ln) {
				System.out.println(n.getKey());

			}
		} else {
			System.out.println("none");
		}

	}
}