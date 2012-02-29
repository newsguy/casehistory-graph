/**
 * 
 */
package com.casehistory.graph.core;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author Abhinav Tripathi
 */
public class TrieIndex implements Index<String, GraphNode> {

	private static final long serialVersionUID = -7693521259975938450L;

	private final Trie<String, GraphNode> index = new QueryTrie();
	private final Date createdAt;
	private Date lastUpdateAt;
	
	private final Set<IndexReader<String, GraphNode>> readers = new HashSet<IndexReader<String, GraphNode>>();
	private final Set<IndexWriter<String, GraphNode>> writers = new HashSet<IndexWriter<String, GraphNode>>();

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

	@Override
	public void nodeAdded(GraphNode node) {
		insert(node.getIndexKey(), node);
		for(IndexWriter<String, GraphNode> writer : writers) {
			writer.write(this);
		}
	}

	@Override
	public void registerReader(IndexReader<String, GraphNode> reader) {
		readers.add(reader);
	}

	@Override
	public void unregisterReader(IndexReader<String, GraphNode> reader) {
		readers.remove(reader);
	}

	@Override
	public void registerWriter(IndexWriter<String, GraphNode> writer) {
		writers.add(writer);
	}

	@Override
	public void unregisterWriter(IndexWriter<String, GraphNode> writer) {
		writers.remove(writer);
	}

	@Override
	public String getFileName() {
		return null;
	}
	
}
