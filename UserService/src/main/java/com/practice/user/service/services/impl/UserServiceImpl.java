package com.practice.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.user.service.entities.Hotel;
import com.practice.user.service.entities.Rating;
import com.practice.user.service.entities.User;
import com.practice.user.service.exceptions.ResourceNotFoundException;
import com.practice.user.service.external.services.HotelService;
import com.practice.user.service.repositories.UserRepository;
import com.practice.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired	
	private HotelService hotelService;
	
	private Logger log=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String userId) {
		//get user from db wqith the help of user repo
		User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found:"+userId));
		//fetch rating of above user from RATING SERVICE
		//http://localhost:7000/users/f1f1a5c4-97fd-4e0d-9610-e9d95b91b3f0
		Rating[] userRating = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		log.info("rating data: {}",userRating.getClass());
		List<Rating> userRatingList = Arrays.stream(userRating).toList();
		log.info("rating data in array list: {}",userRatingList.toString());
		
		//fetch hotel from hotel id from above userRating data
		//http://localhost:7001/hotels/56271eaf-54db-4f85-b70d-49fad826f214
		List<Rating> ratingList= userRatingList.stream().map(rating-> {
			log.info("rating data inside loop: {}",rating.getHotelId());
			/*ResponseEntity<Hotel> hotels = restTemplate.getForEntity("http://localhost:7001/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel=hotels.getBody();*/
			
			//1.for below code we can use fein client apprach
			//Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			
			Hotel hotel= hotelService.getHotel(rating.getHotelId());// for 1
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	}

	@Override
	public User updateUserData(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(String userId) {
		// TODO Auto-generated method stub
		
	}

}
