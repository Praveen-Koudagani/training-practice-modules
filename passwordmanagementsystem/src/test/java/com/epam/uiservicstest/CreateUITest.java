package com.epam.uiservicstest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.Create;
import com.epam.crudoperations.CreateAccount;
import com.epam.crudoperations.CreateMaster;
import com.epam.models.Account;
import com.epam.models.Master;
import com.epam.passwordmanagementsystem.UnClosableInputStream;
import com.epam.uiservices.CreateUI;

class CreateUITest {
	Create create;
	Master master;
	HashMap<String,Account> object;
	@BeforeEach
	void setUp() {
			create= new CreateAccount();
			CreateMaster.createMasterobj();
			master=CreateMaster.getMasterobj();
			Account account=new Account("https://kb.epam.com","praveen","Sjsjs@123","epam");
			object=new HashMap<>();
			object.put("https://kb.epam.com",account);
			master.getData().put("epam",object);
			 
	}
	@Test
	void testCreateUI1() {
		String input="epam1 epam www.epam.com https://epam.com praveen king@12 King@1234 "
				+"epam https://kb.epam.com https://git.epam.com praveen King@1234 ";
		InputStream in=new UnClosableInputStream(input.getBytes());
		System.setIn(in);
		CreateUI.addAccount();
		assertEquals("Success",CreateUI.status);
		
	}
	@Test
	void testCreateUi2() {
		CreateUI.addAccount();
		assertEquals("Success",CreateUI.status);
	}

}





/*
 * String
 * input="epam1 epam www.epam.com https://epam.com praveen king@12 King@1234";
 * InputStream in=new ByteArrayInputStream(input.getBytes()); System.setIn(in);
 */
