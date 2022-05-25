package com.epam.restcontrollerstest;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.epam.crud_operations.AccountService;
import com.epam.crud_operations.GroupService;
import com.epam.dto.AccountDto;
import com.epam.dto.AuthenticationRequest;
import com.epam.entities.Account;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
 class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	AccountService accountservice;
	@MockBean
	GroupService groupservice;
	@Autowired
	private WebApplicationContext context;
	
	private String token;

	@Autowired
	private ObjectMapper mapper;


	@BeforeEach
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		String username = "praveen";
		String password = "Pavi@1234";

		AuthenticationRequest request = new AuthenticationRequest();
		request.setUsername(username);
		request.setPassword(password);

		MvcResult result = mockMvc.perform(post("/login").content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
	    token = response.substring(8, response.length() -2);
		System.out.println("token " + token);
	}

	/*
	 * protected String mapToJson(Object obj) throws JsonProcessingException {
	 * ObjectMapper objectMapper = new ObjectMapper(); return
	 * objectMapper.writeValueAsString(obj); }
	 */

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	void testAddAccountAccount() throws Exception {

		AccountDto account = new AccountDto();
		account.setGroupname("epam");
		account.setUrl("https://epam.com");
		account.setPassword("pavi@1234");
		account.setUsername("praveen");
		when(accountservice.createAccount(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/accounts/createAccount2?groupname=epam&url=https://epam.com&password="
						+ "pavi@1234&username=praveen").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("account saved successfully", data.get("message"));

	}

	@Test
	void testAddAccountAccount3() throws Exception {
		AccountDto account2 = new AccountDto();
		account2.setUrl("www.epam.com");
		account2.setGroupname("epam2");
		account2.setUsername("ravi2");
		account2.setPassword("ravi@1234");
		when(accountservice.createAccount(any())).thenReturn(false);

		MvcResult result = mockMvc.perform(post(
				"/accounts/createAccount2?groupname=epam2&url=www.epam.com&password=" + "ravi@1234&username=ravi2")
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("account already exists", data.get("message"));
	}

	@Test
	void testReadPasswordString() throws Exception {
		when(accountservice.readPassword("https://epam.com")).thenReturn("Pavi@1234");
		when(accountservice.readPassword("https://kb.epam.com")).thenReturn("not present");
		MvcResult result = mockMvc.perform(
				post("/accounts/readPassword2?url=https://epam.com").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("read successfully", data.get("message"));
		result = mockMvc.perform(post("/accounts/readPassword2?url=https://kb.epam.com")
				.header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andReturn();
		urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("account not exists", data.get("message"));
	}

	@Test
	void testViewAll() throws Exception {
		List<Account> accounts = new ArrayList<>();
		Account account = new Account();
		account.setUrl("https://google.com");
		account.setGroupname("google");
		account.setPassword("Pavi@1234");
		account.setUsername("pavi");
		accounts.add(account);
		when(accountservice.viewAllAccounts()).thenReturn(accounts);
		MvcResult result = mockMvc.perform(get("/accounts/viewAll2").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		Account[] accountlist = this.mapFromJson(urlres, Account[].class);
		assertTrue(accountlist.length == 1);
		assertEquals( "https://google.com",accountlist[0].getUrl());
	}

	@Test
	void testViewGroupString() throws Exception {
		List<Account> accounts = new ArrayList<>();
		Account account = new Account();
		account.setUrl("https://google.com");
		account.setGroupname("google");
		account.setPassword("Pavi@1234");
		account.setUsername("pavi");
		accounts.add(account);
		when(groupservice.viewAccounts("google")).thenReturn(accounts);
		when(groupservice.viewAccounts("epam")).thenReturn(new ArrayList<>());
		MvcResult result = mockMvc.perform(post("/accounts/viewGroup2/google").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		List<Account> accountlist = (List<Account>) data.get("accounts");
		assertTrue(accountlist.size() == 1);
		assertEquals("all accounts retrieved successfully", data.get("message"));
		mockMvc.perform(post("/accounts/viewGroup2/epam").header("Authorization", "Bearer " + token))
		.andExpect(status().isOk());

	}

	@Test
	void testDeleteGroupString() throws Exception {
		when(groupservice.deleteByGroupAccount("google")).thenReturn(true);
		when(groupservice.deleteByGroupAccount("epam")).thenReturn(false);
		MvcResult result = mockMvc
				.perform(delete("/accounts/DeleteGroup2/google").header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("deleted successfully", data.get("message"));
		result = mockMvc
				.perform(delete("/accounts/DeleteGroup2/epam")
						.header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
		urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("Group not exists", data.get("message"));

	}

	@Test
	void testDeleteAccountString() throws Exception {
		when(accountservice.deleteAccount("https://googledrive.com")).thenReturn(true);
		when(accountservice.deleteAccount("https://google.com")).thenReturn(false);

		MvcResult result = mockMvc.perform(delete("/accounts/DeleteAccount2?url=https://google.com")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("Account not exists", data.get("message"));
		result = mockMvc.perform(delete("/accounts/DeleteAccount2?url=https://googledrive.com")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andReturn();
		urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("deleted successfully", data.get("message"));
	}

	@Test
	void testUpdateGroupStringString() throws Exception {
		when(groupservice.updateGroupName("google", "googlellc")).thenReturn(true);
		when(groupservice.updateGroupName("epam", "EpamSystems")).thenReturn(false);
		MvcResult result = mockMvc.perform(
				put("/accounts/updateGroup2/google/googlellc")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("group updated  successfully", data.get("message"));
		result = mockMvc.perform(
				put("/accounts/updateGroup2/epam/EpamSystems")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
		urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("group not exists", data.get("message"));

	}

	@Test
	void testUpdateUserNameStringString() throws Exception {
		when(accountservice.updateAccountUsername("https://google.com", "Ram")).thenReturn(true);
		when(accountservice.updateAccountUsername("https://googledrive.com", "gopal")).thenReturn(false);
		MvcResult result = mockMvc.perform(put("/accounts/updateUserName2?url=https://google.com&newname=Ram")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("name updated  successfully", data.get("message"));
		mockMvc.perform(put("/accounts/updateUserName2?url=https://googledrive.com&newname=gopal")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());

	}

	@Test
	void testUpdatePasswordStringString() throws Exception {
		when(accountservice.updateAccountPassword("https://google.com", "Ram@1234")).thenReturn(true);
		when(accountservice.updateAccountPassword("https://googledrive.com", "Ram@1234")).thenReturn(false);

		MvcResult result = mockMvc.perform(put("/accounts/updatePassword2?url=https://google.com&newpassword=Ram@1234")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("password updated  successfully", data.get("message"));
		mockMvc.perform(put("/accounts/updatePassword2?url=https://googledrive.com&newpassword=Ram@1234")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());

	}

}