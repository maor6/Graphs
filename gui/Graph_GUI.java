package gui;

import java.awt.Color;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.StdDraw;

public class Graph_GUI {

	private graph g;

	public Graph_GUI(graph g) {
		this.g = g;
		StdDraw.setCanvasSize(700, 600);
		StdDraw.setScale(0, 100);
		// }
		
		// public drawGraph() {
		for (node_data n : this.g.getV()) {
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.filledCircle(n.getLocation().x(), n.getLocation().y(), 1.2); //draw circle blue
			StdDraw.text(n.getLocation().x()+2, n.getLocation().y()+2, String.valueOf(n.getKey()));
			for (edge_data e : this.g.getE(n.getKey())) {
				StdDraw.setPenColor(Color.RED);
				StdDraw.line(n.getLocation().x(),n.getLocation().y(),g.getNode(e.getDest()).getLocation().x(), g.getNode(e.getDest()).getLocation().y());
				
				StdDraw.setPenColor(Color.BLACK);
				double x = (n.getLocation().x()+g.getNode(e.getDest()).getLocation().x())/2;
				double y = (n.getLocation().y()+g.getNode(e.getDest()).getLocation().y())/2;
				StdDraw.text(x+0.5, y+0.5, String.valueOf(e.getWeight()));
				
				StdDraw.setPenRadius();
				StdDraw.setPenColor(Color.YELLOW);
				 double x1 = 0.1*n.getLocation().x()+0.9*g.getNode(e.getDest()).getLocation().x();
                 double y1 = 0.1*n.getLocation().y()+0.9*g.getNode(e.getDest()).getLocation().y();
				StdDraw.filledCircle(x1, y1, 0.8);
			}
		}

	}

}
