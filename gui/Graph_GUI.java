package gui;

import java.awt.Color;
import java.awt.List;
import algorithms.Graph_Algo;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.StdDraw;

public class Graph_GUI<E> {

	private graph g;

	public Graph_GUI(graph g) {
		this.g = g;
		StdDraw.setCanvasSize(700, 600);
		StdDraw.setScale(-100, 100);
		// }
		
		// public drawGraph() {
		for (node_data n : this.g.getV()) {
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.filledCircle(n.getLocation().x(), n.getLocation().y(), 1.2); //draw circle blue
			StdDraw.text(n.getLocation().x()+2, n.getLocation().y()+2, String.valueOf(n.getKey()));
			if(this.g.getE(n.getKey()) != null) {
				for (edge_data e : this.g.getE(n.getKey())) {
					StdDraw.setPenColor(Color.RED);
					StdDraw.line(n.getLocation().x(),n.getLocation().y(),g.getNode(e.getDest()).getLocation().x(), g.getNode(e.getDest()).getLocation().y());
					
					StdDraw.setPenColor(Color.BLACK);
					double x = (n.getLocation().x()+g.getNode(e.getDest()).getLocation().x())/2;
					double y = (n.getLocation().y()+g.getNode(e.getDest()).getLocation().y())/2;
					StdDraw.text(x+0.5, y+0.5, String.valueOf(e.getWeight()));
					
					StdDraw.setPenRadius();
					StdDraw.setPenColor(Color.GREEN);
					 double x1 = 0.1*n.getLocation().x()+0.9*g.getNode(e.getDest()).getLocation().x();
	                 double y1 = 0.1*n.getLocation().y()+0.9*g.getNode(e.getDest()).getLocation().y();
					StdDraw.filledCircle(x1, y1, 0.8);
				}
			}
			
		}
	}
	
	public Graph_Algo<E> init(graph g){
		Graph_Algo<E> ga = new Graph_Algo<E>();
		ga.init(g);
		return ga; 
	}
	
	public double drawShotrestPath(int src , int dest) {
		Graph_Algo<E> ga = this.init(g);
		java.util.List<node_data> l = ga.shortestPath(src, dest);
		for (int i = 0 ; i < l.size()-1 ; i++) {
			//edge_data e = this.g.getEdge(l.get(i).getKey(), l.get(i+1).getKey());
			StdDraw.setPenColor(Color.CYAN);
			StdDraw.line(l.get(i).getLocation().x(),l.get(i).getLocation().y(),l.get(i+1).getLocation().x(),
					l.get(i+1).getLocation().y());
		}
		double weight = ga.shortestPathDist(src, dest);
		return weight;
	}
	
	public void isConnect() {
		Graph_Algo<E> ga = this.init(g);
		String s = "is connected ?:"+ga.isConnected();
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(-80,90 , s);
	}
}
