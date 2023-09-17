package com.practice.rating.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.rating.service.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {

	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
}
