/**
 * 
 */
package com.casehistory.graph.core;

/**
 * A method to resize the adjacency matrix when its nearing capacity.
 * 
 * @author Abhinav Tripathi
 */
public interface ResizePolicy {

	/**
	 * Resize the given matrix, while maintaining original data. Implementers
	 * should try to ensure there is no loss of original data after the resize
	 * and that resizing is infrequent.
	 * 
	 * @param matrix
	 */
	public int[][] resize(int[][] matrix);

}
