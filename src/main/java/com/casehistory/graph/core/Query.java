/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.casehistory.graph.option.Option;
import com.casehistory.graph.utils.StringUtils;

/**
 * A query node, contains a query string and the best news article results for
 * this query.
 * 
 * @author Abhinav Tripathi
 */
public class Query implements GraphNode {

	private static final long serialVersionUID = -4176121051213850392L;

	public final Date createdAt;
	private final String[] queryTerms;
	/**
	 * Maps the best results available for this query to news sites.
	 */
	private Map<String, Set<NewsArticle>> bestResults;

	private String hashFileName;

	public Query(Option<String[]> queryTerms, Option<Map<String, Set<NewsArticle>>> bestResults)
			throws NoSuchAlgorithmException {
		this.createdAt = new Date();
		if (queryTerms.hasValue())
			this.queryTerms = queryTerms.get();
		else
			throw new IllegalArgumentException("Invalid arguments given for creating a query node!");
		if (bestResults.hasValue())
			this.bestResults = bestResults.get();
		else
			throw new IllegalArgumentException("Invalid arguments given for creating a query node!");
		computeHashFileName();
	}

	@Override
	public String getName() {
		return new StringBuilder("Query [").append(getQuery()).append("]").toString();
	}

	@Override
	public String getIndexKey() {
		return getQuery();
	}

	@Override
	public Date getCreationTime() {
		return createdAt;
	}

	public String getQuery() {
		return StringUtils.join(queryTerms);
	}

	public Map<String, Set<NewsArticle>> getBestResults() {
		return Collections.unmodifiableMap(bestResults);
	}

	public void addResult(String newspaper, NewsArticle article) {
		if (bestResults.containsKey(newspaper)) {
			bestResults.get(newspaper).add(article);
		} else {
			Set<NewsArticle> articles = new HashSet<NewsArticle>();
			articles.add(article);
			bestResults.put(newspaper, articles);
		}
	}

	public void removeResult(String newspaper, NewsArticle article) {
		if (bestResults.containsKey(newspaper)) {
			bestResults.get(newspaper).remove(article);
		}
	}

	public void resetResults(String newspaper, Set<NewsArticle> articles) {
		bestResults.put(newspaper, articles);
	}

	@Override
	public String getFileName() {
		return hashFileName;
	}

	private void computeHashFileName() throws NoSuchAlgorithmException {
		String content = getQuery() + createdAt;

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(content.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		this.hashFileName = sb.toString();
	}

	public class NewsArticle implements Serializable {

		private static final long serialVersionUID = -3490211901946081577L;

		public final String url;
		public final String[] words;
		public final double[] vector;

		public NewsArticle(Option<String> url, Option<String[]> words, Option<double[]> vector) {
			if (url.hasValue())
				this.url = url.get();
			else
				throw new IllegalArgumentException("Invalid arguments given for news article!");
			if (words.hasValue())
				this.words = words.get();
			else
				throw new IllegalArgumentException("Invalid arguments given for news article!");
			if (vector.hasValue())
				this.vector = vector.get();
			else
				throw new IllegalArgumentException("Invalid arguments given for news article!");
		}

		@Override
		public String toString() {
			return "NewsArticle [url=" + url + "]";
		}

	}

}
