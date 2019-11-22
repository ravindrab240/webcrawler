package com.mywebcrawler.webcrawlerservice.controller;

import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mywebcrawler.webcrawlerservice.model.UrlData;
import com.mywebcrawler.webcrawlerservice.repository.UrlDataRepository;
import com.mywebcrawler.webcrawlerservice.utils.CommonUtils;

/**
 * @author Ravindra Bakkamanthala
 * 
 * From the Web Application users can manage the Url Feed data. (Save/delete/Get)
 * The data is stored to MongoDb.
 *
 */
@RestController
public class UrlFeedDataController {
	
	private static final Log LOGGER = LogFactory.getLog(UrlFeedDataController.class);
	
	
	@Autowired
	UrlDataRepository urlDataRepository;
	
	@GetMapping(value="/getUrlFeedData")
	public ResponseEntity<List<UrlData>> getUrlFeedData() {
		List<UrlData> data = urlDataRepository.findAll();
		return ResponseEntity.ok().header(CommonUtils.CROSS_ORIGIN, CommonUtils.STAR).body(data);
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value="/saveUrlFeedData")
	public ResponseEntity<String> saveUrlFeedData(@RequestBody String url) {
		LOGGER.debug("Saving the Url data to database"+ url);
		UrlData data = new UrlData();
		data.setUrlname(URLDecoder.decode(url.substring(0, url.length()-1)));
		urlDataRepository.save(data);
		return ResponseEntity.ok()
				.header(CommonUtils.CROSS_ORIGIN, CommonUtils.STAR)
				.body("{\"status\":\"success\"}");
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value="/deleteUrlFeedData")
	public ResponseEntity<String> deleteUrlFeedData(@RequestBody String url) {
		LOGGER.debug("Deleting the Url data to database"+ url);
		urlDataRepository.deleteById(URLDecoder.decode(url.substring(0, url.length()-1)));
		return ResponseEntity.ok()
				.header(CommonUtils.CROSS_ORIGIN, CommonUtils.STAR)
				.body("{\"status\":\"success\"}");
	}

}
