package com.epam.lms.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

import com.epam.lms.entities.User;
import com.epam.lms.services.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserService userservice;
	User user;

	@Autowired
	private ObjectMapper mapper;

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	@BeforeEach
	public void setUp() {
		user = new User();
		user.setUsername("pavi");
		user.setEmail("pavi@gmail.com");
		
	}
	
	@Test
	void testCreateUser() throws JsonProcessingException, Exception {
		when(userservice.addUser(any())).thenReturn("success");
		MvcResult result = mockMvc
				.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(this.user)))
				.andExpect(status().isCreated()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("success", data.get("message"));

	}

	@Test
	void testDeleteUser() throws Exception {
		when(userservice.deleteUser("pavi")).thenReturn("success");
		MvcResult result = mockMvc
				.perform(delete("/users/pavi").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("success", data.get("message"));
	}

	@Test
	void testGetUser() throws Exception {
		when(userservice.getUserByID("pavi")).thenReturn(this.user);
		MvcResult result = mockMvc
				.perform(get("/users/pavi").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String data= result.getResponse().getContentAsString();
		assertEquals(mapper.writeValueAsString(this.user),data);
	}

	@Test
	void testGetUserList() throws Exception {
		List<User> list=new ArrayList<>();
		list.add(this.user);
		when(userservice.listAllUsers()).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String data= result.getResponse().getContentAsString();
		assertEquals(list.size(),mapFromJson(data,ArrayList.class).size());
	}

	@Test
	void testUpdateUser() throws JsonProcessingException, Exception {
		when(userservice.updateUser(any(),any())).thenReturn("success");
		MvcResult result = mockMvc
				.perform(put("/users/pavi").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(this.user)))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("success", data.get("message"));
	}

}
