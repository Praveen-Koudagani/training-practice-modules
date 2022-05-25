package com.epam.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.epam.security.BCryptService;
@ExtendWith(MockitoExtension.class)
class MasterTest {
	
	@Test
	void testGetUsername() {
		Master master=new Master();
		master.setUsername("pavi");
		assertEquals("pavi",master.getUsername());
	}

	@Test
	void testGetPassword() {
		Master master=new Master();
		
		master.setPassword("pavi@1234");
		assertTrue(new BCryptService().verifyHash("pavi@1234",master.getPassword()));
	}

	@Test
	void testGetAccounts() {
		Master master=new Master();
		List<Account> accounts=new ArrayList<>();
		master.setAccounts(accounts);
		assertNotNull(master.getAccounts());
	}

}
