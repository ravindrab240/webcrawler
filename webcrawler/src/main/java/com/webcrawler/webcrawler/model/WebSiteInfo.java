package com.webcrawler.webcrawler.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="websiteinfo")
public class WebSiteInfo {
	
	private String url;
	private String webcontent;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWebcontent() {
		return webcontent;
	}
	public void setWebcontent(String webcontent) {
		this.webcontent = webcontent;
	}
}
