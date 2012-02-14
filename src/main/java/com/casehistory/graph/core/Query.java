/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;

import com.casehistory.graph.strings.StringUtils;

/**
 * A query node, contains a query string and the best news article results for
 * this query.
 * 
 * @author Abhinav Tripathi
 */
@SuppressWarnings("serial")
public class Query implements GraphNode {

	private String[] queryTerms;
	private NewsArticle[] bestResults;

	public Query(String[] queryTerms, NewsArticle[] bestResults) {
		this.queryTerms = queryTerms;
		this.bestResults = bestResults;
	}

	@Override
	public String getName() {
		StringBuilder builder = new StringBuilder("Query [");
		for (String term : queryTerms)
			builder.append(term).append(" ");
		builder.append("]");

		return builder.toString();
	}

	@Override
	public String getQuery() {
		return StringUtils.join(queryTerms);
	}

	@Override
	public NewsArticle[] getBestResults() {
		return bestResults;
	}

	public class NewsArticle implements Serializable {

		public final String url;
		public final String[] words;
		public final double[] vector;

		public NewsArticle(String url, String[] words, double[] vector) {
			this.url = url;
			this.words = words;
			this.vector = vector;
		}

	}

}
