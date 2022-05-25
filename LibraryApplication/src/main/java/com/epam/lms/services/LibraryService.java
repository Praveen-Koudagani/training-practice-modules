package com.epam.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.lms.entities.Library;
import com.epam.lms.exceptions.BookAlreadyIssuedException;
import com.epam.lms.exceptions.BookLimitExceededException;
import com.epam.lms.exceptions.NotFoundException;
import com.epam.lms.repository.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	LibraryRepository libraryRepo;

	public String add(Library library) {

		String status = "successfully issued";
		Optional<Library> library2=libraryRepo.findByBookId(library.getBookId());
				//.orElseThrow(() -> new BookAlreadyIssuedException("Book not available"));
		List<Library> librarylist = libraryRepo.findByUsername(library.getUsername());
		if(library2.isPresent()) {
		throw new BookAlreadyIssuedException("Book not available");
		}
		if (librarylist.size() <= 2) {
			libraryRepo.save(library);
		} else {
			throw new BookLimitExceededException(" limit Exceeded for user");
		}

		return status;
	}

	public String delete(Library library) {
		Library library2 = libraryRepo.findByBookId(library.getBookId())
				.orElseThrow(() -> new NotFoundException("book is not issued"));
		String status = "successfully deleted";

		libraryRepo.deleteById(library2.getId());
		return status;
	}

	public List<Integer> getBookIDsByUsername(String username) {
		return libraryRepo.findBookIDsByUsername(username);
	}

	public String deleteByUsername(String username) {
		String status = "Successfully deleted";
		int count = libraryRepo.deleteBookIDsByUsername(username);
		if (count <= 0) {
			throw new NotFoundException("not found any records");
		}
		return status;
	}

	public String deleteBookId(int id) {
		Library library = libraryRepo.findByBookId(id).orElseThrow(() -> new NotFoundException("book is not issued"));
		String status = "successfully deleted";

		libraryRepo.deleteById(library.getId());

		return status;
	}

}
