package com.rottenfilm;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {
	private Scanner scanner = new Scanner(System.in);
	private Navigation navbar = new Navigation();
	private CommandProcessor processor = new CommandProcessor(navbar);
	private MovieCatalog catalog;

	// Main loop and UI logic here
    public void run() {
		// Retrieve the CatalogPage from navbar
		Pages pages = navbar.getPages().get("catalog");

		// Check if the retrieved page is an instance of CatalogPage
		if (pages instanceof CatalogPage) {
			CatalogPage catalogPage = (CatalogPage) pages;
			catalog = catalogPage.getCatalog();
		} else {
			System.err.println("The page with key 'catalog' is not of type CatalogPage.");
		}

		processor.clearTerminal();
        System.out.println("Welkom bij Rotten Film, waar de vibes enkel zo rot zijn als jij.\n");
		String prompt = ""; // At the start we want the default prompt
		String[] tempReview = new String[4];

        try {
            while (true) {
				System.out.println(InputPrompt(prompt));
                String input = scanner.nextLine();

				// We check if the input is a command in the format ![action]
				if (input.matches("^![^\\s]+$")) {
					prompt = processor.processOption(input);
				} else if (Arrays.asList(new String[]{"review-movie", "review-name", "review-content", "review-score"}).contains(prompt)) {
					if (prompt == "review-movie") {
						tempReview = new String[4];
						for (int i = 0; i < catalog.size(); i++) {
							if (catalog.get(i).getTitle().equalsIgnoreCase(input)) {
								tempReview[0] = Integer.toString(catalog.get(i).getShowId());
								break;
							}
						}

						if (tempReview[0] == null) {
							System.out.println("Geen film was gevonden met die naam, probeer opnieuw.");
						} else {
							prompt = "review-name";
						}
					} else if (prompt == "review-name") {
						if (!input.matches("^[a-zA-Z]+$") && !input.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
							System.out.println("Die invoer is niet herkend als een geldige naam, probeer opnieuw.");
						} else {
							tempReview[1] = input;
							prompt = "review-content";
						}
					} else if (prompt == "review-content") {
						if (!input.matches("^.{3,1000}$")) {
							System.out.println("Die invoer is niet herkend als een geldige text, probeer opnieuw.");
						} else {
							tempReview[2] = input;
							prompt = "review-score";
						}
					} else if (prompt == "review-score") {
						if (!input.matches("^(\\d+)([,.]\\d+)?$")) {
							System.out.println("Die invoer is niet herkend als een geldige getal, probeer opnieuw.");
						} else {
							try {
								tempReview[3] = Double.toString(Double.parseDouble(input));
							} catch (NullPointerException e) {
								System.err.println("De invoer was leeg: " + e);
							} catch (NumberFormatException e) {
								System.err.println("De invoer was had niet de juiste formaat: " + e);
							}
							prompt = "review-confirm";
						}
					} else if (prompt == "review-confirm") {
						if (Arrays.asList(new String[]{"ja", "yes", "y", "j"}).contains(input)) {
							catalog.getMovie(Integer.parseInt(tempReview[0])).addReview(tempReview);
						} else {
							tempReview = new String[4];
						}
					}
				} else {
					prompt = "";
				}
            }
        } catch (NoSuchElementException | IllegalStateException e) {
			System.err.println("Ongeldige invoer gegeven: " + e);
        } catch (Exception e) {
			System.err.println("Iets ongekends is gebeurd: " + e);
		}
    }

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

	public String InputPrompt(String type) {
		String output = "Huidige pad: " + navbar.getPathString();
		if (type == "review-movie") {
			output = "\nWat is de naam van de film waarvoor u wenst een review achter te laten? (Exact zoals in de catalogus, uitgezonderd hoofdletters)\n";
		} else if (type == "review-name") {
			output = "\nWat is uw auteursnaam? (Voornaam en of achternaam mag)\n";
		} else if (type == "review-content") {
			output = "\nWat is de review? (Minimum 3 characters, maximum 1000)\n";
		} else if (type == "review-score") {
			output = "\nWat is uw score van 0.0 - 10.0 voor de film?\n";
		} else if (type == "review-confirm") {
			output = "\nBent u zeker dat u de review wil plaatsen?\n";
		} else if (type == "next") {

		} else {
			// The default prompt
			output = "\nWat wenst u te doen of waar wenst u naartoe te gaan?\n\n";
		}
		output += "[ " + navbar.getCurrentPage().getPageOptionsString() + " ]\n";
		return output;
	}
}
