package com.epam.lms.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
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

import com.epam.lms.dto.BookDto;
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
class LibraryBookControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	LibraryService libraryService;
	Library library;
	@MockBean
	RestTemplate template;

	@Autowired
	private ObjectMapper mapper;
	@Value("http://localhost:8081/books")
	private String booksUrl;

	/*
	 * protected <T> T mapFromJson(String json, Class<T> clazz) throws
	 * JsonParseException, JsonMappingException, IOException {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); return
	 * objectMapper.readValue(json, clazz); }
	 */
	@MockBean
	BookClient bookClient;
	@MockBean 
	UserClient userClient;
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@Test
	void testGetListOfBooks() throws JsonProcessingException, Exception {
		String url=booksUrl;
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.getListOfBooks()).thenReturn(response);
		mockMvc.perform(get("/library/books").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testGetBookByID() throws Exception {
		String url=booksUrl;
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.getBookByID(1)).thenReturn(response);
	 mockMvc.perform(get("/library/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testAddBook() throws JsonProcessingException, Exception {
		BookDto book=new BookDto();
		book.setId(1);
		book.setAuthor("pavi");
		book.setName("Red Line");
		book.setPublisher("Great Prints");
		String url=booksUrl;
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.addBook(book)).thenReturn(response);
		mockMvc.perform(post("/library/books").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(book)))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testDeleteBook() throws JsonProcessingException, Exception {
		String url=booksUrl+"1";
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.deleteBook(1)).thenReturn(response);
		mockMvc.perform(delete("/library/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}
	@Test
	void testDeleteBook2() throws JsonProcessingException, Exception {
		String url=booksUrl+"1";
		ResponseEntity<String> response=new ResponseEntity<>("success",HttpStatus.OK);
		when(bookClient.deleteBook(1)).thenReturn(response);
		when(libraryService.deleteBookId(1)).thenThrow(new NotFoundException("not issued"));
		mockMvc.perform(delete("/library/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
	}

}
