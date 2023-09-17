package com.practice.hotel.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.hotel.service.entities.Hotel;
import com.practice.hotel.service.exceptions.ResourceNotFoundException;
import com.practice.hotel.service.repositories.HotelRepository;
import com.practice.hotel.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	HotelRepository hotelRepo;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String hotelId=UUID.randomUUID().toString();
		hotel.setHotelId(hotelId);
		return hotelRepo.save(hotel);
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		return hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotel with given id is not found:"+hotelId));
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepo.findAll();
	}

}
