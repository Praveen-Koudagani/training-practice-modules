package com.epam.crud_operations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dao.AccountRepository;
import com.epam.entities.Account;
import com.epam.entities.Master;
@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

	@InjectMocks
	GroupService groupservice;
	@Mock
	AccountRepository accountrepo;
	@Mock
	Master master;
	
	List<Account> accounts=new ArrayList<>();
	List<Account> accounts2=new ArrayList<>();
	@BeforeEach
	public void setUp() {
		Account account=new Account();
		account.setGroupname("google");
		account.setPassword("Pavi@1234");
		account.setUrl("https://googledrive.com");
		accounts.add(account);
	}
	
	@Test
	void testViewAccounts() {
		when(accountrepo.findByGroupnameAndMaster(any(),any())).thenReturn(accounts);
		assertEquals(accounts,groupservice.viewAccounts("pavi"));
	}

	@Test
	void testUpdateGroupName() {
		when(accountrepo.findByGroupnameAndMaster("google",master)).thenReturn(accounts);
		when(accountrepo.findByGroupnameAndMaster("googlellc",master)).thenReturn(accounts2);
		assertTrue(groupservice.updateGroupName("google","googleapps"));
		assertFalse(groupservice.updateGroupName("googlellc","googleapps"));
	}

	@Test
	void testDeleteByGroupAccount() {
		when(accountrepo.findByGroupnameAndMaster("google",master)).thenReturn(accounts);
		when(accountrepo.findByGroupnameAndMaster("googlellc",master)).thenReturn(accounts2);
		assertTrue(groupservice.deleteByGroupAccount("google"));
		assertFalse(groupservice.deleteByGroupAccount("googlellc"));
	}

}
