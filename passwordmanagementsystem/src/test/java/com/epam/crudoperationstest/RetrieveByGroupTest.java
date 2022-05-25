package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.crudoperations.RetrieveByGroup;
import com.epam.models.Account;
import com.epam.models.Master;

 class RetrieveByGroupTest {
	static Master master;
	RetrieveByGroup retrieveobject;
	HashMap<String,Account> object;
	@BeforeEach
	public void setUp() {
		 retrieveobject=new RetrieveByGroup();
		 CreateMaster.createMasterobj();
		 master=CreateMaster.getMasterobj();
		 Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
		 object=new HashMap<>();
		 object.put("https://www.epam.com",account);
		 master.getData().put("Epam",object);
	}
	@Test
	void testRetrieveByGroup1() {
	assertEquals(object,retrieveobject.retrieve("Epam"));

	}
	@Test
	void testRetrieveByGroup2() {
		String message="contains";
		try {
	retrieveobject.retrieve("Google");
		}
		catch(NoSuchElementException nse) {
			message=nse.getMessage();
		}
	assertTrue(message.contains("No value present"));

	}
}
