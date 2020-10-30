package com.molcon.mclabs.jatsutilitysuite.transformation;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.lib.FeatureKeys;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public class SaxonProcessor {
	private Processor processor;
	private XsltCompiler xsltCompiler;
	private XsltExecutable xsltExecutable;
	private XsltTransformer xsltTransformer;
	
	public SaxonProcessor(String xsltPath) {
		processor = new Processor(false);
		processor.setConfigurationProperty(FeatureKeys.DTD_VALIDATION, false);
		processor.setConfigurationProperty(FeatureKeys.DTD_VALIDATION_RECOVERABLE, true);
		xsltCompiler = processor.newXsltCompiler();
		xsltCompiler.setSchemaAware(false);
		Source xslDoc = new StreamSource(new File(xsltPath));
		try {
			xsltExecutable = xsltCompiler.compile(xslDoc);
			xsltTransformer = xsltExecutable.load();
		} catch (SaxonApiException e) {
			e.printStackTrace();
		}
	}
	
	public Document parseXML(File file) {
		Document doc = null;
		try {
			SAXReader reader = new SAXReader();
			reader.setEncoding("utf-8");
			reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			doc = reader.read(file);
			} catch (SAXException e) {
				doc = null;
				e.printStackTrace();
				} catch (DocumentException e) {
					doc = null;
					e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
		return doc;
		}
	
	public String transform(File file) {
		try {
			xsltTransformer.setSource(new DocumentSource(parseXML(file)));
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			Serializer serializer = processor.newSerializer(os);
		    xsltTransformer.setDestination(serializer);
			xsltTransformer.transform();
			return new String(os.toByteArray(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
