package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.crudoperations.RetrievePassword;
import com.epam.models.Account;
import com.epam.models.Master;
import com.epam.securitydefence.Security;

 class RetrievePasswordTest {
	static Master master;
RetrievePassword retrieve;
Security security=new Security();
@BeforeEach
public void setUp() {
	 retrieve=new RetrievePassword();
	 CreateMaster.createMasterobj();
	 master=CreateMaster.getMasterobj();
	 Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
	 HashMap<String,Account> object=new HashMap<>();
	 object.put("https://www.epam.com",account);
	 master.getData().put("Epam",object);
}
@Test
void testRetrievePassword1() {
	String[] input= {"Epam","https://www.epam.com","Sjsjs@123","praveen"};
assertEquals("Sjsjs@123",retrieve.retrieveDecrypted(input));

}
@Test
void testRetrievePassword2() {
	String[] input= {"google","https://www.google.com","Sjsjs@123","praveen"};
assertEquals("No account present in groups",retrieve.retrieveDecrypted(input));

}
@Test
void testRetrieve1() {
	String[] input= {"google","https://www.google.com"};
assertEquals("no account",retrieve.retrieve(input));

}
@Test
void testRetrieve2() {
	String[] input= {"Epam","https://www.epam.com"};
assertEquals("Sjsjs@123",retrieve.retrieve(input));

}
}
