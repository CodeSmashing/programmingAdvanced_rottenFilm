package com.rottenfilm;

import java.util.Map;

public class HelpPage extends Pages {
	public HelpPage() {
		super();
		pageOptions = Map.of("help", new String[]{"Sluit", "Terug", "Thuis", "Catalogus"});
	}

    public void displayHelp() {
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
		 ?help
		 ?hulp
		 ?h

		 [Het programma sluiten]
		 !sluit
		 !exit
		 !x
		""");
	}
}
