package com.practice.hotel.service.services;

import java.util.List;

import com.practice.hotel.service.entities.Hotel;

public interface HotelService {

	//create
	Hotel createHotel(Hotel hotel);
	
	//get Hotel by id
	Hotel getHotelById(String hotelId);
	
	//get all Hotel
	List<Hotel> getAllHotel();
}
