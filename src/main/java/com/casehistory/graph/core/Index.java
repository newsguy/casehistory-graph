/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;

import com.casehistory.graph.core.AbstractGraph.NodeCreationListener;

/**
 * @author Abhinav Tripathi
 */
public interface Index<T, U> extends Serializable, NodeCreationListener, HashFileNamed {
	
	public void insert(T key, U data);
	
	public U search(T key);
	
	public void registerReader(IndexReader<T,U> reader);
	
	public void unregisterReader(IndexReader<T, U> reader);
	
	public void registerWriter(IndexWriter<T, U> reader);
	
	public void unregisterWriter(IndexWriter<T, U> writer);

}
