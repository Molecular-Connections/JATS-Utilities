package com.molcon.mclabs.jatsutilitysuite.validation;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CustomErrorHandler implements ErrorHandler {
		public void warning(SAXParseException e) throws SAXException {
			System.out.println("Warning: "); 
			printInfo(e);
			e.printStackTrace();
		}
		public void error(SAXParseException e) throws SAXException {
			System.out.println("Error: "); 
			printInfo(e);
			e.printStackTrace();
		}
		public void fatalError(SAXParseException e) 
				throws SAXException {
			System.out.println("Fatal error: "); 
			printInfo(e);
			e.printStackTrace();
		}
		private void printInfo(SAXParseException e) {

			System.out.println("   Public ID: "+e.getPublicId());
			System.out.println("   System ID: "+e.getSystemId());
			System.out.println("   Line number: "+e.getLineNumber());
			System.out.println("   Column number: "+e.getColumnNumber());
			System.out.println("   Message: "+e.getMessage());
		}
	}
