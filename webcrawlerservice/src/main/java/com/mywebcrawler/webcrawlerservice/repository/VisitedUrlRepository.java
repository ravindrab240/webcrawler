package com.mywebcrawler.webcrawlerservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mywebcrawler.webcrawlerservice.model.VisitedUrl;

@Repository
public interface VisitedUrlRepository extends CrudRepository<VisitedUrl, String>{
	
}
