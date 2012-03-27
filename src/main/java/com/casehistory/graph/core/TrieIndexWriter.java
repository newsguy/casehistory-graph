/**
 * 
 */
package com.casehistory.graph.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.casehistory.graph.utils.DirectoryUtil;

/**
 * @author Abhinav Tripathi
 */
public class TrieIndexWriter implements IndexWriter<String, GraphNode> {

	private String baseDirectory;
	private final String indexDirectory;

	private static Logger logger = Logger.getLogger("VERBOSE");

	public TrieIndexWriter(String baseDirectory) {
		this.baseDirectory = baseDirectory;
		if (this.baseDirectory == null || this.baseDirectory.equals("")) {
			this.indexDirectory = Constants.INDEXES_DIR + "/" + Constants.TRIE_INDEX_DIR;
		} else {
			this.indexDirectory = baseDirectory + "/" + Constants.INDEXES_DIR + "/" + Constants.TRIE_INDEX_DIR;
		}
	}

	@Override
	public void write(Index<String, GraphNode> index) {
		String path = indexDirectory + "/" + index.getFileName();
		if (!DirectoryUtil.pathExists(path)) {
			DirectoryUtil.build(path, true);
		}
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(path);
			out = new ObjectOutputStream(fos);
			out.writeObject(index);
			out.close();
		} catch (IOException ex) {
			// TODO: would be nice to log this as a failed attempt and re-schedule it
			logger.error("Failed to write trie-index for the database! A restart of the application maybe necessary." + ex);
		}
	}

	@Override
	public void run() {
		
	}

}
