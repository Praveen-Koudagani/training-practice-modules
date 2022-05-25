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
import com.epam.uiservices.ReadPasswordUI;

public class ReadPasswordUITest {
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
	void testReadPassword1() {
		String input="https://kb.epam.com epam ";
		InputStream in=new UnClosableInputStream(input.getBytes());
		System.setIn(in);
		ReadPasswordUI.readPassword();
		assertEquals("Sjsjs@123",ReadPasswordUI.password);
	}
}
