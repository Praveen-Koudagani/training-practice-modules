package com.epam.lms.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.lms.dto.BookDto;

@FeignClient(name="BOOK-APPLICATION")
public interface BookClient {
	@GetMapping("/books")
	public ResponseEntity<String> getListOfBooks();
	@GetMapping("/books/{id}")
	public ResponseEntity<String> getBookByID(@PathVariable int id);
	@PostMapping("/books")
	public ResponseEntity<String> addBook(@RequestBody BookDto book);
	@DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id);
}
