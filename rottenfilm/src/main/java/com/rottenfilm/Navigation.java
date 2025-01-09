package com.rottenfilm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Navigation {
	private ArrayList<String> currentPath = new ArrayList<>(Arrays.asList("", "thuis"));
	private Map<String, Pages> pages = new HashMap<>(Map.ofEntries(
		Map.entry("thuis", new HomePage()),
		Map.entry("help", new HelpPage()),
		Map.entry("catalog", new CatalogPage())
	));

	public Map<String, Pages> getPages() {
		return pages;
	}

	public String getPathString() {
		return String.join("/", currentPath);
	}

    public ArrayList<String> getPath() {
        return currentPath;
    }

    public Pages getCurrentPage() {
        return pages.get(currentPath.get(currentPath.size() - 1));
    }

	public void stepUpPath(String key) {
		currentPath.add(key);
	}

	public void stepBackPath() {
		currentPath.remove(currentPath.size() - 1);
	}
}
