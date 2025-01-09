package com.rottenfilm;

public class CommandProcessor {
    private Navigation navbar;
	private final HomePage homePage = new HomePage();
	private final HelpPage helpPage = new HelpPage();
	private final CatalogPage catalogPage = new CatalogPage();

	public CommandProcessor(Navigation navbar) {
        this.navbar = navbar;
	}

	public void processOption(Character action, String option) {
		if (action == '!') {
			switch (option.toLowerCase()) {
				case "s":
				case "sluit":
				case "sluiten":
				case "exit":
				case "x":
					handleExit();
					break;
				case "t":
				case "terug":
					handleBack();
					break;
				case "test":
					System.out.println("This is a succesfull test.");
					break;
				default:
					System.err.println("Onherkende optie: " + option);
					break;
			}
		} else if (action == '?') {
			switch (option.toLowerCase()) {
				case "f":
				case "film":
				case "c":
				case "catalog":
				case "catalogus":
				case "filmcatalogus":
					clearTerminal();
					navbar.stepUpCurrentPath("catalogus");
					catalogPage.displayFilmCatalog();
					break;
				case "h":
				case "hulp":
				case "help":
					clearTerminal();
					navbar.stepUpCurrentPath("help");
					helpPage.displayHelp();
					break;
				case "test":
					System.out.println("This is a succesfull test.");
					break;
				default:
					System.err.println("Onherkende optie: " + option);
					break;
			}
		} else {
			System.err.println("Onherkende action: " + action);
		}
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
		if (navbar.getCurrentPathString() != "/thuis") {
			navbar.stepBackCurrentPath();
		}
	}

	public CatalogPage getCatalogPage() {
		return catalogPage;
	}

	public HelpPage getHelpPage() {
		return helpPage;
	}

	public HomePage getHomePage() {
		return homePage;
	}
}
