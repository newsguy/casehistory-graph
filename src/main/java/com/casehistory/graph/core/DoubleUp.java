/**
 * 
 */
package com.casehistory.graph.core;

/**
 * A simple {@link ResizePolicy}, which doubles up the length of the square
 * matrix and returns the new bigger matrix with original data.
 * 
 * @author Abhinav Tripathi
 */
public class DoubleUp implements ResizePolicy {

	@Override
	public int[][] resize(int[][] matrix) {
		int[][] newMatrix = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				newMatrix[i][j] = matrix[i][j];
			}
		}

		return newMatrix;
	}

}
