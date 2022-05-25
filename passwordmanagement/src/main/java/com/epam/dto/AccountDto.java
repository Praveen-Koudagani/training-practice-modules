package com.epam.dto;

import org.springframework.stereotype.Component;

import com.epam.entities.Master;

import lombok.Data;
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
	/*
	 * public long getId() { return this.id; } public String getUrl() { return url;
	 * } public void setUrl(String url) { this.url = url; } public String
	 * getUsername() { return username; } public void setUsername(String username) {
	 * this.username = username; } public String getPassword() { return password; }
	 * public void setPassword(String password) { this.password = password; } public
	 * String getGroupname() { return groupname; } public void setGroupname(String
	 * groupname) { this.groupname = groupname; } public Master getMaster() { return
	 * master; } public void setMaster(Master master) { this.master = master; }
	 */
	
}
