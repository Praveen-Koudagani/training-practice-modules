package com.epam.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.epam.security.BCryptService;

@Entity
@Table(name = "MASTER_TABLE")
public class Master {
	@Id
	private String username;
	@Column
	private String password;
	boolean enabled = true;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "master")
	private List<Account> accounts;

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
		BCryptService bcryptObject=new BCryptService();
		this.password = bcryptObject.hash(password);
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		accounts.forEach(account -> account.setMaster(this));
		this.accounts = accounts;
	}

	/*
	 * public boolean isEnabled() { return enabled; }
	 * 
	 * public void setEnabled(boolean enabled) { this.enabled = enabled; }
	 */
}
