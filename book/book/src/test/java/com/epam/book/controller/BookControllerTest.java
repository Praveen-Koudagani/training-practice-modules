package com.epam.book.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.epam.book.entities.Book;
import com.epam.book.services.BookService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class BookControllerTest {

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
	void testCreateBook() throws Exception {
		when(bookservice.addBook(any())).thenReturn("success");
		MvcResult result = mockMvc
				.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(this.book)))
				.andExpect(status().isCreated()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("success", data.get("message"));

	}

	@Test
	void testDeleteBook() throws JsonProcessingException, Exception {
		when(bookservice.deleteBook(1)).thenReturn("success");
		MvcResult result = mockMvc
				.perform(delete("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("success", data.get("message"));
	}

	@Test
	void testGetBook() throws Exception {
		when(bookservice.getBookByID(1)).thenReturn(this.book);
		MvcResult result = mockMvc
				.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String data= result.getResponse().getContentAsString();
		assertEquals(mapper.writeValueAsString(this.book),data);
	}

	@Test
	void testGetBookList() throws Exception {
		List<Book> list=new ArrayList<>();
		list.add(this.book);
		when(bookservice.listAllBooks()).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String data= result.getResponse().getContentAsString();
		assertEquals(list.size(),mapFromJson(data,ArrayList.class).size());
	}

	@Test
	void testUpdateBook() throws JsonProcessingException, Exception {
		when(bookservice.updateBook(any(),anyInt())).thenReturn("success");
		MvcResult result = mockMvc
				.perform(put("/books/1").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(this.book)))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, Object> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("success", data.get("message"));
	}

}
