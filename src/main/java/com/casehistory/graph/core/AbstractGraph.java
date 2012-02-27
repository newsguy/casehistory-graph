package com.casehistory.graph.core;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Abhinav Tripathi
 */
public abstract class AbstractGraph implements Serializable {

	private static final long serialVersionUID = -2529271384525223550L;

	protected final Set<GraphNode> roots = new HashSet<GraphNode>();
	protected final List<GraphNode> nodes = new ArrayList<GraphNode>();
	protected final Representation representation;

	public AbstractGraph() {
		this.representation = new AdjacencyMatrix();
	}

	public AbstractGraph(Representation representation) {
		this.representation = representation;
	}

	/**
	 * The data structure used for the representation of the graph, e.g.
	 * adjacency matrix, adjacency lists
	 * 
	 * @author Abhinav Tripathi
	 */
	public static interface Representation {

		public void add(GraphNode node, Collection<GraphNode> neighbours);
		
	}

	public Set<GraphNode> getRoots() {
		return Collections.unmodifiableSet(roots);
	}

	public List<GraphNode> getNodes() {
		return Collections.unmodifiableList(nodes);
	}

	public abstract void addNode(GraphNode node);
	
	public abstract void addNode(String[] queryTerms) throws NoSuchAlgorithmException;

	class AdjacencyMatrix implements Serializable, Representation {

		private static final long serialVersionUID = 5100496977059320517L;

		// TODO: use bit vectors instead of ints to make this memory-efficient
		private final int DEFAULT_INITIAL_SIZE = 256;
		public int[][] matrix = new int[DEFAULT_INITIAL_SIZE][DEFAULT_INITIAL_SIZE];

		private int initialsize;
		private ResizePolicy policy;

		public final double DEFAULT_THRESHOLD = 0.8d;
		private double threshold;
		
		private int currentpos;

		public AdjacencyMatrix() {
			initialsize = DEFAULT_INITIAL_SIZE;
			threshold = DEFAULT_THRESHOLD;
			policy = new DoubleUp();
			currentpos = 0;
		}

		public AdjacencyMatrix(int initialsize) {
			this.initialsize = initialsize;
			matrix = new int[initialsize][initialsize];
			this.threshold = DEFAULT_THRESHOLD;
			this.policy = new DoubleUp();
			currentpos = 0;
		}

		void resize() {
			matrix = policy.resize(matrix);
			System.gc();
		}

		void setResizePolicy(ResizePolicy policy) {
			this.policy = policy;
		}

		void setThreshold(double threshold) {
			this.threshold = threshold;
		}

		@Override
		public void add(GraphNode node, Collection<GraphNode> neighbours) {
			currentpos++;
			for(GraphNode neighbour : neighbours) {
				matrix[currentpos][nodes.indexOf(neighbour)-1] = 1;
			}
		}

	}

}
