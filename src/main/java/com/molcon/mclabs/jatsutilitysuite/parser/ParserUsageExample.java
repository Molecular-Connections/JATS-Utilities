package com.molcon.mclabs.jatsutilitysuite.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

public class ParserUsageExample {
	public static void main(String[] args) {
		try {
			String filePath = "EMS85291.xml";
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);
			factory.setFeature("http://xml.org/sax/features/namespaces", false);
			factory.setFeature("http://xml.org/sax/features/validation", false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			
			InputStream inputStream= new FileInputStream(filePath);
	        Reader reader = new InputStreamReader(inputStream,"UTF-8");
	        InputSource is = new InputSource(reader);
	        is.setEncoding("UTF-8");
			
			JatsParsedObject obj = new JatsParsedObject(JatsParsedObject.PARSER.XML, 
					filePath, docBuilder.parse(is));
			
			System.out.println(obj.getFrontTags().getArticleTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
