package com.rottenfilm;

import java.util.ArrayList;
import java.util.Arrays;

public class Navigation {
	private ArrayList<String> currentPath = new ArrayList<>(Arrays.asList("", "thuis"));

	public String getCurrentPathString() {
		return String.join("/", currentPath);
	}

    public ArrayList<String> getCurrentPath() {
        return currentPath;
    }

    public String getCurrentPage() {
        return currentPath.get(currentPath.size() - 1);
    }

	public void stepUpCurrentPath(String key) {
		currentPath.add(key);
	}

	public void stepBackCurrentPath() {
		currentPath.remove(currentPath.size() - 1);
	}
}
