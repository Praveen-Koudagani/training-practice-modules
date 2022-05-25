package com.epam.crudoperationstest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.crudoperations.Create;
import com.epam.crudoperations.CreateAccount;
import com.epam.crudoperations.CreateMaster;
import com.epam.models.Account;
import com.epam.models.Master;

class CreateAccountTest {
static Create create;
static Master master;
HashMap<String,Account> object;
@BeforeAll
static void setUp() {
	create= new CreateAccount();
	 CreateMaster.createMasterobj();
	 master=CreateMaster.getMasterobj();
}
@BeforeEach
void setUp2() {
	Account account=new Account("https://www.epam.com","praveen","Sjsjs@123","epam");
	 object=new HashMap<>();
	 object.put("https://www.epam.com",account);
	 master.getData().put("epam",object);
}

@Test
void testCreate1() {
	String[] input= {"https://git.epam.com","King@1234","praveen","epam"};
assertEquals("Success",create.create(input));

}
@Test
void testCreate2() {
	String[] input= {"https://www.epam.com","King@1234","praveen","epam"};
assertEquals("Account already present in group",create.create(input));

}
}
