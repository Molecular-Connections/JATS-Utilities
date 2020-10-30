package com.molcon.mclabs.jatsutilitysuite.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FrontTags {
	
	private String journalTitle="";
	private String issn="";
	private String publisherName="";
	private String doi=""; 
	private String aipid="";
	private String articleTitle="";
	private String pubDate="";
	private String absText = "";
	
	public String getAbsText() {
		return absText;
	}

	public void setAbsText(String absText) {
		this.absText = absText;
	}
	private List<String> contribName=new ArrayList<String>();
	private List<String> historyDate=new ArrayList<String>() ;
	private Set<String> subjects = new HashSet<String>();

	public Set<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<String> subjects) {
		this.subjects = subjects;
	}
	String journalId="";
	public String getJournalId() {
		return journalId;
	}

	public void setJournalId(String journalId) {
		this.journalId = journalId;
	}
	private Set<String> aff = new HashSet<String>();
	

	public void setAff(Set<String> aff) {
		this.aff = aff;
	}

	private StringBuilder abstractSect;
	public String getJournalTitle() {
		return journalTitle;
	}
	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle.replaceAll("\\n", " ");
	}
	
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getAipid() {
		return aipid;
	}
	public void setAipid(String aipid) {
		this.aipid = aipid;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public List<String> getContribName() {
		return contribName;
	}
	public void setContribName(List<String> contribName) {
		this.contribName = contribName;
	}

	public List<String> getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(List<String> historyDate) {
		this.historyDate = historyDate;
	}
	public StringBuilder getAbstractSect() {
		return abstractSect;
	}
	public void setAbstractSect(StringBuilder abstractSect) {
		this.abstractSect = abstractSect;
	}
	
	@Override
	public String toString() {
		return getJournalTitle()+getIssn()+getAipid()+getArticleTitle()+getDoi()
				+getPubDate()+getPublisherName()+getAbstractSect()+getAff()+getContribName()
				+getHistoryDate()+getSubjects();
	}
	public Set<String> getAff() {
		return aff;
	}
	

}
