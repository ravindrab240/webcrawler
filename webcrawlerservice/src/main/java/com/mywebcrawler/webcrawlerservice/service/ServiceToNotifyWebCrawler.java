package com.mywebcrawler.webcrawlerservice.service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mywebcrawler.webcrawlerservice.model.UrlData;
import com.mywebcrawler.webcrawlerservice.repository.UrlDataRepository;

@Service
public class ServiceToNotifyWebCrawler {
	
	@Autowired
	UrlDataRepository urlDataRepository;
	
	@Autowired
	@LoadBalanced
	RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		scheduleCrawlService(service);
	}

	private void scheduleCrawlService(ScheduledExecutorService service) {
		Runnable runnable = () -> {
			List<UrlData> datas = urlDataRepository.findAll();
			datas.forEach(data -> {
				System.out.println(data.getUrlname());
				HttpEntity<String> request = new HttpEntity<String>(data.getUrlname(), new HttpHeaders());
				try {
					ResponseEntity<String> response = restTemplate.postForEntity("http://crawler-server/webcrawler/crawl", request, String.class);
					System.out.println(response.getBody());
				} catch (Exception e) {
					System.out.println(e);
				}
				
			});
		};
	    service.scheduleWithFixedDelay(runnable, 0, 30, TimeUnit.SECONDS);
	}
	
}
