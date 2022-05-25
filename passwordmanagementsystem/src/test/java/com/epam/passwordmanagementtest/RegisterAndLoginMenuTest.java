package com.epam.passwordmanagementtest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import com.epam.passwordmanagementsystem.RegisterAndLoginMenu;
import com.epam.passwordmanagementsystem.UnClosableInputStream;

class RegisterAndLoginMenuTest {
	@Test
	void testPasswordCheck() {
		String input="pavi1234 Pavi@1234  pavi pavi@1234 King@11234 ";
		InputStream in=new UnClosableInputStream(input.getBytes());
		System.setIn(in);
		 assertTrue(RegisterAndLoginMenu.passwordCheck() instanceof String);
	}
	@Test
	void testRegisterMaster() {
		RegisterAndLoginMenu.registerAsMaster();
		assertNotEquals(0,RegisterAndLoginMenu.master.getPassword().length());
		assertNotEquals(0,RegisterAndLoginMenu.master.getUsername().length());

	}
	
	
}
