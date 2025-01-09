package com.rottenfilm;

import java.util.Map;

public class HomePage extends Pages {
	public HomePage() {
		super();
        pageOptions = Map.of("thuis", new String[]{"Sluit", "Catalogus", "Hulp"});
	}
}
