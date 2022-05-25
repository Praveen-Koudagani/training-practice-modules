package com.epam.models;

import com.epam.securitydefence.Security;

public class Account {
	private String url;
	private String username;
	private String password;
	private String group;
	public Account(String url,String username,String password,String group) {
		Security obj=new Security();
		this.url=url;
		this.username=username;
		this.password=obj.encrypt(password);
		this.group=group;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
		Security obj=new Security();
		this.password =obj.encrypt(password);
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getDecryptedPassword() {
		Security obj=new Security();

		return obj.decrypt(this.password);
	}
}
