/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;

/**
 * @author Abhinav Tripathi
 */
public interface Index<T, U> extends Serializable {
	
	public void insert(T key, U data);
	
	public U search(T key);

}
