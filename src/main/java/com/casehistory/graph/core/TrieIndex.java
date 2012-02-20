/**
 * 
 */
package com.casehistory.graph.core;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Abhinav Tripathi
 */
public class TrieIndex implements Index<String, GraphNode> {

	private static final long serialVersionUID = -7693521259975938450L;

	private final Trie<String, GraphNode> index = new QueryTrie();
	private final Date createdAt;
	private Date lastUpdateAt;

	private static final Logger logger = Logger.getLogger("VERBOSE");

	public TrieIndex() {
		this.createdAt = new Date();
		this.lastUpdateAt = new Date();
		logger.info("Index successfully created at " + createdAt);
	}

	@Override
	public void insert(String key, GraphNode data) {
		logger.info("Inserting " + key + " into the index ...");
		index.insert(key, data);
		logger.info("Inserted " + key + " into the index successfully. Entry points to " + data);
		lastUpdateAt = new Date();
	}

	@Override
	public GraphNode search(String key) {
		logger.info("Index queried for [" + key + "]");
		return index.search(key).getData();
	}

}
