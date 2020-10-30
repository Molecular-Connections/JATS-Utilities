package com.molcon.mclabs.jatsutilitysuite.validation;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.EntityResolver;

public class DTDValidation {
	
	private String dtdFile;  
	
	public DTDValidation(String dtd) {
		this.dtdFile = dtd;
	}
	
	public void validate(String xmlFile) throws ParserConfigurationException{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		domFactory.setNamespaceAware(true);
		domFactory.setFeature("http://xml.org/sax/features/namespaces", true);
		domFactory.setFeature("http://xml.org/sax/features/validation", true);
		domFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", true);
		domFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
	

		builder.setEntityResolver(new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) 
					throws SAXException, IOException {
				return new InputSource(new FileInputStream(dtdFile));
			}
		});

		ErrorHandler handler = new CustomErrorHandler();
		builder.setErrorHandler(handler);
		try {
			builder.parse(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
