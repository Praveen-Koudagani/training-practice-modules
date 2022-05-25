package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.crudoperations.DeleteAccount;
import com.epam.models.Account;
import com.epam.models.Master;

 class DeleteAccountTest {
	static Master master;
	DeleteAccount deleteobject;
	HashMap<String,Account> object;
	@BeforeEach
	public void setUp() {
		 deleteobject=new DeleteAccount();
		 CreateMaster.createMasterobj();
		 master=CreateMaster.getMasterobj();
		 Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
		 object=new HashMap<>();
		 object.put("https://www.epam.com",account);
		 master.getData().put("Epam",object);
	}
	@Test
	void testDeleteAccount1() {
	assertEquals("Deleted Successfully",deleteobject.delete("https://www.epam.com"));

	}
}
