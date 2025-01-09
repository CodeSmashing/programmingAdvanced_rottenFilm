package com.rottenfilm;

import java.util.ArrayList;

public class Review {
	String authorName;
	String content;
	Double score;

	public Review(String authorName, String content, Double score) {
		this.authorName = authorName;
		this.content = content;
		this.score = score;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getContent() {
		return content;
	}

	public Double getScore() {
		return score;
	}

	public static Object join(String string, ArrayList<Review> reviews) {
		return join(string, reviews);
	}

	@Override
	public String toString() {
		return String.format(
			"%s{\n" +
			" author: '%s',\n" +
			" content: '%s',\n" +
			" score: %.2f\n" +
			'}',
			this.getClass().getSimpleName(), getAuthorName(),
			getContent(), getScore()
		);
	}
}
