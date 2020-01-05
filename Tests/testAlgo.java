package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class testAlgo {

	static graph g = new DGraph();
	static Graph_Algo GA = new Graph_Algo(g);

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
		g.connect(0, 3, 75);
		g.connect(3, 1, 5);
		g.connect(4, 0, 12);
	}

	@Test
	void copyTest() {
		graph copy = testAlgo.GA.copy();
		boolean b = (copy == testAlgo.g);
		assertFalse(b);
		assertEquals(copy.nodeSize(), testAlgo.g.nodeSize());

	}

	@Test
	void initTest() {
		testAlgo.GA.init("graph");
	}
	@Test
	void saveTest() {
		testAlgo.GA.save("graph");
	}
	
	@Test
	void isConnectedTest() {
		assertTrue(testAlgo.GA.isConnected());
		testAlgo.g.removeEdge(4, 0);
		testAlgo.GA.init(g);
		assertFalse(testAlgo.GA.isConnected());
	}
	
	@Test
	void shortestPathDistTest() {
		testAlgo.GA.init(g);
		double actual = testAlgo.GA.shortestPathDist(0, 4);
		double expected = 89;
		assertEquals(expected, actual);
	}
	
	@Test
	void shortestPathTest() {
		List<node_data> n = testAlgo.GA.shortestPath(0, 4);
		List<node_data> expected = new LinkedList<node_data>();
		expected.add(g.getNode(0));
		expected.add(g.getNode(3));
		expected.add(g.getNode(4));
		for (int i = 0; i < n.size(); i++) {
			assertEquals(n.get(i).getKey(), expected.get(i).getKey()); //Compare the keys in both list
		}
	}
	
	@Test()
	void TSPtest() {
		List<Integer> targets = new LinkedList<Integer>();
		targets.add(0);
		targets.add(2);
		targets.add(1);
		List<node_data> n = testAlgo.GA.TSP(targets);
		List<node_data> expected = new LinkedList<node_data>();
		expected.add(g.getNode(0));
		expected.add(g.getNode(1));
		expected.add(g.getNode(2));
		expected.add(g.getNode(3));
		expected.add(g.getNode(1));
		for (int i = 0; i < n.size(); i++) {
			assertEquals(n.get(i).getKey(), expected.get(i).getKey()); //Compare the keys in both list
		}
	}
	
}
