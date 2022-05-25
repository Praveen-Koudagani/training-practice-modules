package com.epam.lms.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.epam.lms.dto.UserDto;
import com.epam.lms.entities.Library;
import com.epam.lms.exceptions.NotFoundException;
import com.epam.lms.feignclient.BookClient;
import com.epam.lms.feignclient.UserClient;
import com.epam.lms.services.LibraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class LibraryUserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@MockBean
	LibraryService libraryService;
	Library library;
	
	@MockBean
	BookClient bookClient;
	@MockBean 
	UserClient userClient;

	@Autowired
	private ObjectMapper mapper;

	/*
	 * protected <T> T mapFromJson(String json, Class<T> clazz) throws
	 * JsonParseException, JsonMappingException, IOException {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); return
	 * objectMapper.readValue(json, clazz); }
	 */

	@Test
	void testGetListOfUsers() throws Exception {
		
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(userClient.getListOfUsers()).thenReturn(response);
		mockMvc
				.perform(get("/library/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testGetUserByID() throws Exception {
		
		List<Integer> list=new ArrayList<>();
		list.add(1);
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		ResponseEntity response2=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.getBookByID(1)).thenReturn(response);
		when(userClient.getUserByID("pavi")).thenReturn(response2);
		
		when(libraryService.getBookIDsByUsername("pavi")).thenReturn(list);
		mockMvc
				.perform(get("/library/users/pavi").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testAddUser() throws JsonProcessingException, Exception {
		UserDto user=new UserDto();
		user.setUsername("pavi");
		user.setEmail("pavi@gmail.com");
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(userClient.addUser(user)).thenReturn(response);
		mockMvc
				.perform(post("/library/users").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testDeleteUser() throws Exception {
		
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(userClient.deleteUser("pavi")).thenReturn(response);
		mockMvc
				.perform(delete("/library/users/pavi").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}
	@Test
	void testDeleteUser2() throws Exception {
	
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(userClient.deleteUser("pavi")).thenReturn(response);
		when(libraryService.deleteByUsername("pavi")).thenThrow(new NotFoundException("not found any records"));
		mockMvc
				.perform(delete("/library/users/pavi").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
	}

}
