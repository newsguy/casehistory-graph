/**
 * 
 */
package com.casehistory.graph.core;

/**
 * @author Abhinav Tripathi
 */
public class QueryTrie extends Trie<String, GraphNode> {

	@Override
	protected void constructRoot() {
		root = new TrieNode("", null, null);
	}

	@Override
	protected void init() {

	}

	@Override
	protected boolean isPartOf(String term, String otherTerm) {
		return otherTerm.contains(term);
	}
	
}
