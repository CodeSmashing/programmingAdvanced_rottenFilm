package com.rottenfilm;

import java.util.Map;

public class CatalogPage extends Pages {
	private Integer currentPage = 0;
	private Integer perPageLimit = 5;
	private MovieCatalog catalog = new MovieCatalog();

	public CatalogPage() {
		super();
        pageOptions = Map.of("catalog", new String[]{"sluit", "terug", "thuis", "volgende", "review", "help"});
	}

	public MovieCatalog getCatalog() {
		return catalog;
	}

	public Integer getCurrentCatalogPage() {
		return currentPage;
	}

	public Integer getPerPageLimit() {
		return perPageLimit;
	}

	@Override
    public void displayPage() {
		for (int i = 0; i < getPerPageLimit(); i++) {
			if (i > catalog.size()) break;

			// We take the catalog at index i + (the page we're on * whatever the limit per page is)
			// Because we want every page to contain for example 5 movies and no duplicates
			Movie movie = catalog.get(i + (getCurrentCatalogPage() * getPerPageLimit()));
			System.out.println("----------+++++++++========[]========+++++++++----------");
			System.out.println(String.format(
				"\t%s\n\t\t%s",
				movie.getTitle(), String.join(", ", movie.getDirectors())
			));
			System.out.println("----------+++++++++========[]========+++++++++----------\n\n");
		}
	}
}
