package com.practice.hotel.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.hotel.service.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{ 

}
