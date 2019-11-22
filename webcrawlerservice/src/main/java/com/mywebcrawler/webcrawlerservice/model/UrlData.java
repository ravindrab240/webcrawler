package com.mywebcrawler.webcrawlerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="urldata")
public class UrlData {
	
	@Id
	private String urlname;

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
}
