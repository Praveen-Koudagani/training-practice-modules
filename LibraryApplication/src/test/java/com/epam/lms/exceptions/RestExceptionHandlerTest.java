package com.epam.lms.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.epam.lms.entities.Library;
import com.epam.lms.feignclient.BookClient;
import com.epam.lms.feignclient.UserClient;
import com.epam.lms.services.LibraryService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class RestExceptionHandlerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	LibraryService libraryService;
	Library library;
	
	@MockBean
	BookClient bookClient;
	@MockBean 
	UserClient userClient;

	protected <T> T mapFromJson(String json, Class<T> clazz) throws
	 JsonParseException, JsonMappingException, IOException {
	
	 ObjectMapper objectMapper = new ObjectMapper(); return
	 objectMapper.readValue(json, clazz); }
	
	@Test
	void testHandlesBookAlreadyIssuedException() throws Exception {
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		ResponseEntity response2=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.getBookByID(1)).thenReturn(response);
		when(userClient.getUserByID("pavi")).thenReturn(response2);
		when(libraryService.add(any())).thenThrow(new BookAlreadyIssuedException("not available"));
		MvcResult result = mockMvc.perform(post("/library/users/pavi/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("not available", data.get("error"));	
	}

	@Test
	void testHandlesBookLimitExceededException() throws Exception {
	
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		ResponseEntity response2=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.getBookByID(1)).thenReturn(response);
		when(userClient.getUserByID("pavi")).thenReturn(response2);
		when(libraryService.add(any())).thenThrow(new BookLimitExceededException("limit reached"));
		MvcResult result = mockMvc.perform(post("/library/users/pavi/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("limit reached", data.get("error"));	
	}

	/*
	 * @Test void testHandlesHttpClientErrorException() throws Exception {
	 * 
	 * }
	 */
	
}
