package com.molcon.mclabs.jatsutilitysuite.transformation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class TransformationUsageExample {

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = TransformationUsageExample.class.getClassLoader();
		File xsltfile = new File(classLoader.getResource("xslt/jats.xslt").getFile());
		SaxonProcessor proc = new SaxonProcessor(xsltfile.getAbsolutePath());
		String xmlFile = "EMS85291.xml";
		String html = proc.transform(new File(xmlFile));
		FileUtils.write(new File("textfile.html"), html, "UTF-8");

	}

}
