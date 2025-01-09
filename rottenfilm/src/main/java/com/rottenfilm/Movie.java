package com.rottenfilm;

import java.util.ArrayList;

public class Movie {
	private final String[] randomNames = {"Theodora Bell", "Emmett Colon", "Remy Madden", "Everest Reilly", "Tori Sellers", "Madden Sanders", "Everleigh Reyes", "Eli Anderson", "Ella Glover", "Mack Bryan", "Meredith Wallace", "Chase Barnes", "Liliana Romero", "Bryson Lucero", "Ila Preston", "Vincenzo Fleming", "Fatima Beck", "Eduardo Jordan", "Adalynn Thomas", "Logan Dyer", "Estrella Jenkins", "Declan Fuentes", "Madeleine Doyle", "Kashton Barr", "Noemi Page", "Pablo Pacheco", "Paris Clay", "Yosef Russell", "Raelynn Ellison", "Kye Greene", "Selena Wilkins", "Yusuf Atkinson", "Jazmin Navarro", "Reid Mathews", "Sloan Tucker", "Ivan Campos", "Sutton Alfaro", "Xzavier Hurst", "Adalee Baxter", "Tomas Hahn", "Fallon McGee", "Conner Christensen", "Carmen Tyler", "Emmitt Arellano", "Faye Branch", "Keenan Dillon", "Laurel Shaffer", "Dexter Barnes", "Liliana Lester", "Lee Nielsen", "Vienna Randolph"};
	private Integer showId;
	private String type;
	private String title;
	private String[] directors;
	private String[] cast;
	private String[] country;
	private String dateAdded;
	private Integer releaseYear;
	private String rating;
	private Integer duration; // In minutes
	private String[] listedIn;
	private String description;
	private ArrayList<Review> reviews;

	// // If types are correct
	// public Movie(Integer showId, String type, String title, String[] directors, String[] cast, String[] country, LocalDate dateAdded, Integer releaseYear, String rating, Integer duration, String[] listedIn, String description) {
	// 	this.showId = showId;
	// 	this.type = type;
	// 	this.title = title;
	// 	this.directors = directors;
	// 	this.cast = cast;
	// 	this.country = country;
	// 	this.dateAdded = dateAdded;
	// 	this.releaseYear = releaseYear;
	// 	this.rating = rating;
	// 	this.duration = duration;
	// 	this.listedIn = listedIn;
	// 	this.description = description;
	// }

	public Movie(String showId, String type, String title, String directors, String cast, String country, String dateAdded, String releaseYear, String rating, String duration, String listedIn, String description, String reviews) {
		// If we don't have any directors then we will pick one at random
		if (directors.equals("")) directors = randomNames[(int) Math.floor(Math.random() * randomNames.length)];

		this.showId = Integer.parseInt(showId);
		this.type = type;
		this.title = title;
		this.directors = directors.split(",");
		this.cast = cast.split(",");
		this.country = country.split(",");
		this.dateAdded = dateAdded;
		this.releaseYear = Integer.parseInt(releaseYear);
		this.rating = rating;
		this.duration = Integer.parseInt(duration.split("\\s+")[0]);
		this.listedIn = listedIn.split(",");
		this.description = description;

		// Reviews string format: [author="?",content="?",score=?],[author="?",content="?",score=?],[author="?",content="?",score=?]
		if (!reviews.equals("")) {
			for (String reviewString : reviews.split(",")) {
				reviewString.replaceAll("[\\[\\]]", "");
				String[] reviewParts = reviewString.split(",");
				this.reviews.add(new Review(reviewParts[0], reviewParts[1], Double.parseDouble(reviewParts[2])));
			}
		}
	}

	public Integer getShowId() {
		return showId;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String[] getDirectors() {
		return directors;
	}

	public String[] getCast() {
		return cast;
	}

	public String[] getCountry() {
		return country;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public String getRating() {
		return rating;
	}

	public Integer getDuration() {
		return duration;
	}

	public String[] getListedIn() {
		return listedIn;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	@Override
	public String toString() {
		return String.format(
			"%s{\n" +
			" showId: %d,\n" +
			" type: '%s',\n" +
			" title: '%s',\n" +
			" directors: {'%s'},\n" +
			" cast: {'%s'},\n" +
			" country: '%s',\n" +
			" dateAdded: '%s',\n" +
			" releaseYear: '%s',\n" +
			" rating: '%s',\n" +
			" duration: '%d min',\n" +
			" listedIn: '%s',\n" +
			" description: '%s',\n" +
			" reviews: {'%s'}\n" +
			'}',
			this.getClass().getSimpleName(), getShowId(),
			getType(), getTitle(),
			String.join("', '", getDirectors()), String.join("', '", getCast()),
			String.join("', '", getCountry()), getDateAdded(),
			getReleaseYear(), getRating(),
			getDuration(), String.join("', '", getListedIn()),
			getDescription(), Review.join("', '", getReviews())
		);
	}
}
