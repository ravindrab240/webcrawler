package com.mywebcrawler.webcrawlerservice.controller;

import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mywebcrawler.webcrawlerservice.model.User;
import com.mywebcrawler.webcrawlerservice.repository.UserRepository;
import com.mywebcrawler.webcrawlerservice.utils.CommonUtils;

/**
 * @author Ravindra Bakkamanthala
 * 
 * The User login and Registering the new users are handled in this class.
 * All the information will be stored to Mongo db.
 *
 */
@RestController
public class UserLoginController {
	
	private static final Log LOGGER = LogFactory.getLog(UserLoginController.class);
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping(value="/login") 
	public ResponseEntity<?> userLoginValidation(@RequestBody String payload) {
		@SuppressWarnings("deprecation")
		String[] decodeUser = URLDecoder.decode(payload).split(":");
		LOGGER.debug("User Logged In "+decodeUser[0] + " Time: " + LocalDate.now());
		Optional<User> userOpt = userRepository.findById(decodeUser[0]);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (user.getPassword().equals(decodeUser[1])) {
				return ResponseEntity.ok().header(CommonUtils.CROSS_ORIGIN, CommonUtils.STAR).body(user);
			}
			return ResponseEntity.ok()
					.header(CommonUtils.CROSS_ORIGIN, CommonUtils.STAR)
					.body("{\"status\":\"failure\",\"message\":\"Invalid Password\"}");
		}
		
		return ResponseEntity.ok()
				.header(CommonUtils.CROSS_ORIGIN, CommonUtils.STAR)
				.body("{\"status\":\"failure\",\"message\":\"UserName not Found\"}");
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value="/usersignup")
	public ResponseEntity<?> userSignUp(@RequestBody String payload) {
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = mapper.readValue(URLDecoder.decode(payload), User.class);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			LOGGER.error("Exception Occured while mapping data to User class"+ e);
		} 
		if (user != null && user.getEmailId() != null) {
			try {
				user.setPassword(new String(Base64.encode(user.getPassword().getBytes())));
				userRepository.save(user);
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (Exception e) {
				LOGGER.error("Exception Occured while saving the user to database" + user.getEmailId() + e);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

}