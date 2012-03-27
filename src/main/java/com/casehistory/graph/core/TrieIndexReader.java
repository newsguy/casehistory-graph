/**
 * 
 */
package com.casehistory.graph.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;

import com.casehistory.graph.utils.DirectoryUtil;

/**
 * @author Abhinav Tripathi
 */
public class TrieIndexReader implements IndexReader<String, GraphNode> {

	private String baseDirectory;
	private final String indexDirectory;

	private static Logger logger = Logger.getLogger("VERBOSE");

	public TrieIndexReader(String baseDirectory) {
		this.baseDirectory = baseDirectory;
		if (this.baseDirectory == null || this.baseDirectory.equals("")) {
			this.indexDirectory = Constants.INDEXES_DIR + "/" + Constants.TRIE_INDEX_DIR;
		} else {
			this.indexDirectory = baseDirectory + "/" + Constants.INDEXES_DIR + "/" + Constants.TRIE_INDEX_DIR;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Index<String, GraphNode> read() {
		// TODO: use None instead of null
		Index<String, GraphNode> index = null;
		String path = indexDirectory;
		if (!DirectoryUtil.pathExists(path)) {
			logger.error("Indexes directory not found! Restart the application OR issue an index write command.");
		}
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(path);
			in = new ObjectInputStream(fis);
			index = (Index<String, GraphNode>) in.readObject();
			in.close();
		} catch (IOException ex) {
			logger.error("Failed to read trie-index for the database! Restart the application OR issue an index write command." + ex);
		} catch (ClassNotFoundException ex) {
			logger.error("Failed to read trie-index for the database! Restart the application OR issue an index write command." + ex);
		}

		return index;
	}

	@Override
	public void run() {
		while(true) {
			
		}
	}

}
