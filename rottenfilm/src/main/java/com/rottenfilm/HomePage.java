package com.rottenfilm;

import java.util.Map;

public class HomePage extends Pages {
	public HomePage() {
		super();
        pageOptions = Map.of("thuis", new String[]{"sluit", "catalog", "help"});
	}

	@Override
	protected void displayPage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'displayPage'");
	}
}
