package com.practice.rating.service.services;

import java.util.List;

import com.practice.rating.service.entities.Rating;

public interface RatingService {

	//create rating
	Rating createRating(Rating rating);
	
	//get all rating
	List<Rating> getAllRating();
	
	//get rating by user Id
	List<Rating> getRatingByUserId(String userId);
	
	//get rating by hotel id
	List<Rating> getRatingByHotelId(String hotelId);
}
