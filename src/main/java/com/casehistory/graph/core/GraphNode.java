/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;

import com.casehistory.graph.core.Query.NewsArticle;

/**
 * @author Abhinav Tripathi
 */
public interface GraphNode extends Serializable {

	public String getName();
	
	public String getQuery();
	
	public NewsArticle[] getBestResults();

}
