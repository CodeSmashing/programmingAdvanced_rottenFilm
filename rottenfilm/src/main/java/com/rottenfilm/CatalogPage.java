package com.rottenfilm;

import java.util.Map;

public class CatalogPage extends Pages {
    private static final String DATA_PATH = "./rottenfilm/src/main/resources/";
	private MovieCatalog catalog = new MovieCatalog(DATA_PATH + "netflix_titles.csv");

	public CatalogPage() {
		super();
        pageOptions = Map.of("catalog", new String[]{"Sluit", "Terug", "Thuis", "Hulp"});
	}

    public void displayFilmCatalog() {
		// We take the current catalogue page number and multiply that by the amount of movies we want per page
		for (int i = 0; i < catalog.getCurrentPage() * catalog.getPerPageLimit(); i++) {
			if (i > catalog.size()) break;
			Movie movie = catalog.get(i);
			System.out.println("----------+++++++++========[]========+++++++++----------");
			System.out.println(String.format(
				"\t%s\n\t\t%s",
				movie.getTitle(), String.join(", ", movie.getDirectors())
			));
			System.out.println("----------+++++++++========[]========+++++++++----------\n\n");
		}
	}
}
