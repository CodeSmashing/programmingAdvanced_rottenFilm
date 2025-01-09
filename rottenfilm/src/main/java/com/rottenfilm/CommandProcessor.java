package com.rottenfilm;

import java.util.Arrays;

public class CommandProcessor {
    private Navigation navbar;

	public CommandProcessor(Navigation navbar) {
        this.navbar = navbar;
	}

	public String processOption(String input) {
		Pages currentPage = navbar.getCurrentPage();

		// We check if the input is a command in the format ![action]
		if (input.matches("^![^\\s]+$")) {
			String option = input.substring(1).toLowerCase();

			// See if the inputted option matches any available of the current page
			if (!Arrays.asList(currentPage.getPageOptionsString().split(", ")).contains(option)) {
				System.err.println("Onherkende optie: " + option);
				return "default";
			}

			switch (option) {
				case "sluit":
					handleExit();
					break;
				case "terug":
					handleBack();
					break;
				case "catalog":
					clearTerminal();
					navbar.stepUpPath("catalog");
					navbar.getPages().get("catalog").displayPage();
					break;
				case "review":
					if (currentPage.getClass().getSimpleName() == "CatalogPage") break;
					return "review-movie";
				case "volgende":
					if (currentPage.getClass().getSimpleName() == "CatalogPage") break;
					return "next-catalog-page";
				case "help":
					clearTerminal();
					navbar.stepUpPath("help");
					navbar.getPages().get("help").displayPage();
					break;
				case "test":
					System.out.println("This is a succesfull test.");
					break;
				default:
					System.err.println("Onherkende optie: " + option);
					break;
			}
		} else if (currentPage.getClass().getSimpleName() == "CatalogPage") {
			input = input.substring(0).toUpperCase() + input.substring(1);

			if (input.matches("[^a-zA-Z]+")) {
				System.err.println("De input bevat ongeldige characters: " + input);
				return "default";
			} else {
				String[] tempReview = new String[3];
			}
		}
		return "default";
	}

    public void clearTerminal() {
        // https://www.javatpoint.com/how-to-clear-screen-in-java
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void handleExit() {
		clearTerminal();

		// Terminate JVM 
		System.exit(0);
	}

	private void handleBack() {
		clearTerminal();
		if (navbar.getPathString() != "/thuis") {
			navbar.stepBackPath();
		}
	}
}
