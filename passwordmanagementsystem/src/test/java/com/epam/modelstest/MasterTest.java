package com.epam.modelstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.models.Account;
import com.epam.models.Master;

public class MasterTest {
	static Master master;
	static public HashMap<String,HashMap<String,Account>> groups;
	@BeforeEach
void setUpMaster() {
	CreateMaster.createMasterobj();
	master=CreateMaster.getMasterobj();
	Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
	HashMap<String,Account> accountsobject=new HashMap<>();
	accountsobject.put("https://www.epam.com",account);
	groups=new HashMap<>();
	groups.put("Epam",accountsobject);
	master.setData(groups);
	}
	@Test
	void testGetGroupsData() {
		master.setData(groups);
	assertEquals(groups,master.getData());

	}
	
	@Test
	void testGetPassword1() {
		master.setPassword("King@12345");
	assertEquals("King@12345",master.getPassword());

	}
	@Test
	void testGetUserName1() {
		master.setUsername("Praveen");
	assertEquals("Praveen",master.getUsername());

	}
	
	
	
}
