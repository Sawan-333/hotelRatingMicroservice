package com.practice.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.user.service.entities.User;
import com.practice.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	//create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User userData = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userData);
	}
	int retryCount= 1;
	//get user by id
	@GetMapping("{userId}")
	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
	//@Retry(name="ratingHotelServiceRetry", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUserById(@PathVariable String userId)
	{
		System.out.printf("Retry Count:", retryCount);
		retryCount++;
		User userData=userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.FOUND).body(userData);
	}
	
	//creating fall back method for circuit breaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex){
		User user=User.builder().email("dummy@gmai.com").name("dumy").build();
		//log.info("Fallback is executed because the service is down:", ex.getMessage());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	//get all user
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> userList=userService.getAllUser();
		return ResponseEntity.ok(userList);
	}
}
