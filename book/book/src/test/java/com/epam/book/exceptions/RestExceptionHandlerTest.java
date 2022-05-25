package com.epam.book.exceptions;

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

import com.epam.book.entities.Book;
import com.epam.book.services.BookService;
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
	BookService bookservice;
	Book book;

	@Autowired
	private ObjectMapper mapper;

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	@BeforeEach
	public void setUp() {
		book = new Book();
		book.setAuthor("pavi");
		book.setName("Red line");
		book.setPublisher("great prints");
	}
	
	@Test
	void testHandlesBookNotFoundException() throws Exception {
		when(bookservice.deleteBook(1)).thenThrow(new BookNotFoundException("book not found"));
		MvcResult result = mockMvc
				.perform(delete("/books/1")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isNotFound()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("book not found", data.get("error"));	
	}

	@Test
	void testHandlesBookDuplicationException() throws Exception {
		when(bookservice.addBook(any())).thenThrow(new BookDuplicationException("book duplication"));
		MvcResult result = mockMvc
				.perform(post("/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(this.book)))
				.andExpect(status().isNotFound()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("book duplication", data.get("error"));	}

}
