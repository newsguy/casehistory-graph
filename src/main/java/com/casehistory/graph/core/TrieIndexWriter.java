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
	
	private String baseDirectory = "";
	
	private static Logger logger  = Logger.getLogger("VERBOSE");

	@Override
	public void write(Index<String, GraphNode> index) {
		String path = baseDirectory + "/" + Constants.INDEXES_DIR + "/" + Constants.TRIE_INDEX_DIR + "/" + index.getFileName();
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
			logger.error("Failed to write trie-index for the database! A restart of the application maybe necessary." + ex);
		}
	}

}
