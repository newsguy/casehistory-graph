/**
 * 
 */
package com.casehistory.graph.core;

/**
 * @author Abhinav Tripathi
 */
public interface IndexReader<T, U> extends Runnable {

	public Index<T, U> read();
	
}
