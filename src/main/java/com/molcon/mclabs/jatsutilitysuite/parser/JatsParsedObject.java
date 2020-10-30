package com.molcon.mclabs.jatsutilitysuite.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Bean for CCS document [xml/pdf]
 * @author naima.v
 * @author tanvi.h
 */
public class JatsParsedObject {
	
	public enum PARSER {
		XML, ITEXT, GROBID, ITEXT_GROBID;
	}
	
	private PARSER parserType;
	private String articleType;
	private String contentValidityFlag;
	private String filePath;
	private FrontTags frontTags;
	private Sections sections;
	private List<String> figures;
	private List<String> tables;
	private Document document;

	public JatsParsedObject(PARSER pType, String filepath, Document document) {
		this.parserType = pType;
		this.filePath = filepath;
		this.document = document;
		setArticleType(getNodeValue("/article/@article-type"));
		findFloatsGroup();
		findFrontTags();
		findBodyContents();
		findVContents();	
	}
	
	public Sections getSections() {
		return sections;
	}

	public void setSections(Sections sections) {
		this.sections = sections;
	}
	
	public String getContentValidityFlag() {
		return contentValidityFlag;
	}

	public void setContentValidityFlag(String contentValidityFlag) {
		this.contentValidityFlag = contentValidityFlag;
	}
	
	public List<String> getFigures() {
		return figures;
	}

	public void setFigures(List<String> figures) {
		this.figures = figures;
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType.replaceAll("\n","").replaceAll("\\s\\s+"," ");
	}
	
	public FrontTags getFrontTags() {
		return frontTags;
	}

	public void setFrontTags(FrontTags frontTags) {
		this.frontTags = frontTags;
	}
	
	private void findFloatsGroup() {
		
		figures = new ArrayList<String>();
		tables = new ArrayList<String>();
		
		switch (parserType) {
		case XML:
			figures = getNodeValues("/article/floats-group/fig");
			tables = getNodeValues("/article/floats-group/table-wrap");
			break;
		case ITEXT:
			// Do nothing
			break;
		case GROBID:
			// Do nothing
			break;
		case ITEXT_GROBID:
			// Do nothing
			break;
		default:
			break;
		}
	}
	
	private void findFrontTags() {
		frontTags = new FrontTags();
		
		switch (parserType) {
		case XML:
			frontTags.setJournalId(getNodeValue("//front//journal-id[@journal-id-type=\"coden\"]"));
			frontTags.setJournalTitle(getNodeValue("//front//journal-title"));
			frontTags.setPublisherName(getNodeValue("//front//publisher-name"));
			frontTags.setAipid(getNodeValue("//front//article-id[@ pub-id-type=\"publisher-id:aipkey\"]"));
			frontTags.setDoi(getNodeValue("//front//article-id[@ pub-id-type=\"doi\"]"));
			frontTags.setArticleTitle(
					getNodeValue2(
					"/article/front/article-meta/title-group/article-title/self::node()"));

			frontTags.setPubDate(getNodeValue("//front//pub-date/day") + "/" + getNodeValue("//front//pub-date/month") + "/" + getNodeValue("//front//pub-date/year") );
			frontTags.setSubjects(new HashSet<String>(getNodeValues("//front//article-categories//subject")));
			frontTags.setAbsText(getNodeValue("//front/article-meta/abstract"));
			break;
		case ITEXT:
			//Do nothing
			break;
		case GROBID:
			frontTags.setArticleTitle(getNodeValue("//titleStmt/title")); 
			frontTags.setAbsText(getNodeValue("//profileDesc/abstract")); 
			break;
		case ITEXT_GROBID:
			frontTags.setArticleTitle(getNodeValue("//titleStmt/title"));
			frontTags.setAbsText(getNodeValue("//profileDesc/abstract"));
			break;
		default:
			break;
		}
	}
	
	private void findBodyContents() {
		sections = new Sections();
		
		switch (parserType) {
		case XML:
			sections.addSection("title", frontTags.getArticleTitle());
			sections.addSection("abstract", frontTags.getAbsText());
			sections.addSection("subject", String.join(", ", frontTags.getSubjects()));
			String content = String.join(" ", getNodeValues("/article/body/p"));
			if(content.trim().length() > 0)	sections.addSection("Content", content);
			
			try {
				XPath xPath =  XPathFactory.newInstance().newXPath();
				NodeList nodes = (NodeList) xPath.compile("//body/sec").evaluate(document, XPathConstants.NODESET);
				String t;
				StringBuilder sb;
				
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					NodeList childNodes = node.getChildNodes();
					t = "";
					sb = new StringBuilder();
					for (int j = 0; j < childNodes.getLength(); j++) {
						if(childNodes.item(j).getNodeName().equalsIgnoreCase("label")) continue;
						if(childNodes.item(j).getNodeName().equalsIgnoreCase("title")) t = childNodes.item(j).getTextContent();
						else {
							sb.append(norm(childNodes.item(j).getTextContent() + " "));
						}
					}
					
					NodeList subTitles = (NodeList) xPath.compile("sec/title").evaluate(node, XPathConstants.NODESET);
					
					for (int j = 0; j < subTitles.getLength(); j++) {
						String subtitle = subTitles.item(j).getTextContent();
						if(subtitle != null && subtitle.length() > 0) {
							sb = new StringBuilder(sb.toString().replaceAll(Pattern.quote(subtitle), " " + subtitle + " " ));
						}
					}
					
					sections.addSection(t, sb.toString().trim());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case ITEXT:
			// do nothing
			break;
		case GROBID:
			sections.addSection("title", frontTags.getArticleTitle());
			sections.addSection("abstract", frontTags.getAbsText());
			sections.addSection("subject", String.join(", ", frontTags.getSubjects()));
			
			String content_ = norm(getNodeValue("/TEI/text/body"));
			if(content_.trim().length() > 0)	sections.addSection("Content", content_);
			
			break;
		case ITEXT_GROBID:
			sections.addSection("title", frontTags.getArticleTitle());
			sections.addSection("abstract", frontTags.getAbsText());
			sections.addSection("subject", String.join(", ", frontTags.getSubjects()));
			break;
		default:
			break;
		}
	}

	public  void findVContents() {
		String content = "invalid";
		if(sections.size() > 3) content = "fulltext"; // since 3 sections are default [title, abstract, subject]
		else if(frontTags.getAbsText() != null && frontTags.getAbsText().trim().length() > 0) content = "abstract";
		setContentValidityFlag(content);
	}
	
	private String getNodeValue(String expression) {
		try {
			XPath xPath =  XPathFactory.newInstance().newXPath();
			return norm((String) xPath.compile(expression).evaluate(document, XPathConstants.STRING));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getNodeValue2(String expression) {
		try {
			XPath xPath =  XPathFactory.newInstance().newXPath();
			
			StringBuilder sb = new StringBuilder();
			
			NodeList nodes = ((Node) xPath.compile(expression).evaluate(document, XPathConstants.NODE)).getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				sb.append(norm(nodes.item(i).getTextContent()) + " ");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<String> getNodeValues(String expression) {
		ArrayList<String> values = new ArrayList<String>();
		try {
			XPath xPath =  XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for (int i = 0; i < nodes.getLength(); i++) {
				values.add(norm(nodes.item(i).getTextContent()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}
	
	private String norm(String text) {
		return text.replaceAll("\n", " ").replaceAll("\\s+", " ");
	}
}