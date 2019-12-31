package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class DgraphTest {
	static graph g = new DGraph();
	
//	//@BeforeAll
//	void createGraph() {
//		graph g = new DGraph();
//	}
	
	@Test
	void AddNodeTest() { // check addNode function and getNode also
		node_data n = new Node(0, new Point3D(1, 2));
		g.addNode(n);
		 node_data expected  = n;
		 node_data actual = g.getNode(0);
		 
		 assertEquals(expected, actual);
	}
	
	@Test
	void AddEdgeTest() { //check connect function and getEdge function also
		node_data n = new Node(0, new Point3D(1, 2));
		node_data n1 = new Node(1, new Point3D(-10, 10));
		g.addNode(n);
		g.addNode(n1);
		edge_data e = new Edge(0, 1, 20);
		g.connect(0, 1, 20);
		edge_data actual = g.getEdge(0, 1);
		edge_data expected = e;
		
		assertEquals(expected.getSrc(), actual.getSrc());
		assertEquals(expected.getDest(), actual.getDest());
		assertEquals(expected.getWeight(), actual.getWeight());
	}
	
	@Test
	void RemoveNodeTest() {
		g.removeNode(0);// after we removed , we checked
		node_data actual = g.getNode(0);
		node_data expected = null;
		
		assertEquals(expected, actual);
	}
	

	@Test
	void RemoveEdgeTest() {
		node_data n = new Node(2, new Point3D(10, 2));
		node_data n1 = new Node(4, new Point3D(-20, 10));
		g.addNode(n);
		g.addNode(n1);
		g.connect(1, 2, 30);
		g.connect(1, 4, 20);
		g.removeEdge(1, 2);
		edge_data actual = g.getEdge(1, 2);
		edge_data expected  = null;
		
		assertEquals(expected, actual,"test if the edge removed");
	}
	
	@Test
	void edgeSizeTest() {
		g.connect(2, 4, 10);
		int actual = g.edgeSize();
		int expected = 2;
		assertEquals(expected, actual);
	}
	
//	@Test //to fix
//	void getMcTest() {
//		int actual = g.getMC();
//		System.out.println(actual);
//		int expected = ;
//		assertEquals(expected, actual);
//	}
}
