package com.epam.lms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="LMS_USER")
@Getter
@Setter
public class User {

	@Id
	@Column(name="username",updatable=false,nullable = false)
	String username;
	
	@Column(unique = true)
	String email;
	
}
