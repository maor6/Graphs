package Tests;

import java.util.Collection;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class DgraphTest {

	public static void main(String[] args) {
		
		graph g = new DGraph();
		Point3D p = new Point3D(2.3, 3.3);
		Node n = new Node(0, p);
		Node n1 = new Node(1, p);
		Node n2 = new Node(2, p);
		Node n3 = new Node(3, p);
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		//g.removeNode(0);
		//g.removeNode(0);
		System.out.println(n2.getKey());
		g.connect(n.getKey(), n1.getKey(), 2);
		g.connect(n.getKey(), n2.getKey(), 6);
		g.connect(n1.getKey(), n2.getKey(), 3);
		g.connect(n.getKey(), n3.getKey(), 1);
		g.connect(n3.getKey(), n2.getKey(), 1);
		g.connect(n2.getKey(), n.getKey(), 1);
		System.out.println();
		System.out.println(g.getMC());
		
		Graph_Algo g1 = new Graph_Algo();
		g1.init(g);
		g1.save("maorgraph.txt");
		
		graph g2 = g1.copy();
		
		Collection<edge_data> e = g.getE(n3.getKey());
		
		
		
		
		
		System.out.println("shortest is :" + g1.shortestPathDist(2, 0));
		
		System.out.println(g1.isConnected());
		
		
		
//		graph gofna = new DGraph();
//		
//		Graph_Algo g2 = new Graph_Algo();
//		g2.init(gofna);
//		
//		g2.init("maorgraph.txt");
		
		
		
		
		
	}
}
