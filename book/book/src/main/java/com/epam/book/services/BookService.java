package com.epam.book.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.book.entities.Book;
import com.epam.book.exceptions.BookDuplicationException;
import com.epam.book.exceptions.BookNotFoundException;
import com.epam.book.repo.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepo;

	public List<Book> listAllBooks() {
		return (List<Book>) bookRepo.findAll();
	}

	public Book getBookByID(int id) {
		return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
	}

	public String addBook(Book book) {
		String status = "successfully added";
		Optional<Book> book2 = bookRepo.findByName(book.getName());
		if (book2.isEmpty()) {
			bookRepo.save(book);
		} else {
			throw new BookDuplicationException("Book alreay exists");
		}

		return status;
	}

	public String deleteBook(int id) {
		bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
		String status = "successfully deleted";

		bookRepo.deleteById(id);

		return status;
	}

	public String updateBook(Book book, int id) {

		Book book2 = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
		String status = "successfully updated";

		book2.setAuthor(book.getAuthor());
		book2.setPublisher(book.getPublisher());
		book2.setName(book.getName());
		bookRepo.save(book2);
		return status;
	}
}
