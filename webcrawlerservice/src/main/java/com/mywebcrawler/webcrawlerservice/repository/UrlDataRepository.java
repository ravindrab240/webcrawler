package com.mywebcrawler.webcrawlerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mywebcrawler.webcrawlerservice.model.UrlData;

@Repository
public interface UrlDataRepository extends MongoRepository<UrlData, String> {

}
