/**
 * 
 */
package com.casehistory.graph.server;

import org.apache.log4j.Logger;

import com.casehistory.graph.core.GraphNode;
import com.casehistory.graph.core.Index;
import com.casehistory.graph.core.IndexReader;
import com.casehistory.graph.core.IndexWriter;
import com.casehistory.graph.core.TrieIndexReader;
import com.casehistory.graph.core.TrieIndexWriter;

/**
 * @author Abhinav Tripathi
 */
public class Server {

	private static final Logger logger = Logger.getLogger("VERBOSE");

	public static void main(String[] args) {
		logger.info("Starting graph database server ...");
		logger.info("Loading the indexes ...");
		loadIndexes();
		logger.info("Loading the graph ...");
		loadGraph();
	}

	private static void loadGraph() {

	}

	private static void loadIndexes() {
		IndexReader<String, GraphNode> indexReader = new TrieIndexReader(null); // TODO: make this configurable
		IndexWriter<String, GraphNode> indexWriter = new TrieIndexWriter(null); // TODO: make this configurable
		Index<String, GraphNode> index = indexReader.read();
		index.registerReader(indexReader);
		index.registerWriter(indexWriter);
	}

}
