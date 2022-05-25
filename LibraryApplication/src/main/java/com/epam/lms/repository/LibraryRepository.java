package com.epam.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lms.entities.Library;


public interface LibraryRepository extends CrudRepository<Library,Integer>{
	public List<Library> findByUsername(String username);

	public Optional<Library> findByBookId(int id);
	
	@Query("SELECT l.bookId FROM Library l WHERE l.username = ?1")
	public List<Integer> findBookIDsByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Library l WHERE l.username = ?1")
	public int deleteBookIDsByUsername(String username);
}
