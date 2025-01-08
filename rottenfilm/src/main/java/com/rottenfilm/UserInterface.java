package com.rottenfilm;

import java.util.Scanner;

public class UserInterface {
	private MovieCatalog movieCatalog;
    private Scanner scanner;

    public UserInterface(MovieCatalog movieCatalog) {
        this.movieCatalog = movieCatalog;
        this.scanner = new Scanner(System.in);
    }

	// Main loop and UI logic here
    public void run() {
        System.out.println("Welkom tot Rotten Film, waar de vibes enkel zo rot zijn als jij.");

        try {
            while (true) {
                System.out.print("Wat wenst u te doen of waar wenst u naartoe te gaan?\n\n" +
                "[ Sluit, Filmcatalogus, hulp ]\n");
        
                String option = scanner.nextLine();
        
                switch (option.toLowerCase()) {
					case "s":
					case "sluit":
					case "x":
                        clearTerminal();
        
                        // Terminate JVM 
                        System.exit(0);
                        break;
                    case "f":
                    case "film":
                    case "catalogus":
                    case "filmcatalogus":
                        clearTerminal();

						// We take the current catalogue page number and multiply that by the amount of movies we want per page
						for (int i = 0; i < movieCatalog.getCurrentPage() * movieCatalog.getPerPageLimit(); i++) {
							if (i > movieCatalog.size()) break;
							Movie movie = movieCatalog.get(i);
							System.out.println("\n----------+++++++++========[]========+++++++++----------\n");
							System.out.println(String.format(
								"\t%s\n\t\t%s",
								movie.getTitle(), String.join(", ", movie.getDirectors())
							));
							System.out.println("\n----------+++++++++========[]========+++++++++----------\n\n");
						}

                        // ...
                        break;
                    case "h":
                    case "hulp":
                    case "help":
                        clearTerminal();
    
                        // ...
                        break;
                    default:
                        break;
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
}
