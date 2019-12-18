package Tests;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

public class DgraphTest {

	public static void main(String[] args) {
		
		DGraph g = new DGraph();
		Point3D p = new Point3D(2.3, 3.3);
		Node n = new Node(0, p, 2, 2);
		Node n2 = new Node(1, p, 2, 2);
		Node n3 = new Node(2, p, 2, 2);
		Node n4 = new Node(3, p, 2, 2);
		g.addNode(n);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.removeNode(2);
		g.removeNode(2);
		System.out.println();
		System.out.println(g.getMC());
	}
}
