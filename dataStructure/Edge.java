package dataStructure;

import java.io.Serializable;

/**
 * This class create edge and the set of operations applicable on a 
 * directional edge(src,dest) in a (directional) weighted graph.
 * 
 * @author Gofna and Maor
 *
 */
public class Edge implements edge_data, Serializable {
	private int src;
	private int dest;
	private double weight;
	private String info;
	private int tag;

	public Edge() {
		this.src = 0;
		this.dest = 0;
		this.weight = 0;
		this.info = null;
		this.tag = -1;
	}

	public Edge(int s, int d, double w) {
		this.src = s;
		this.dest = d;
		this.weight = w;

	}

	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
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

}
