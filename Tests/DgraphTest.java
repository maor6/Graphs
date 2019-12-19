package Tests;

import java.util.Collection;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

public class DgraphTest {

	public static void main(String[] args) {
		
		DGraph g = new DGraph();
		Point3D p = new Point3D(2.3, 3.3);
		Node n = new Node(0, p, 2);
		Node n1 = new Node(1, p, 2);
		Node n2 = new Node(2, p, 2);
		Node n3 = new Node(3, p, 2);
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		//g.removeNode(0);
		//g.removeNode(0);
		System.out.println(n2.getKey());
		g.connect(n2.getKey(), n3.getKey(), 10);
		g.connect(n2.getKey(), n.getKey(), 20);
		System.out.println();
		System.out.println(g.getMC());
		
		Collection c = g.getV();
		
	}
}
