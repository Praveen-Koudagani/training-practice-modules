package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.crudoperations.UpdateGroupDetails;
import com.epam.models.Account;
import com.epam.models.Master;

 class UpdateGroupDetailsTest {
	static Master master;
	UpdateGroupDetails updateobject;
	@BeforeEach
	public void setUp() {
		 updateobject=new UpdateGroupDetails();
		 CreateMaster.createMasterobj();
		 master=CreateMaster.getMasterobj();
		 Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
		 HashMap<String,Account> object=new HashMap<>();
		 object.put("https://www.epam.com",account);
		 master.getData().put("Epam",object);
	}
	@Test
	void testUpdateGroup1() {
		String[] input= {"Epam","EpamSystems"};
	assertEquals("Modified",updateobject.update(input));

	}
	@Test
	void testUpdateGroup2() {
		String[] input= {"gmail","google"};
	assertEquals("No such Group",updateobject.update(input));

	}
}
