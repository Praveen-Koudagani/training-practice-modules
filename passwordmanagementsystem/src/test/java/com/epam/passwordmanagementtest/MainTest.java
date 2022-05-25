package com.epam.passwordmanagementtest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.InputStream;
import org.junit.jupiter.api.Test;

import com.epam.passwordmanagementsystem.Main;
import com.epam.passwordmanagementsystem.RegisterAndLoginMenu;
import com.epam.passwordmanagementsystem.UnClosableInputStream;

 class MainTest {
	@Test
	void testRegisterMaster() {
        String input="1\n pavi "
        		+ "Pavi@1234 "
        		+ "2 "
        		+ "Pavi "
        		+ "2 "
        		+ "pavi "
        		+ "pavi1234 "
        		+ "2 "
        		+ "pavi "
        		+ "Pavi@1234 "
        		+ "1 "
        		+ "epam "
        		+ "https "
        		+ "https://epam.com "
        		+ "pavi "
        		+ "pavi "
        		+ "Pavi@1234 "
        		+ "2 "
        		+ "https "
        		+ "epam "
        		+ "2 "
        		+ "https://epam.com "
        		+ "epam "
        		+ "3 "
        		+ "epam "
        		+ "1 "
        		+ "epam1 "
        		+ "google "
        		+ "https://google.com "
        		+ "praveen "
        		+ "Pavi@1234 "
        		+ "5 "
        		+ "1 "
        		+ "https://google.com "
        		+ "epam "
        		+ "King@1234 "
        		+ "2 "
        		+ "https://epam.com "
        		+ "https://kb.epam.com "
        		+ "3 "
        		+ "3 "
        		+ "epam "
        		+ "3 "
        		+ "google "
        		+ "6 "
        		+ "goog "
        		+ "googlellc "
        		+ "5 "
        		+ "google "
        		+ "1 "
        		+ "https://epam.com "
        		+ "Pavi@1234 "
        		+ "2 "
        		+ "https://epam.com "
        		+ "https://git. "
        		+ "3 "
        		+ "3 "
        		+ "google "
        		+ "3 "
        		+ "epams "
        		+ "3 "
        		+ "epam "
        		+ "4 "
        		+ "https:// "
        		+ "https://google.com "
        		+ "4 "
        		+ "https://google.com "
        		+ "3 "
        		+ "google "
        		+ "3 "
        		+ "epam "
        		+ "6 "
        		+ "epam "
        		+ "epamsys "
        		+ "7 "
        		+ "epam "
        		+ "7 "
        		+ "epamsys "
        		+ "3 "
        		+ "epamsys "
        		+ "8 "
        		+ "2 "
        		+ "pavi "
        		+ "pavi "
        		+ "Pavi "
        		+ "2 "
        		+ "pavi "
        		+ "Pavi@1234 "
        		+ "8 "
        		+ "3 "
        		+ "pavi "
        		+ "Pavi@1234 "
        		+ "pavi "
        		+ "Pavi@1234 "
        		+"epam "
        		+"pavi "+"https://pavi.com "+"pavi "+"Pavi@1234 "
        		+"epam "+"https://kb.epam.com https://git.epam.com "+"praveen "+"King@1234 "
        		+"https://kb.epam.com epam "
        		+"https://kb.epam.com "+"epam ";
        
		//InputStream in=new ByteArrayInputStream(input.getBytes());
        InputStream in=new UnClosableInputStream(input.getBytes());
		System.setIn(in);
		Main.mainMenu();
		assertNotEquals(0,RegisterAndLoginMenu.master.getPassword().length());
		assertNotEquals(0,RegisterAndLoginMenu.master.getUsername().length());
		assertTrue(RegisterAndLoginMenu.loginstatus);
		//System.setIn(new UnClosableDecorator(System.in));
	}
}
