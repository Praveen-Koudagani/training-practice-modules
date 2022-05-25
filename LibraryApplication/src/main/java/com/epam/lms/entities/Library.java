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
@Table(name="LIBRARY")
@Getter
@Setter
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",updatable=false,nullable = false)
	int id;
    @Column
    String username;
    @Column(unique=true)
    int bookId;
}
