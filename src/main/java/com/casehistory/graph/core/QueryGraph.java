/**
 * 
 */
package com.casehistory.graph.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.casehistory.graph.core.Query.NewsArticle;
import com.casehistory.graph.option.None;
import com.casehistory.graph.option.Some;

/**
 * A concrete {@link AbstractGraph}, which acts as a persistent store for the
 * {@link Query} nodes and interconnections between them.
 * 
 * @author Abhinav Tripathi
 */
public class QueryGraph extends AbstractGraph {

	private static final long serialVersionUID = -7435720926935182932L;

	public QueryGraph() {
		super();
	}

	@Override
	public void addNode(GraphNode node) {

	}

	public void addNode(String[] queryTerms) {
		GraphNode queryNode = new Query(new Some<String[]>(queryTerms), new None<Map<String, Set<NewsArticle>>>());
		List<GraphNode> neighbours = findNeighbours(queryNode);
	}

	/**
	 * When a new node is added to the graph, we need to link it (if possible)
	 * to the nodes that are similar to it.
	 * 
	 * TODO: this should be an injectable algorithm!
	 * 
	 * @param queryNode
	 * @return
	 */
	private List<GraphNode> findNeighbours(GraphNode queryNode) {
		return null;
	}

}
