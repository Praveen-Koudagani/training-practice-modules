package com.epam.modelstest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.models.Account;
import com.epam.securitydefence.Security;

public class AccountTest {
Account account;
Security security=new Security();
@BeforeEach
public void setUp() {
account=new Account("https://git.epam.com","praveenraj","Good@1234","epam");
}
@Test
void testGetPassword1() {
assertEquals(security.encrypt("Good@1234"),account.getPassword());

}
@Test
void testGetPassword2() {
assertEquals("Good@1234",account.getDecryptedPassword());

}
@Test
void testGetUrl() {
assertEquals("https://git.epam.com",account.getUrl());
}
@Test
void testGetGroup1() {
assertEquals("epam",account.getGroup());
}
@Test
void testGetUserName() {
assertEquals("praveenraj",account.getUsername());
}
@Test
void testSetPassword() {
	account.setPassword("Praveen@344$");
assertEquals("Praveen@344$",account.getDecryptedPassword());

}
@Test
void testSetUrl() {
	account.setUrl("https://kb.epam.com");
assertEquals("https://kb.epam.com",account.getUrl());
}
@Test
void testSetGroup() {
	account.setGroup("EpamSystems");
assertEquals("EpamSystems",account.getGroup());
}
@Test
void testSetUserName() {
	account.setUsername("PraveenKoudagani");
assertEquals("PraveenKoudagani",account.getUsername());
}

}
