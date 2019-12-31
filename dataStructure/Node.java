package dataStructure;

import java.io.Serializable;

import utils.Point3D;

/**
 * This class create Node and the set of operations applicable on a 
 * node (vertex) in a (directional) weighted graph.
 * @author Gofna and Maor
 *
 */

public class Node implements node_data, Serializable {
	private int key;
	private Point3D location;
	private double weight;
	private String info; //from where we came from at the shortest path function
	private int tag; //the minimum cost we have reach at the current node
	private boolean visited;
	
	/**
	 * this is a default constructor but **should not use** because the limitation for the keys - 
	 * every node have special key. but this contractor build with key = 0  by default.
	 * in addition, because the location is (0,0) by default- and logically we want to build the nodes in different locations in space.
	 */
//	public Node() { 
//		this.key = 0;
//		this.location = new Point3D(0 , 0);
//		this.weight = Double.POSITIVE_INFINITY;
//		this.tag = -1;
//		this.setVisited(false);
//	}
	
	
	/**
	 * constructor
	 * @param key, special key for each node to map the nodes in the HashMap.
	 * @param location, the location of the node.
	 */
	public Node(int key, Point3D location) {
		this.key = key;
		this.location = new Point3D(location);
		this.weight = Double.POSITIVE_INFINITY;
		this.tag = -1;
		this.setVisited(false);
		this.info = "";
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
