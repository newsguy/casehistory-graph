/**
 * 
 */
package com.casehistory.graph.core;

/**
 * @author Abhinav Tripathi
 */
public interface IndexWriter<T, U> {
	
	public void write(Index<T, U> index);

}
