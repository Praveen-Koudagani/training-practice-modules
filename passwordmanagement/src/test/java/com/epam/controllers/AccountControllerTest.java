package com.epam.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.swing.text.View;

import com.epam.crud_operations.AccountService;
import com.epam.crud_operations.GroupService;
import com.epam.dao.AccountRepository;
import com.epam.dto.AccountDto;
import com.epam.entities.Account;
@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = {AccountController.class})
class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	AccountService accountservice;
	@MockBean
	GroupService groupservice;
	
	@Test
	void testAddAccount() throws Exception {
		AccountDto account=new AccountDto();
		account.setGroupname("epam");
		account.setUrl("https://epam.com");
		account.setPassword("pavi@1234");
		account.setUsername("Praveen");
		when(accountservice.createAccount(account)).thenReturn(true);
		mockMvc.perform(get("/createAccount")).andExpect(status().isOk()).andExpect(view().name("createAccount"));
	}

	@Test
	void testReadPassword() throws Exception{
		
		mockMvc.perform(get("/readPassword")).andExpect(status().isOk()).andExpect(view().name("readPassword"));
	}

	@Test
	void testDeleteGroup() throws Exception{
		mockMvc.perform(get("/DeleteGroup")).andExpect(status().isOk()).andExpect(view().name("DeleteGroup"));
	}

	@Test
	void testDeleteAccount() throws Exception {
		mockMvc.perform(get("/DeleteAccount")).andExpect(status().isOk()).andExpect(view().name("DeleteAccount"));
	}

	@Test
	void testDelete() throws Exception{
		mockMvc.perform(get("/Delete")).andExpect(status().isOk()).andExpect(view().name("DeleteAccount"));
	}

	@Test
	void testViewGroup() throws Exception{
		mockMvc.perform(get("/viewGroup")).andExpect(status().isOk()).andExpect(view().name("groupInput"));
	}

	@Test
	void testUpdateGroup() throws Exception{
		mockMvc.perform(get("/updateGroup")).andExpect(status().isOk()).andExpect(view().name("updateGroupInput"));
	}

	@Test
	void testUpdateUserName() throws Exception{
		mockMvc.perform(get("/updateUserName")).andExpect(status().isOk()).andExpect(view().name("updateUsername"));
	}

	@Test
	void testUpdatePassword() throws Exception{
		mockMvc.perform(get("/updatePassword")).andExpect(status().isOk()).andExpect(view().name("updateAccountPassword"));
	}

	@Test
	void testAddAccountAccount() throws Exception{
		AccountDto account=new AccountDto();
		account.setGroupname("epam");
		account.setUrl("https://epam.com");
		account.setPassword("pavi@1234");
		account.setUsername("praveen");
		when(accountservice.createAccount(any())).thenReturn(true);
		mockMvc.perform(post("/createAccount2?groupname=epam&url=https://epam.com&password="
				+ "pavi@1234&username=praveen").contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk()).andExpect(view().name("success"));
		
	}
	@Test
	void testAddAccountAccount2() throws Exception{
		when(accountservice.createAccount(null)).thenReturn(false);
		mockMvc.perform(post("/createAccount2").contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk()).andExpect(view().name("success"));
		
	}
	@Test
	void testAddAccountAccount3() throws Exception{
		AccountDto account2=new AccountDto();
		account2.setUrl("www.epam.com");
		account2.setGroupname("epam2");
		account2.setUsername("ravi2");
		account2.setPassword("ravi@1234");
		when(accountservice.createAccount(any())).thenThrow(new RuntimeException());
		
		mockMvc.perform(post("/createAccount2?groupname=epam2&url=www.epam.com&password="
		+ "ravi@1234&username=ravi2"))
		.andExpect(status().isOk()).andExpect(view().name("error"));
	}

	@Test
	void testReadPasswordString() throws Exception {
		when(accountservice.readPassword("https://epam.com")).thenReturn("Pavi@1234");
		when(accountservice.readPassword("https://kb.epam.com")).thenReturn("");
		mockMvc.perform(post("/readPassword2?url=https://epam.com").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
		mockMvc.perform(post("/readPassword2?url=https://kb.epam.com").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
	}

	@Test
	void testViewAll() throws Exception {
		when(accountservice.viewAllAccounts()).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/viewAll2").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("viewAll"))
		;
	}

	@Test
	void testViewGroupString() throws Exception {
		List<Account> accounts=new ArrayList<>();
		accounts.add(new Account());
		when(groupservice.viewAccounts("epam")).thenReturn(accounts);
		when(groupservice.viewAccounts(null)).thenThrow(new RuntimeException());
		mockMvc.perform(post("/viewGroup2?group=epam").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("viewGroup"))
		;
		mockMvc.perform(post("/viewGroup2?").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("error"))
		;
		
	}

	@Test
	void testDeleteGroupString() throws Exception {
		when(groupservice.deleteByGroupAccount("google")).thenReturn(true);
		when(groupservice.deleteByGroupAccount("epam")).thenReturn(false);
		mockMvc.perform(post("/DeleteGroup2?group=google").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
		mockMvc.perform(post("/DeleteGroup2?group=epam").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
	}

	@Test
	void testDeleteAccountString() throws Exception {
		when(accountservice.deleteAccount("https://googledrive.com")).thenReturn(true);
		when(accountservice.deleteAccount("https://google.com")).thenReturn(false);
		when(accountservice.deleteAccount(null)).thenThrow(new RuntimeException());
		mockMvc.perform(post("/DeleteAccount2?url=https://google.com").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
		mockMvc.perform(post("/DeleteAccount2?url=https://googledrive.com").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
		mockMvc.perform(post("/DeleteAccount2?").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("error"))
		;
	}

	@Test
	void testDeleteRow() throws Exception {
		when(accountservice.deleteAccount("https://googledrive.com")).thenReturn(true);
		when(accountservice.viewAllAccounts()).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/Delete2?url=https://googledrive.com").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("viewAll"))
		;
		
	}

	@Test
	void testDeleteRowGroup() throws Exception {
		when(accountservice.deleteAccount("https://google.com")).thenReturn(true);
		when(groupservice.viewAccounts("google")).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/DeleteFromGroup2?group=google&url=https://google.com").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("viewGroup"))
		;
		
	}

	@Test
	void testUpdateGroupStringString() throws Exception {
		when(groupservice.updateGroupName("epam","epamsystems")).thenReturn(true);
		mockMvc.perform(post("/updateGroup2?group=google&newgroup=epamsystems").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
		mockMvc.perform(post("/updateGroup2?group=epam&newgroup=epamsystems").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
	}

	@Test
	void testUpdateUserNameStringString() throws Exception {
		when(accountservice.updateAccountUsername("https://google.com","Ram")).thenReturn(true);
		mockMvc.perform(post("/updateUserName2?url=https://google.com&newname=Ram").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
		mockMvc.perform(post("/updateUserName2?url=https://googledrive.com&newname=Ram").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
	}

	@Test
	void testUpdatePasswordStringString() throws Exception {
		when(accountservice.updateAccountPassword("https://google.com","Ram@1234")).thenReturn(true);
		mockMvc.perform(post("/updatePassword2?url=https://google.com&newpassword=Ram@1234").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
		mockMvc.perform(post("/updatePassword2?url=https://googledrive.com&newpassword=Ram@1234").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("success"))
		;
	}

}
