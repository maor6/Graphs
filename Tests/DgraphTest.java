package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

class DgraphTest {
	static graph g = new DGraph();
	
	@BeforeAll
	public static void createGraph() {
		node_data n = new Node(0, new Point3D(0, 50));
		node_data n1 = new Node(1, new Point3D(20, 20));
		node_data n2 = new Node(2, new Point3D(10, 5));
		node_data n3 = new Node(3, new Point3D(-10, -10));
		node_data n4 = new Node(4, new Point3D(-30, 25));
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.connect(0, 1, 40);
		g.connect(1, 2, 10);
		g.connect(2, 3, 90);
		g.connect(3, 4, 14);
		g.connect(3, 0, 75);
		g.connect(3, 1, 5);
		g.connect(4, 0, 12);
	}
	
	@Test
	void AddNodeTest() { // check addNode function and getNode also
		node_data n5 = new Node(5, new Point3D(30, 30));
		g.addNode(n5);
		node_data expected  = n5;
		node_data actual = g.getNode(5);
		 
		 assertEquals(expected, actual);
	}
	
	@Test
	void AddEdgeTest() { //check connect function and getEdge function also
		node_data n6 = new Node(6, new Point3D(30, 30));
		g.addNode(n6);
		edge_data e = new Edge(0, 6, 20);
		g.connect(0, 6, 20);
		edge_data actual = g.getEdge(0, 6);
		edge_data expected = e;
		
		assertEquals(expected.getSrc(), actual.getSrc());
		assertEquals(expected.getDest(), actual.getDest());
		assertEquals(expected.getWeight(), actual.getWeight());
	}
	
	
	@Test
	void RemoveNodeTest() {
		g.removeNode(6);// after we removed , we checked
		node_data actual = g.getNode(6);
		node_data expected = null;
		
		assertEquals(expected, actual);
	}
	

	@Test
	void RemoveEdgeTest() {
		g.removeEdge(3, 1);
		edge_data actual = g.getEdge(3, 1);
		edge_data expected  = null;
		
		assertEquals(expected, actual,"test if the edge removed");
	}
	
	@Test
	void edgeSizeTest() {
		int actual = g.edgeSize();
		int expected = 6;
		assertEquals(expected, actual);
	}
	
	@Test 
	void getMcTest() {
		int actual = g.getMC();
		System.out.println(actual);
		int expected = 16;
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testgraph() {
		boolean ans = drawGraph(g);
		assertTrue(ans);
	}
	boolean drawGraph(graph gr) { 
		Graph_GUI g1 = new  Graph_GUI(gr);
		return true;
		
	}
}
