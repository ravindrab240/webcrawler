package com.mywebcrawler.webcrawlerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableMongoRepositories(basePackages="com.mywebcrawler.webcrawlerservice")
@RibbonClient(name="crawler-a-server", configuration=RibbonConfiguration.class)
public class WebcrawlerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcrawlerserviceApplication.class, args);
	}
	
	@LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
