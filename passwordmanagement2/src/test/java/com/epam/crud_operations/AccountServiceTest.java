package com.epam.crud_operations;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dao.AccountRepository;
import com.epam.dto.AccountDto;
import com.epam.entities.Account;
import com.epam.entities.Master;
import com.epam.exceptions.AccountDuplicationException;
import com.epam.exceptions.AccountNotFoundException;
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@InjectMocks
	AccountService accountservice;
	@Mock
	AccountRepository accountrepo;
	@Mock
	Master master;
	Account account;
	Account account2=null;
	@BeforeEach
	public void setUp() {
		 account=new Account();
		account.setGroupname("google");
		account.setPassword("Pavi@1234");
		account.setUrl("https://googledrive.com");
	}
	@Test
	void testDeleteAccount() {
		
		when(accountrepo.findByUrlAndMaster("https://googledrive.com", master)).thenReturn(Optional.ofNullable(account));
		when((accountrepo.findByUrlAndMaster("https://epam.com",master))).thenReturn(Optional.ofNullable(null));
		assertTrue(accountservice.deleteAccount("https://googledrive.com"));
		assertThrows(AccountNotFoundException.class,()->accountservice.deleteAccount("https://epam.com"));
	}
	@Test
	void testUpdateAccountPassword() {	
		
		when(accountrepo.findByUrlAndMaster("https://googledrive.com", master)).thenReturn(Optional.ofNullable(account));
		when((accountrepo.findByUrlAndMaster("https://epam.com",master))).thenReturn(Optional.ofNullable(account2));
		assertTrue(accountservice.updateAccountPassword("https://googledrive.com","King@1234"));
		assertThrows(AccountNotFoundException.class,()->accountservice.updateAccountPassword("https://epam.com","King@1234"));
	}
	@Test
	void testUpdateAccountUsername() {
		

		when(accountrepo.findByUrlAndMaster("https://googledrive.com", master)).thenReturn(Optional.ofNullable(account));
		when((accountrepo.findByUrlAndMaster("https://epam.com",master))).thenReturn(Optional.ofNullable(account2));
		assertTrue(accountservice.updateAccountUsername("https://googledrive.com","King"));
		Exception exception=assertThrows(AccountNotFoundException.class,()->accountservice.updateAccountUsername("https://epam.com","King"));
		String message=exception.getMessage();
		assertEquals("account not exists",message);
	}

	
	@Test
	void testReadPassword() {
		
		when((accountrepo.findByUrlAndMaster("https://googledrive.com",master))).thenReturn(Optional.ofNullable(account));
		when((accountrepo.findByUrlAndMaster("https://epam.com",master))).thenReturn(Optional.ofNullable(account2));
		assertEquals("Pavi@1234",accountservice.readPassword("https://googledrive.com"));
		assertThrows(AccountNotFoundException.class,()->accountservice.readPassword("https://epam.com"));
	}
	
	@Test
	void testCreateAccount() {
		AccountDto account=new AccountDto();
		account.setGroupname("google");
		account.setUrl("https://googledrive.com");
		account.setPassword("Pavi@1234");
		account.setUsername("praveen");
		AccountDto account2=new AccountDto();
		account2.setUrl("https://epam.com");
		account2.setGroupname("google");
		account2.setUsername("praveen");
		account2.setPassword("Pavi@1234");
		when((accountrepo.findByUrlAndMasterAndGroupname("https://epam.com", master,"google")))
		.thenReturn(Optional.ofNullable(null));
		when((accountrepo.findByUrlAndMasterAndGroupname("https://googledrive.com", master,"google"))).thenReturn(Optional.ofNullable(this.account));
		assertTrue(accountservice.createAccount(account2));
		assertThrows(AccountDuplicationException.class,()->accountservice.createAccount(account));
		
	}
	@Test
	void testViewAllAccounts() {
		when(accountrepo.findAll()).thenReturn(null);
		assertEquals(null,accountservice.viewAllAccounts());
		
	}

}
