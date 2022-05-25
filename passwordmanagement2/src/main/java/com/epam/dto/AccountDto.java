package com.epam.dto;

import org.springframework.stereotype.Component;

import com.epam.entities.Master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Getter
@Setter
@NoArgsConstructor
public class AccountDto {


	private long id;

	private String url;
	
	private String username;
	
	private String password;
	private String groupname;
	
	private Master master;
	
}
