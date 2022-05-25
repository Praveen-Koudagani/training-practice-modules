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
import com.epam.uiservices.ViewByGroupUI;

public class ViewUITest {
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
	void testView1(){
		String input="epam ";
		InputStream in=new UnClosableInputStream(input.getBytes());
		System.setIn(in);
		ViewByGroupUI.display();
		for(String url:ViewByGroupUI.groupdata.keySet()) {
		assertEquals("https://kb.epam.com",ViewByGroupUI.groupdata.get(url).getUrl());
		assertEquals("praveen",ViewByGroupUI.groupdata.get(url).getUsername());
		assertEquals("Sjsjs@123",ViewByGroupUI.groupdata.get(url).getDecryptedPassword());
		assertEquals("epam",ViewByGroupUI.groupdata.get(url).getGroup());
		}
	}
}
