package com.molcon.mclabs.jatsutilitysuite.parser;

public class Section {
	private String heading;
	private String content;

	public String getHeading() {
		return heading;
	}

	public String getContent() {
		return content;
	}

	public void setHeading(String heading) {
		this.heading = heading.replaceAll("\n", " ").trim();
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
