/**
 * 
 */
package com.casehistory.graph.core;

/**
 * @author Abhinav Tripathi
 */
public interface IndexWriter<T, U> extends Runnable {
	
	public void write(Index<T, U> index);

}
