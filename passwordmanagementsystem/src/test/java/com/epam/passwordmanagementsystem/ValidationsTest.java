package com.epam.passwordmanagementsystem;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.CreateMaster;
import com.epam.models.Account;
import com.epam.models.Master;
import com.epam.validations.Validations;

public class ValidationsTest {
Validations validations;
static Master master;
static void setUpMaster() {
CreateMaster.createMasterobj();
master=CreateMaster.getMasterobj();
Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","Epam");
HashMap<String,Account> object=new HashMap<>();
object.put("https://www.epam.com",account);
master.getData().put("Epam",object);
}
@BeforeEach
public void setUp() {
	    setUpMaster();
		validations=new Validations();
}
@Test
void testValidatePassword1() {
assertEquals(true,validations.validatePassword("Ram@1234"));

}
@Test
void testValidatePassword2() {
assertEquals(false,validations.validatePassword("Ramu1234"));

}
@Test
void testValidatePassword3() {
assertEquals(false,validations.validatePassword("Ram@123"));

}
@Test
void testValidatePassword4() {
assertEquals(false,validations.validatePassword(""));

}

@Test
void testGroupValid1() {
assertEquals(true,validations.isgroupValid("Google"));
}
@Test
void testGroupValid2() {
assertEquals(false,validations.isgroupValid("Google.com"));
}
@Test
void testGroupValid3() {
assertEquals(false,validations.isgroupValid("Google2"));
}
@Test
void testIsValidUrl1() {
assertEquals(true,validations.isValidUrl("https://www.epam.com"));

}
@Test
void testIsValidUrl2() {
assertEquals(false,validations.isValidUrl(""));

}
@Test
void testIsValidUrl3() {
assertEquals(true,validations.isValidUrl("http://www.google.com"));

}
@Test
void testIsValidUrl4() {
assertEquals(false,validations.isValidUrl("www.google.com"));

}
@Test
void testIsAccountAddable1() {
assertEquals(true,validations.isAccountUnique("https://git.epam.com", "Epam"));

}
@Test
void testIsAccountAddable2() {

assertEquals(false,validations.isAccountUnique("https://www.epam.com", "Epam"));

}
@Test
void testIsAccountAddable3() {
	
assertEquals(true,validations.isAccountUnique("https://www.google.com", "Epam"));

}
}
