package dataStructure;

import utils.Point3D;

public class Node implements node_data {
	private int key;
	private Point3D location;
	private double weight;
	private String info; //from where we came from at the little cost
	private int tag; //the minimum cost we have reach at the current node
	private boolean visited;
	

	public Node(int key, Point3D location) {
		this.key = key;
		this.location = location;
		this.weight = Double.POSITIVE_INFINITY;
		this.tag = -1;
		this.setVisited(false);
	}
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = new Point3D(p);
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
		
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
		
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	

}
