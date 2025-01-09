package com.rottenfilm;

import java.util.Map;

public class HelpPage extends Pages {
	public HelpPage() {
		super();
		pageOptions = Map.of("help", new String[]{"sluit", "terug", "thuis", "catalogus"});
	}

	@Override
    public void displayPage() {
		System.out.println("""
		Via de terminal kan je ingeven wat je wil doen of waar je naartoe wil gaan.
		Aan het prille begin krijg je specifiek de kans om:
		 - het programma te sluiten;
		 - de filmcatalogus te bezoeken;
		 - deze pagina te bezoeken;

		Om naar een bepaalde pagina te gaan of om iets te doen gebruiken we de volgende syntax (case-insensitive):
		 ![actie]

		Enkele voorbeelden:
		 [Deze pagina bezoeken]
		 !help
		 
		 [Catalogus pagina bezoeken]
		 !catalogus

		 [Het programma sluiten]
		 !sluit
		""");
	}
}
