package com.epam.book.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.book.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{
	public Optional<Book> findByName(String name);
	
}
