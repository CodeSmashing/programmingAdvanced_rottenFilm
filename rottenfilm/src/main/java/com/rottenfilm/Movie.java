package com.rottenfilm;

public class Movie {
	private final String[] randomNames = {"Theodora Bell", "Emmett Colon", "Remy Madden", "Everest Reilly", "Tori Sellers", "Madden Sanders", "Everleigh Reyes", "Eli Anderson", "Ella Glover", "Mack Bryan", "Meredith Wallace", "Chase Barnes", "Liliana Romero", "Bryson Lucero", "Ila Preston", "Vincenzo Fleming", "Fatima Beck", "Eduardo Jordan", "Adalynn Thomas", "Logan Dyer", "Estrella Jenkins", "Declan Fuentes", "Madeleine Doyle", "Kashton Barr", "Noemi Page", "Pablo Pacheco", "Paris Clay", "Yosef Russell", "Raelynn Ellison", "Kye Greene", "Selena Wilkins", "Yusuf Atkinson", "Jazmin Navarro", "Reid Mathews", "Sloan Tucker", "Ivan Campos", "Sutton Alfaro", "Xzavier Hurst", "Adalee Baxter", "Tomas Hahn", "Fallon McGee", "Conner Christensen", "Carmen Tyler", "Emmitt Arellano", "Faye Branch", "Keenan Dillon", "Laurel Shaffer", "Dexter Barnes", "Liliana Lester", "Lee Nielsen", "Vienna Randolph"};
	private Integer show_id;
	private String type;
	private String title;
	private String[] directors;
	private String[] cast;
	private String[] country;
	private String date_added;
	private Integer release_year;
	private String rating;
	private Integer duration; // In minutes
	private String[] listed_in;
	private String description;

	// // If types are correct
	// public Movie(Integer show_id, String type, String title, String[] directors, String[] cast, String[] country, LocalDate date_added, Integer release_year, String rating, Integer duration, String[] listed_in, String description) {
	// 	this.show_id = show_id;
	// 	this.type = type;
	// 	this.title = title;
	// 	this.directors = directors;
	// 	this.cast = cast;
	// 	this.country = country;
	// 	this.date_added = date_added;
	// 	this.release_year = release_year;
	// 	this.rating = rating;
	// 	this.duration = duration;
	// 	this.listed_in = listed_in;
	// 	this.description = description;
	// }

	public Movie(String show_id, String type, String title, String directors, String cast, String country, String date_added, String release_year, String rating, String duration, String listed_in, String description) {
		// If we don't have any directors then we will pick one at random
		if (directors.equals("")) directors = randomNames[(int) Math.floor(Math.random() * randomNames.length)];

		this.show_id = Integer.parseInt(show_id.trim());
		this.type = type.trim();
		this.title = title.trim();
		this.directors = directors.trim().split(",");
		this.cast = cast.trim().split(",");
		this.country = country.trim().split(",");
		this.date_added = date_added;
		this.release_year = Integer.parseInt(release_year.trim());
		this.rating = rating.trim();
		this.duration = Integer.parseInt(duration.trim().split("\\s+")[0]);
		this.listed_in = listed_in.trim().split(",");
		this.description = description.trim();
	}

	public Integer getShow_id() {
		return show_id;
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

	public String getDate_added() {
		return date_added;
	}

	public Integer getRelease_year() {
		return release_year;
	}

	public String getRating() {
		return rating;
	}

	public Integer getDuration() {
		return duration;
	}

	public String[] getListed_in() {
		return listed_in;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return String.format(
			"%s{\n" +
			" show_id: %d,\n" +
			" type: '%s',\n" +
			" title: '%s',\n" +
			" directors: {'%s'},\n" +
			" cast: {'%s'},\n" +
			" country: '%s',\n" +
			" date_added: '%s',\n" +
			" release_year: '%s',\n" +
			" rating: '%s',\n" +
			" duration: '%d min',\n" +
			" listed_in: '%s',\n" +
			" description: '%s'\n" +
			'}',
			this.getClass().getSimpleName(), getShow_id(),
			getType(), getTitle(),
			String.join("', '", getDirectors()), String.join("', '", getCast()),
			String.join("', '", getCountry()), getDate_added(),
			getRelease_year(), getRating(),
			getDuration(), String.join("', '", getListed_in()),
			getDescription()
		);
	}
}
