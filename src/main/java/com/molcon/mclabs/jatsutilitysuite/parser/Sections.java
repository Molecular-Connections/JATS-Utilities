package com.molcon.mclabs.jatsutilitysuite.parser;

import java.util.ArrayList;
import java.util.List;

public class Sections extends ArrayList<Section> {
	private static final long serialVersionUID = 1L;
	
	public String getContent(String title) {
		for (Section sec : this) {
			if(title.equalsIgnoreCase(sec.getHeading())) return sec.getContent(); 
		}
		return null;
	}

	public Section getSection(String title) {
		for (Section sec : this) {
			if(title.equalsIgnoreCase(sec.getHeading())) return sec;
		}
		return null;
	}
	
	public List<String> getAllSectionNames() {
		List<String> temp = new ArrayList<String>();
		for (Section sec : this) {
			temp.add(sec.getHeading());
		}
		return temp;
	}

	
	public void addSection(String heading, String content) {
		boolean flag = false;
		for (Section sec : this) {
			if(heading.equalsIgnoreCase(sec.getHeading())) {
				sec.setContent(content);
				flag = true;
			}
		}
		if(!flag) {
			Section temp = new Section();
			temp.setHeading(heading);
			temp.setContent(content);
			add(temp);
		} else {
			getSection(heading).setContent(getContent(heading) + " " + content);
		}
	}
}
