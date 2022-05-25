package com.epam.lms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.lms.dto.BookDto;
import com.epam.lms.feignclient.BookClient;
import com.epam.lms.services.LibraryService;



@RestController
@RequestMapping("/library/books")
public class LibraryBookController {
	@Autowired
	RestTemplate template;
	@Autowired
	LibraryService libraryservice;

	@Value("http://localhost:8081/books")
	private String booksUrl;
	
	@Autowired
	BookClient bookClient;

	@GetMapping
	public ResponseEntity<String> getListOfBooks() {

		//return template.exchange(booksUrl, HttpMethod.GET, null, String.class);
		return bookClient.getListOfBooks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getBookByID(@PathVariable int id) {

		/*
		 * String url=booksUrl.concat("/"+id); return template.exchange(url,
		 * HttpMethod.GET, null, String.class);
		 */
		return bookClient.getBookByID(id);
	}
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookDto book) {
		/*
		 * String url=booksUrl; HttpEntity<BookDto> book2=new HttpEntity<>(book); return
		 * template.exchange(url, HttpMethod.POST,book2, String.class);
		 */
		return bookClient.addBook(book);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		/*
		 * String url=booksUrl.concat("/"+id); libraryservice.deleteBookId(id); return
		 * template.exchange(url, HttpMethod.DELETE,null, String.class);
		 */
		libraryservice.deleteBookId(id);
		return bookClient.deleteBook(id);
	}
	
}
