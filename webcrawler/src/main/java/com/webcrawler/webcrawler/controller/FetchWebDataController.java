package com.webcrawler.webcrawler.controller;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FetchWebDataController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping(value="/crawl")
	public ResponseEntity<String> fetchWebData(@RequestBody String urllink) {
		System.out.println("Recieved: "+urllink);
		try {
			URL url = new URL(urllink);
			URLConnection connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			Path tempFile = Files.createTempDirectory("").resolve(UUID.randomUUID().toString() + ".tmp");
		    Files.copy(stream, tempFile, StandardCopyOption.REPLACE_EXISTING);
		    String result = new String(Files.readAllBytes(tempFile));
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e);
		}
		return ResponseEntity.ok().body("Success");
	}
}
