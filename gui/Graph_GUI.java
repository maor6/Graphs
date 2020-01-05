package gui;

import java.awt.Color;
import java.util.Collection;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Range;
import utils.StdDraw;

public class Graph_GUI implements Runnable {

	private graph g = new DGraph();
	private Graph_Algo ga = new Graph_Algo();
	private int count;
    private static Range Rx= new Range(0,0);
    private static Range Ry= new Range(0,0);

	public Graph_GUI(graph g) {
		this.g = g;
		this.ga.init(g);
		StdDraw.gui = this;
		StdDraw.ga = this.ga;
		initGUI();
		this.count = this.g.getMC();
		Thread t1 = new Thread(this);
		t1.start();

	}

	public void initGUI() {
		StdDraw.clear();
		StdDraw.setCanvasSize(700, 600);
		Rx = findRangeX();
        Ry = findRangeY();
        StdDraw.setXscale(Rx.get_min()-50,Rx.get_max()+50);
        StdDraw.setYscale(Ry.get_min()-50,Ry.get_max()+50);

		if (this.g != null) {
			for (node_data n : this.g.getV()) {
				StdDraw.setPenColor(Color.BLUE);
				StdDraw.filledCircle(n.getLocation().x(), n.getLocation().y(), 1.2); // draw circle blue
				StdDraw.text(n.getLocation().x() + 2, n.getLocation().y() + 2, String.valueOf(n.getKey()));
				if (this.g.getE(n.getKey()) != null) {
					for (edge_data e : this.g.getE(n.getKey())) {
						StdDraw.setPenColor(Color.RED);
						StdDraw.line(n.getLocation().x(), n.getLocation().y(), g.getNode(e.getDest()).getLocation().x(),
								g.getNode(e.getDest()).getLocation().y());

						StdDraw.setPenColor(Color.BLACK);
						double x = (n.getLocation().x() + g.getNode(e.getDest()).getLocation().x()) / 2;
						double y = (n.getLocation().y() + g.getNode(e.getDest()).getLocation().y()) / 2;
						StdDraw.text(x + 0.5, y + 0.5, String.valueOf(e.getWeight()));

						StdDraw.setPenRadius();
						StdDraw.setPenColor(Color.GREEN);
						double x1 = 0.1 * n.getLocation().x() + 0.9 * g.getNode(e.getDest()).getLocation().x();
						double y1 = 0.1 * n.getLocation().y() + 0.9 * g.getNode(e.getDest()).getLocation().y();
						StdDraw.filledCircle(x1, y1, 0.8);
					}
				}
			}
		}

	}

	public void save(String filename) {
		this.ga.save(filename);
	}

	public void init(String filename) {
		this.ga.init(filename);
		this.g = ga.getGr();
	}

	public double drawShotrestPath(int src, int dest) {
		initGUI();
		java.util.List<node_data> l = this.ga.shortestPath(src, dest);
		if (l != null) {
			for (int i = 0; i < l.size() - 1; i++) {

				StdDraw.setPenColor(Color.CYAN);
				StdDraw.line(l.get(i).getLocation().x(), l.get(i).getLocation().y(), l.get(i + 1).getLocation().x(),
						l.get(i + 1).getLocation().y());
			}
		}
		double weight = ga.shortestPathDist(src, dest);
		return weight;
	}

	public boolean isConnect() {
		initGUI();
		return this.ga.isConnected();
	}

	public List<node_data> drawTSP(List<Integer> targets) {

		List<node_data> list = this.ga.TSP(targets);

		for (int i = 0; i < list.size() - 1; i++) {

			StdDraw.setPenColor(Color.CYAN);
			StdDraw.line(list.get(i).getLocation().x(), list.get(i).getLocation().y(),
					list.get(i + 1).getLocation().x(), list.get(i + 1).getLocation().y());
		}

		return list;
	}

	public void run() {
		try {
			while (true) {
				if (this.count != this.g.getMC()) {
					initGUI();
					
					this.count = this.g.getMC();
				}
			}
		} catch (Exception e) {
		}

	}
	
	  public Range findRangeX(){
	        if(g.nodeSize()!=0) {
	            double min = Integer.MAX_VALUE;
	            double max = Integer.MIN_VALUE;
	            Collection<node_data> V = g.getV();
	            for (node_data node : V) {
	                if (node.getLocation().x() > max) 
	                	max = node.getLocation().x();
	                if (node.getLocation().x() < min) 
	                	min = node.getLocation().x();
	            }
	            Range ans = new Range(min, max);
	            Rx = ans;
	            return ans;
	        }
	        else{
	            Range Default = new Range(-100,100);
	            Rx=Default;
	            return Default;
	        }
	    }
	    public Range findRangeY(){
	        if(g.nodeSize()!=0) {
	            double min = Integer.MAX_VALUE;
	            double max = Integer.MIN_VALUE;
	            Collection<node_data> V = g.getV();
	            for (node_data node : V) {
	                if (node.getLocation().y() > max)
	                	max = node.getLocation().y();
	                if (node.getLocation().y() < min) 
	                	min = node.getLocation().y();
	            }
	            Range ans = new Range(min, max);
	            Ry = ans;
	            return ans;
	        }
	        else{
	            return new Range(-100,100);
	        }
	    }

}
