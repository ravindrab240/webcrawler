package com.mywebcrawler.webcrawlerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mywebcrawler.webcrawlerservice.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
