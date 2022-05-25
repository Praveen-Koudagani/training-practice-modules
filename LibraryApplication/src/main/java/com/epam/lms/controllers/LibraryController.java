package com.epam.lms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.lms.entities.Library;
import com.epam.lms.feignclient.BookClient;
import com.epam.lms.feignclient.UserClient;
import com.epam.lms.services.LibraryService;


@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	RestTemplate template;
	@Autowired
	LibraryService libraryservice;
	/*
	 * @Value("http://localhost:8081/books") private String booksUrl;
	 * 
	 * @Value("http://localhost:8082/users") private String usersUrl;
	 */
	
	@Autowired
	BookClient bookClient;
	@Autowired
	UserClient userClient;
	
	@PostMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<String> addDetails(@PathVariable String username,@PathVariable int bookId) {
	
		/*
		 * template.exchange(url, HttpMethod.GET, null, Map.class);
		 * template.exchange(url2, HttpMethod.GET, null, Map.class);
		 */
		bookClient.getBookByID(bookId);
		userClient.getUserByID(username);
		Library library=new Library();
		library.setUsername(username);
		library.setBookId(bookId); 
		return new ResponseEntity<>(libraryservice.add(library),HttpStatus.CREATED);

	}
	@DeleteMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<String> deleteDetails(@PathVariable String username,@PathVariable int bookId) {
		//String url=booksUrl.concat("/"+bookId);
		bookClient.getBookByID(bookId);
		//template.exchange(url, HttpMethod.GET, null, Map.class);
		Library library=new Library();
		library.setUsername(username);
		library.setBookId(bookId);
		return new ResponseEntity<>(libraryservice.delete(library),HttpStatus.NO_CONTENT);
	}
	
}
