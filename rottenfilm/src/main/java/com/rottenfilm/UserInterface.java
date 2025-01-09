package com.rottenfilm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
	private Scanner scanner = new Scanner(System.in);
	private Navigation navbar = new Navigation();
	private CommandProcessor processor = new CommandProcessor(navbar);
	private Map<String, Pages> pages = new HashMap<>(Map.ofEntries(
		Map.entry("thuis", processor.getHomePage()),
		Map.entry("help", processor.getHelpPage()),
		Map.entry("catalog", processor.getCatalogPage())
	));

	// Main loop and UI logic here
    public void run() {
		processor.clearTerminal();
        System.out.println("Welkom tot Rotten Film, waar de vibes enkel zo rot zijn als jij.\n");

        try {
            while (true) {
                System.out.print("Huidige pad: " + navbar.getCurrentPathString() +
				"\nWat wenst u te doen of waar wenst u naartoe te gaan?\n\n" +
				"[ " + pages.get(navbar.getCurrentPage()).getPageOptionsString() + " ]\n");

                String option = scanner.nextLine();
				if (option.length() > 0 && (option.charAt(0) == '?' || option.charAt(0) == '!')) {
					processor.processOption(option.charAt(0), option.substring(1));
				} else {
					System.err.println("Onherkende command: " + option.charAt(0));
                }
            }
        } catch (Exception e) {
			System.out.println("An error occured: " + e);
            // TODO: handle exception
        }
    }
}
