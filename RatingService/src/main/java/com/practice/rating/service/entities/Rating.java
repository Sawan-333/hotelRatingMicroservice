package com.practice.rating.service.entities;

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
@Entity
@Table(name="ratings")
public class Rating {
	
	@Id
	private String ratingId;
	private String userId;
	private String hotelId;
	private Integer rating;
	private String feedback;

}
