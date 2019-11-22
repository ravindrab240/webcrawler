package com.webcrawler.webcrawler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webcrawler.webcrawler.model.WebSiteInfo;

@Repository
public interface WebSiteInfoRepository extends MongoRepository<WebSiteInfo, String>{

}
