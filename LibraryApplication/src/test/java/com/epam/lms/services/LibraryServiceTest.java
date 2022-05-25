package com.epam.lms.services;

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

import com.epam.lms.entities.Library;
import com.epam.lms.exceptions.BookAlreadyIssuedException;
import com.epam.lms.exceptions.BookLimitExceededException;
import com.epam.lms.exceptions.NotFoundException;
import com.epam.lms.repository.LibraryRepository;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

	@InjectMocks
	LibraryService libraryservice;
	@Mock
	LibraryRepository libraryrepo;
	Library library;

	@BeforeEach
	public void setUp() {
		library = new Library();
		library.setId(1);
		library.setBookId(1);
		library.setUsername("pavi");
	}

	
	@Test
	void testAdd() {
		Library library2 = new Library();
		library2.setBookId(2);
		library2.setUsername("ram");
		List<Library> list=new ArrayList<>();
		list.add(library2);
		list.add(this.library);
		when((libraryrepo.findByBookId(1))).thenReturn(Optional.ofNullable(this.library));
		when((libraryrepo.findByBookId(2))).thenReturn(Optional.ofNullable(null));
		when((libraryrepo.findByUsername("pavi"))).thenReturn(list);
		when((libraryrepo.findByUsername("ram"))).thenReturn(list);
		assertEquals("successfully issued", libraryservice.add(library2));
		assertThrows(BookAlreadyIssuedException.class, () -> libraryservice.add(library));
	}
	@Test
	void testAdd2() {
		Library library2 = new Library();
		library2.setBookId(2);
		library2.setUsername("ram");
		List<Library> list=new ArrayList<>();
		list.add(library2);
		list.add(this.library);
		list.add(new Library());
		when((libraryrepo.findByBookId(1))).thenReturn(Optional.ofNullable(null));
		when((libraryrepo.findByUsername("pavi"))).thenReturn(list);
		assertThrows(BookLimitExceededException.class, () -> libraryservice.add(this.library));
	}


	@Test
	void testDelete() {
		Library library2 = new Library();
		library2.setBookId(2);
		library2.setUsername("ram");
		when((libraryrepo.findByBookId(1))).thenReturn(Optional.ofNullable(this.library));
		when((libraryrepo.findByBookId(2))).thenReturn(Optional.ofNullable(null));
	
		assertEquals("successfully deleted", libraryservice.delete(library));
		assertThrows(NotFoundException.class, () -> libraryservice.delete(library2));
	}

	@Test
	void testGetBookIDsByUsername() {
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		when((libraryrepo.findBookIDsByUsername("pavi"))).thenReturn(list);
		assertEquals(list, libraryservice.getBookIDsByUsername("pavi"));
	}

	@Test
	void testDeleteByUsername() {
		Library library2 = new Library();
		library2.setBookId(2);
		library2.setUsername("pavi");
		when((libraryrepo.deleteBookIDsByUsername("pavi"))).thenReturn(2);
		when((libraryrepo.deleteBookIDsByUsername("ram"))).thenReturn(0);
	
		assertEquals("Successfully deleted", libraryservice.deleteByUsername("pavi"));
		assertThrows(NotFoundException.class, () -> libraryservice.deleteByUsername("ram"));
	}

	@Test
	void testDeleteBookId() {
		Library library2 = new Library();
		library2.setBookId(2);
		library2.setUsername("ram");
		when((libraryrepo.findByBookId(1))).thenReturn(Optional.ofNullable(this.library));
		when((libraryrepo.findByBookId(2))).thenReturn(Optional.ofNullable(null));
	
		assertEquals("successfully deleted", libraryservice.deleteBookId(1));
		assertThrows(NotFoundException.class, () -> libraryservice.deleteBookId(2));
	}

}
