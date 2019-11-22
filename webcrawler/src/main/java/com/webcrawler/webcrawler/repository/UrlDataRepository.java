package com.webcrawler.webcrawler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webcrawler.webcrawler.model.UrlData;

@Repository
public interface UrlDataRepository extends MongoRepository<UrlData, String>{
	
}
