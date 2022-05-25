package com.epam.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.book.entities.Book;
import com.epam.book.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookService bookService;

	@PostMapping()
	public ResponseEntity<Map<String, String>> createBook(@RequestBody Book book) {
		Map<String, String> map = new HashMap<>();
		String status = bookService.addBook(book);
		map.put("message", status);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteBook(@PathVariable int id) {
		Map<String, String> map = new HashMap<>();
		String status = bookService.deleteBook(id);
		map.put("message", status);
		return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id) {
		Book book = bookService.getBookByID(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List> getBookList() {
		List<Book> books = bookService.listAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, String>> updateBook(@RequestBody Book book,@PathVariable int id) {
		Map<String, String> map = new HashMap<>();
		String status = bookService.updateBook(book,id);
		map.put("message", status);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
