package com.rottenfilm;

import java.util.Map;

public abstract class Pages {
    protected Map<String, String[]> pageOptions;

    public Map<String, String[]> getPageOptions() {
        return pageOptions;
    }

	public String getPageOptionsString() {
        String[] options = pageOptions.values().iterator().next();
		return String.join(", ", options);
	}
}
