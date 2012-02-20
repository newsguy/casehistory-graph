package com.casehistory.graph.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the <a href="http://en.wikipedia.org/wiki/Trie">trie</a>
 * key structure.
 * 
 * @author Abhinav Tripathi
 * @param <T>
 */
public abstract class Trie<T, U> {

	/**
	 * The root here is a special {@link TrieNode} in that it does not hold any
	 * key or data itself.
	 * 
	 * <p>
	 * The specific implementation of this fact "does not hold any key or data"
	 * is left to the subclasses (e.g. for strings this would be a blank string)
	 */
	protected TrieNode root;

	protected Trie() {
		constructRoot();
		if (root.parent != null) {
			throw new IllegalStateException("Root node cannot have a parent!");
		}
		init();
	}

	protected abstract void constructRoot();

	protected abstract void init();

	/**
	 * Insert a new node, if necessary, into this trie which holds the given
	 * key.
	 * 
	 * @param key
	 */
	public void insert(T key, U data) {
		TrieNode parentNode = search(key, root).get(1);
		TrieNode node = new TrieNode(key, data, parentNode);
		parentNode.children.add(node);
	}

	/**
	 * Searches for the node in the trie, which contains the key asked for.
	 * 
	 * @param key
	 * @param currentNode
	 * @return list of two {@link TrieNode}s, first is the node containing the
	 *         key, second is the parent of this node
	 */
	protected List<TrieNode> search(T key, TrieNode currentNode) {
		List<TrieNode> nodes = new ArrayList<TrieNode>();
		boolean digDeeper = false;
		boolean found = false;
		for (TrieNode child : currentNode.children) {
			if (child.key.equals(key)) {
				found = true;
				nodes.add(child);
				nodes.add(currentNode);
				break;
			}
			if (isPartOf(child.key, key)) {
				digDeeper = true;
				nodes.add(1, child); // add only parent node as we haven't found
										// the node with the key yet
				break;
			}
		}
		if (!found) {
			if (digDeeper) {
				search(key, nodes.get(1));
			}
		}

		return nodes;
	}

	/**
	 * Searches for the node in the trie, which contains the key asked for.
	 * 
	 * @param key
	 * @return the node containing the key
	 */
	public TrieNode search(T key) {
		return search(key, root).get(0);
	}

	/**
	 * Answers whether key1 is contained within key2.
	 * 
	 * @param key1
	 * @param key2
	 * @return true/false
	 */
	protected abstract boolean isPartOf(T key1, T key2);

	/**
	 * A node in the trie key structure.
	 * 
	 * @author Abhinav Tripathi
	 * @param <T>
	 */
	class TrieNode {
		private T key;
		private U data;
		private TrieNode parent;
		private Collection<TrieNode> children;

		public TrieNode(T key, U data, TrieNode parent) {
			this.key = key;
			this.data = data;
			this.parent = parent;
		}

		public T getKey() {
			return key;
		}

		public U getData() {
			return data;
		}

		public TrieNode getParent() {
			return parent;
		}

		public Collection<TrieNode> getChildren() {
			return Collections.unmodifiableCollection(children);
		}

	}

}