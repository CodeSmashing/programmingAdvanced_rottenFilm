package com.rottenfilm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MovieCatalog {
    private ArrayList<Movie> catalog;
	private String dataPath;
	private Integer currentPage = 1;
	private Integer perPageLimit = 5;
	private Integer movieLimit = 20; // Limit the amount of movies for testing

	public MovieCatalog(String dataPath) {
		this.dataPath = dataPath;
		this.catalog = parseMovies();
	}

    public ArrayList<Movie> parseMovies() {
        ArrayList<Movie> parsedMovies = new ArrayList<>(movieLimit);
        String line = "";
		Integer movieCount = 0;

		// Regex to split by comma, ignoring commas inside double double quotes
        String splitBy = ",(?=(?:[^\\\"]*\\\"\\\"[^\\\"]*\\\"\\\")*[^\\\"]*$)";

        try (BufferedReader br = new BufferedReader(new FileReader(dataPath))) {
			// Get all the fields from the header
			String[] headerFields = br.readLine().replace(";", "").split(",");

            while ((line = br.readLine()) != null && movieCount < movieLimit) {
                // Remove the enclosing quotes and double semicolons and split the initial movie item into its constituent parts
                String[] values = line.substring(1, line.length() - 3).split(splitBy, -1); // -1 to keep trailing empty values
                // To map keys to values
                HashMap<String, String> movieMap = new HashMap<>();

                for (int i = 0; i < values.length; i++) {
                    // Replace double double quotes with square brackets in values that have them
                    if (values[i].startsWith("\"\"") && values[i].endsWith("\"\"")) {
                        values[i] = "" + values[i].substring(2, values[i].length() - 2);
                        // We avoid doing anything with the description or the release date for now
                        if (!(i == values.length - 1 || values[i].matches("[a-zA-Z]*\\s\\d+,\\s\\d+"))) {
                            // We add square brackets for other collections
                            // values[i] = "[" + values[i] + "]";
                            values[i] = values[i].replace(", ", ",");
                        }
                    }

                    movieMap.put(headerFields[i], values[i]);
                }
                parsedMovies.add(new Movie(
					movieMap.get("show_id"),
					movieMap.get("type"),
					movieMap.get("title"),
					movieMap.get("director"),
					movieMap.get("cast"),
					movieMap.get("country"),
					movieMap.get("date_added"),
					movieMap.get("release_year"),
					movieMap.get("rating"),
					movieMap.get("duration"),
					movieMap.get("listed_in"),
					movieMap.get("description")
				));
                movieCount++;
            }
        } catch(IOException e) {
            System.err.println("Fout bij het lezen van het bestand.");
            e.printStackTrace();
        }

        return parsedMovies;
    }

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getPerPageLimit() {
		return perPageLimit;
	}

	public int size() {
		return catalog.size();
	}

	public Movie get(int i) {
		return catalog.get(i);
	}
}
