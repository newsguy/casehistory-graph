/**
 * 
 */
package com.casehistory.graph.core;

/**
 * @author Abhinav Tripathi
 */
public class StandardEdge implements Edge {

	private static final long serialVersionUID = 6687020751592856809L;

	private double weight;
	private GraphNode nodeA;
	private GraphNode nodeB;

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public GraphNode[] getNodes() {
		return new GraphNode[] { nodeA, nodeB };
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
