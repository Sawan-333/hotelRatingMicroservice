package com.practice.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="micro_users")
public class User {

	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="NAME", length=30)
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MOBILE")
	private Integer mobile;
	
	@Transient
	private List<Rating> ratings =new ArrayList<>();

	
	
}
