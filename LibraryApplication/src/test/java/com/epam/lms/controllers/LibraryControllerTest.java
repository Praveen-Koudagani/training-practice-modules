package com.epam.lms.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.epam.lms.entities.Library;
import com.epam.lms.feignclient.BookClient;
import com.epam.lms.feignclient.UserClient;
import com.epam.lms.services.LibraryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class LibraryControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	LibraryService libraryService;
	Library library;
	
	@MockBean
	BookClient bookClient;
	@MockBean 
	UserClient userClient;
	
	@Test
	void testAddDetails() throws Exception {
		
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		ResponseEntity response2=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.getBookByID(1)).thenReturn(response);
		when(userClient.getUserByID("pavi")).thenReturn(response2);
		when(libraryService.add(library)).thenReturn("success");
		mockMvc.perform(post("/library/users/pavi/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
	}

	@Test
	void testDeleteDetails() throws Exception {
		
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.getBookByID(1)).thenReturn(response);
		when(libraryService.delete(library)).thenReturn("success");
		mockMvc.perform(delete("/library/users/pavi/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()).andReturn();
	}

}
