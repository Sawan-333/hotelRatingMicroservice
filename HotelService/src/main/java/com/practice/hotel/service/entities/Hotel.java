package com.practice.hotel.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="hotels")
@Entity
public class Hotel {
	
	@Id
	private String hotelId;
	
	private String hotelName;
	
	private String hotelAdd;
	
	private String aboutHotel;

}
