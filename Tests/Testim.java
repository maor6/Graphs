package Tests;


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
		graph g = new DGraph();
		
		for (int i = 0 ; i < 10 ; i++) {
			Point3D p = new Point3D((Math.random()*(90-(-100)+1)+(-100)), (Math.random()*(90-(-100)+1)+(-100)));
			Node n = new Node(i,p);
			g.addNode(n);
		
		}

		for (int i = 0 ; i < 10 ; i++) {
			g.connect((int) (Math.random()*10), (int) (Math.random()*10), (int)(Math.random()*10));
		}
		Graph_GUI g3 = new Graph_GUI(g);
		Graph_Algo ga = new Graph_Algo();
		ga.init(g);
		System.out.println(ga.isConnected());
		int src = (int) (Math.random()*10);
		int dest = (int) (Math.random()*10);
		System.out.println(ga.shortestPathDist(src, dest));
		System.out.println("src  : " + src + "dest   : " + dest);
		
	}
}