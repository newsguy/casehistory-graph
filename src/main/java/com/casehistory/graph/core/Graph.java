package com.casehistory.graph.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abhinav Tripathi
 */
public class Graph implements Serializable {

	private List<GraphNode> roots = new ArrayList<GraphNode>();

	private Map<GraphNode, List<Edge>> adjacencyMap = new HashMap<GraphNode, List<Edge>>();

}
