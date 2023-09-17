package com.practice.rating.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.rating.service.entities.Rating;
import com.practice.rating.service.repositories.RatingRepository;
import com.practice.rating.service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	RatingRepository ratingRepo;
	
	@Override
	public Rating createRating(Rating rating) {
		String ratingId=UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepo.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepo.getRatingByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepo.getRatingByHotelId(hotelId);
	}

	
}
