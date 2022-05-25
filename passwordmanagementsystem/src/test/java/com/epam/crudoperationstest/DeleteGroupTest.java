package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.crudoperations.DeleteGroup;
import com.epam.models.Account;
import com.epam.models.Master;

 class DeleteGroupTest {
	static Master master;
	DeleteGroup deleteobject;
	HashMap<String,Account> object;
	@BeforeEach
	public void setUp() {
		 deleteobject=new DeleteGroup();
		 CreateMaster.createMasterobj();
		 master=CreateMaster.getMasterobj();
		 Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
		 object=new HashMap<>();
		 object.put("https://www.epam.com",account);
		 master.getData().put("Epam",object);
	}
	@Test
	void testDeleteGroup1() {
	assertEquals("deleted group successfully",deleteobject.delete("Epam"));
	}
	@Test
	void testDeleteGroup2() {
	assertEquals("no such group",deleteobject.delete("google"));
	}
}
