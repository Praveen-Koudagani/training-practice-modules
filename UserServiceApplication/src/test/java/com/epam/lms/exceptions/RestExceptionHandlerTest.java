package com.epam.lms.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import java.io.IOException;
import java.util.HashMap;

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
class RestExceptionHandlerTest {
	
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
		user.setEmail("Pavi@gamil.com");
		
	}
	

	@Test
	void testHandlesUserNotFoundExceptionUserNotFoundException() throws Exception {
		when(userservice.deleteUser("pavi")).thenThrow(new UserNotFoundException("user not found"));
		MvcResult result = mockMvc
				.perform(delete("/users/pavi")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isNotFound()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("user not found", data.get("error"));	
	}

	@Test
	void testHandlesUserNotFoundExceptionUserDuplicationException() throws JsonProcessingException, Exception {
		when(userservice.addUser(any())).thenThrow(new UserDuplicationException("user duplication"));
		MvcResult result = mockMvc
				.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(this.user)))
				.andExpect(status().isNotFound()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("user duplication", data.get("error"));
	}

}
