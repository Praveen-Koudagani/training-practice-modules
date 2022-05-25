package com.epam.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @Entity
 * 
 * @Table(name = "Group_TABLE") public class Group {
 * 
 * @Id String groupname;
 * 
 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "group") private
 * List<Account> accounts;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "matser_username") private Master master2;
 * 
 * public Master getMaster() { return master; }
 * 
 * public void setMaster(Master master) { this.master = master; }
 * 
 * public String getGroupname() { return groupname; }
 * 
 * public void setGroupname(String groupname) { this.groupname = groupname; }
 * 
 * public List<Account> getAccounts() { return accounts; }
 * 
 * public void setAccounts(List<Account> accounts) {
 * accounts.forEach(account->account.setGroup(this)); this.accounts = accounts;
 * }
 * 
 * 
 * 
 * }
 */