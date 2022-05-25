package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.crudoperations.UpdateAccountPassword;
import com.epam.models.Account;
import com.epam.models.Master;
import com.epam.securitydefence.Security;

 class UpadateAccountPasswordTest {
	static Master master;
UpdateAccountPassword updateobject;
Security security=new Security();
@BeforeEach
public void setUp() {
	 updateobject=new UpdateAccountPassword();
	 CreateMaster.createMasterobj();
	 master=CreateMaster.getMasterobj();
	 Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
	 HashMap<String,Account> object=new HashMap<>();
	 object.put("https://www.epam.com",account);
	 master.getData().put("Epam",object);
}
@Test
void testUpdatePassword1() {
	String[] input= {"https://www.epam.com","Xyz@123"};
assertEquals("Modified Successfully",updateobject.update(input));

}
@Test
void testUpdatePassword2() {
	String[] input= {"https://git.epam.com","Xyz@123"};
assertEquals("no such Account",updateobject.update(input));

}
@Test
void testUpdateUrl1() {
	String[] input= {"https://git.epam.com","https://kb.epam.com"};
assertEquals("no such Account",updateobject.updateURL(input));

}
@Test
void testUpdateUrl2() {
	String[] input= {"https://www.epam.com","https://kb.epam.com"};
assertEquals("Modified Successfully",updateobject.updateURL(input));
}
}
