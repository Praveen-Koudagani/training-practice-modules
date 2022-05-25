package com.epam.book.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.book.entities.Book;
import com.epam.book.exceptions.BookDuplicationException;
import com.epam.book.exceptions.BookNotFoundException;
import com.epam.book.repo.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	BookService bookservice;
	@Mock
	BookRepository bookrepo;
	Book book;

	@BeforeEach
	public void setUp() {
		book = new Book();
		book.setAuthor("pavi");
		book.setName("Red line");
		book.setPublisher("great prints");
	}

	@Test
	void testListAllBooks() {
		List<Book> books=new ArrayList<>();
		books.add(this.book);
		when((bookrepo.findAll())).thenReturn(books);
		assertEquals(1, bookservice.listAllBooks().size());
	}

	@Test
	void testGetBookByID() {
		Book book2 = new Book();
		book2.setName("King Of forest");
		when((bookrepo.findById(1))).thenReturn(Optional.ofNullable(book2));
		when((bookrepo.findById(2))).thenReturn(Optional.ofNullable(null));
		assertEquals(book2, bookservice.getBookByID(1));
		assertThrows(BookNotFoundException.class, () -> bookservice.getBookByID(2));
	}

	@Test
	void testAddBook() {
		Book book2 = new Book();
		book2.setName("King Of forest");
		when((bookrepo.findByName("King Of forest"))).thenReturn(Optional.ofNullable(book2));
		when((bookrepo.findByName("Red line"))).thenReturn(Optional.ofNullable(null));
		assertEquals("successfully added", bookservice.addBook(this.book));
		assertThrows(BookDuplicationException.class, () -> bookservice.addBook(book2));
	}

	@Test
	void testDeleteBook() {
		Book book2 = new Book();
		book2.setName("King Of forest");
		when((bookrepo.findById(1))).thenReturn(Optional.ofNullable(book2));
		when((bookrepo.findById(2))).thenReturn(Optional.ofNullable(null));
		assertEquals("successfully deleted", bookservice.deleteBook(1));
		assertThrows(BookNotFoundException.class, () -> bookservice.deleteBook(2));
	}

	@Test
	void testUpdateBook() {
		Book book2 = new Book();
		book2.setName("King Of forest");
		when((bookrepo.findById(1))).thenReturn(Optional.ofNullable(book2));
		when((bookrepo.findById(2))).thenReturn(Optional.ofNullable(null));
		assertEquals("successfully updated", bookservice.updateBook(book,1));
		assertThrows(BookNotFoundException.class, () -> bookservice.updateBook(book2,2));
	}

}
