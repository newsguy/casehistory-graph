/**
 * 
 */
package com.casehistory.graph.core;

/**
 * @author Abhinav Tripathi
 */
public interface IndexReader<T, U> {

	public void read(Index<T, U> index);
	
}
