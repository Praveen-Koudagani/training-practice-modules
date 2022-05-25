package com.epam.models;

import java.util.HashMap;

public class Master {
	private String username;
	private String password;
	private HashMap<String,HashMap<String,Account>> data=new HashMap<>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public HashMap<String, HashMap<String, Account>> getData() {
		return this.data;
	}
	public void setData(HashMap<String, HashMap<String, Account>> data) {
		this.data = data;
	}

}
