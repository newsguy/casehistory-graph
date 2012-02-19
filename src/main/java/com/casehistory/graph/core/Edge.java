/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;

/**
 * @author Abhinav Tripathi
 */
public interface Edge extends Serializable {

	/**
	 * Retrieves the two nodes which are connected by this edge. Implementers
	 * should ensure that the array is always of size 2.
	 * 
	 * @return the nodes connected by this edge
	 */
	public GraphNode[] getNodes();

	/**
	 * Weights are measure of how strongly related the two nodes connected by
	 * this edge are. The more the weight, the more are the results common for
	 * the nodes.
	 * 
	 * @return weight of the edge
	 */
	public double getWeight();

}
