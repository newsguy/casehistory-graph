/**
 * 
 */
package com.casehistory.graph.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.casehistory.graph.core.Query.NewsArticle;
import com.casehistory.graph.option.None;
import com.casehistory.graph.option.Some;
import com.casehistory.graph.utils.DirectoryUtil;

/**
 * A concrete {@link AbstractGraph}, which acts as a persistent store for the
 * {@link Query} nodes and interconnections between them.
 * 
 * @author Abhinav Tripathi
 */
public class QueryGraph extends AbstractGraph {

	private static final long serialVersionUID = -7435720926935182932L;

	// TODO: read from configuration
	private final String baseDirectory = "";

	public QueryGraph() {
		super();
	}

	@Override
	public void addNode(GraphNode node) {

	}

	@Override
	public void addNode(String[] queryTerms) throws NoSuchAlgorithmException {
		// add the node to the in-memory graph
		GraphNode queryNode = new Query(new Some<String[]>(queryTerms), new None<Map<String, Set<NewsArticle>>>());
		nodes.add(queryNode);
		representation.add(queryNode, findNeighbours(queryNode));

		// add the node to the index

		persist(queryNode);

		// persist the index (batch op)

	}

	private void persist(GraphNode queryNode) {
		String path = baseDirectory + "/" + Constants.QUERY_NODES_DIR + "/" + queryNode.getFileName();
		if (!DirectoryUtil.pathExists(path)) {
			DirectoryUtil.build(path, true);
		}
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(path);
			out = new ObjectOutputStream(fos);
			out.writeObject(queryNode);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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
