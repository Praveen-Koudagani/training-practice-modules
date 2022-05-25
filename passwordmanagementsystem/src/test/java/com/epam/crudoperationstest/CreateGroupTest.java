package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateGroup;
import com.epam.crudoperations.CreateMaster;
import com.epam.models.Account;
import com.epam.models.Master;

 class CreateGroupTest {
	static Master master;
	CreateGroup createobject;
	HashMap<String,Account> object;
	@BeforeEach
	public void setUp() {
		 createobject=new CreateGroup();
		 CreateMaster.createMasterobj();
		 master=CreateMaster.getMasterobj();
		 Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
		 object=new HashMap<>();
		 object.put("https://www.epam.com",account);
		 master.getData().put("Epam",object);
	}
	@Test
	void testCreateGroup1() {
		String[] input= {"Epam"};
	assertEquals("Group Already Exists you can Add accounts",createobject.create(input));

	}
	@Test
	void testCreateGroup2() {
		String[] input= {"EpamSystems"};
	assertEquals("Added",createobject.create(input));

	}
}
