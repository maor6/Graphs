package Tests;

import java.util.Collection;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

public class DgraphTest {

	public static void main(String[] args) {
		
		graph g = new DGraph();
		Point3D p = new Point3D(2.3, 3.3);
		Point3D p1 = new Point3D(50, 55);
		Point3D p2 = new Point3D(90, 10);
		Point3D p3 = new Point3D(30, 70.4);
		Point3D p4 = new Point3D(20, 14);
		Point3D p5 = new Point3D(60, 80);
		Node n = new Node(0, p);
		Node n1 = new Node(1, p1);
		Node n2 = new Node(2, p2);
		Node n3 = new Node(3, p3);
		Node n4 = new Node(4, p4);
		Node n5 = new Node(5, p5);
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		//g.removeNode(0);
		//g.removeNode(0);
		g.connect(n.getKey(), n1.getKey(), 2);
		g.connect(n.getKey(), n2.getKey(), 6);
		g.connect(n2.getKey(), n3.getKey(), 3);
		g.connect(n3.getKey(), n4.getKey(), 1);
		g.connect(n4.getKey(), n.getKey(), 1);
		g.connect(n1.getKey(), n5.getKey(), 1);
		g.connect(n5.getKey(), n3.getKey(), 1);
		System.out.println();
		System.out.println(g.getMC());
		
		Graph_Algo g1 = new Graph_Algo();
		g1.init(g);
		//g1.save("maorgraph.txt");
		
		graph g2 = g1.copy();
		
		Collection<edge_data> e = g.getE(n3.getKey());
		
		
		System.out.println(g1.isConnected());
		
		
		
		System.out.println("shortest is :" + g1.shortestPathDist(5,1));
		
		
		List<node_data> l = g1.shortestPath(5, 1);
		
		for (node_data q : l) {
			System.out.print(q.getKey() + ",");
		}
		
//		graph gofna = new DGraph();
//		
//		Graph_Algo g2 = new Graph_Algo();
//		g2.init(gofna);
//		
//		g2.init("maorgraph.txt");
		
		Graph_GUI g3 = new Graph_GUI(g);
		
		
		
		
		
		
	}
}
