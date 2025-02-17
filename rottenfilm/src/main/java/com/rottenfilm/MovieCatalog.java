package com.rottenfilm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class MovieCatalog implements Iterable<Movie> {
    private static final String DATA_PATH = "./rottenfilm/src/main/resources/netflix_titles.csv";
	private ArrayList<Movie> catalog = new ArrayList<Movie>();
	private Integer movieLimit = 20; // Limit the amount of movies for testing

    public MovieCatalog() {
        String line = "";
		Integer movieCount = 0;

		// Regex to split by comma, ignoring commas inside double double quotes
        String splitBy = ",(?=(?:[^\\\"]*\\\"\\\"[^\\\"]*\\\"\\\")*[^\\\"]*$)";

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH))) {
			// Get all the fields from the header
			ArrayList<String> headerFields = new ArrayList<>(Arrays.asList(br.readLine().replace(";", "").split(",")));

			if (!headerFields.contains("reviews")) {
				headerFields.add("reviews");
			}

            while ((line = br.readLine()) != null && movieCount < movieLimit) {
                // Remove the enclosing quotes and double semicolons and split the initial movie item into its constituent parts
                ArrayList<String> values = new ArrayList<>(Arrays.asList(line.substring(1, line.length() - 3).split(splitBy, -1))); // -1 to keep trailing empty values

                // To map keys to values
                HashMap<String, String> movieMap = new HashMap<>();

				// If a movie item doesn't have all the fields as defined in headerFields,
				// we add every missing field as "null" (Should only be temporary as we should check where to add these)
				for (int i = values.size(); i < headerFields.size(); i++) {
					values.add("");
				}

                for (int i = 0; i < values.size(); i++) {
                    // Replace double double quotes with square brackets in values that have them
                    if (values.get(i).startsWith("\"\"") && values.get(i).endsWith("\"\"")) {
                        values.set(i, "" + values.get(i).substring(2, values.get(i).length() - 2));

                        // We avoid doing anything with the description or the release date for now
						// But we do change the comma sepperation of everything else to not include a space
                        if (!(i == values.size() - 1 || values.get(i).matches("[a-zA-Z]*\\s\\d+,\\s\\d+"))) {
                            values.set(i, values.get(i).replace(", ", ","));
                        }
                    }

                    movieMap.put(headerFields.get(i), values.get(i));
                }

                catalog.add(new Movie(
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
					movieMap.get("description"),
					movieMap.get("reviews")
				));
                movieCount++;
            }
        } catch(IOException e) {
            System.err.println("Fout bij het lezen van het bestand.");
            e.printStackTrace();
        }
    }

	public Movie getMovie(Integer showId) {
		for (Movie movie : catalog) {
			if (movie.getShowId().equals(showId)) return movie;
		}
		return null; // Return null if no movie is found
	}

	public int size() {
		return catalog.size();
	}

	public Movie get(int i) {
		return catalog.get(i);
	}

    @Override
    public Iterator<Movie> iterator() {
        return catalog.iterator();
    }
}
