package com.rottenfilm;

import java.util.Scanner;

public class UserInterface {
	private MovieCatalog catalog;
    private Scanner scanner;

    public UserInterface(MovieCatalog catalog) {
        this.catalog = catalog;
        this.scanner = new Scanner(System.in);
    }

	// Main loop and UI logic here
    public void run() {
        System.out.println("Welkom tot Rotten Film, waar de vibes enkel zo rot zijn als jij.");

        try {
            while (true) {
                System.out.print("Wat wenst u te doen of waar wenst u naartoe te gaan?\n\n" +
                "[ Sluiten, Filmcatalogus, Hulp ]\n");
        
                String option = scanner.nextLine();
				if (option.length() > 0 && (option.charAt(0) == '?' || option.charAt(0) == '!')) {
					processOption(option);
				} else {
					System.err.println("Onherkende command: " + option.charAt(0));
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void clearTerminal() {
        // https://www.javatpoint.com/how-to-clear-screen-in-java
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

	private void processOption(String option) {
		switch (option) {
			case "!s":
			case "!sluit":
			case "!sluiten":
			case "!exit":
			case "!x":
				handleExit();
				break;
			case "?f":
			case "?film":
			case "?catalogus":
			case "?filmcatalogus":
				displayFilmCatalog();
				break;
			case "?h":
			case "?hulp":
			case "?help":
				displayHelp();
				break;
			default:
				System.err.println("Onherkende command: " + option);
				break;
		}
	}

    private void handleExit() {
		clearTerminal();

		// Terminate JVM 
		System.exit(0);
	}

    private void displayFilmCatalog() {
		clearTerminal();

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

    private void displayHelp() {
		clearTerminal();

		System.out.println("""
		Via de terminal kan je ingeven wat je wil doen of waar je naartoe wil gaan.
		In de start menu krijg je specifiek de kans om:
		 - het programma te sluiten;
		 - de filmcatalogus te bezoeken;
		 - deze pagina te bezoeken;

		Om naar een bepaalde pagina te gaan gebruiken we de volgende syntax (case-insensitive):
		 ?[paginanaam]
		 ![actie]

		Enkele voorbeelden:
		 [Deze pagina bezoeken]
		 ?hulp
		 ?h

		 [Het programma sluiten]
		 !sluit
		 !exit
		 !x
		""");
	}
}
