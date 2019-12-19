package dataStructure;

import utils.Point3D;

public class Node implements node_data {
	private int key;
	private Point3D location;
	private double weight;
	private String info; //from where we came from at the little cost
	private int tag; //the minimum cost we have reach at the current node

	public Node(int key, Point3D location, double weight) {
		this.key = key;
		this.location = location;
		this.weight = weight;
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

//	public HashMap<Integer , edge_data> getEdges() {
//		return this.edges;
//	}

//	public void setEdges(HashMap<Integer , edge_data> edges) {
//		this.edges = edges;
//	}
	

}
