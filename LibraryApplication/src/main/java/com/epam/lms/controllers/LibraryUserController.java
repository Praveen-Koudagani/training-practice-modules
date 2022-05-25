package com.epam.lms.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.lms.dto.UserDto;
import com.epam.lms.feignclient.BookClient;
import com.epam.lms.feignclient.UserClient;
import com.epam.lms.services.LibraryService;

@RestController
@RequestMapping("/library/users")
public class LibraryUserController {

	@Autowired
	RestTemplate template;
	@Autowired
	LibraryService libraryservice;

	@Value("http://localhost:8082/users")
	private String usersUrl;
	@Value("http://localhost:8081/books")
	private String booksUrl;
	
	@Autowired
	BookClient bookClient;
	@Autowired
	UserClient userClient;
	
	/*
	 * protected <T> T mapFromJson(String json, Class<T> targetClass) throws
	 * IOException {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); return
	 * objectMapper.readValue(json, targetClass); }
	 */

	@GetMapping
	public ResponseEntity<String> getListOfUsers() {
		return userClient.getListOfUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable String id) {

		ResponseEntity<?> responseFromUsers=userClient.getUserByID(id);

		List<Integer> bookIds=libraryservice.getBookIDsByUsername(id);
		List<String> books=new ArrayList<>();
		bookIds.stream().forEach(i->books.add(bookClient.getBookByID(i).getBody()));

		Map<String,Object> responsedata=new HashMap<>();
		responsedata.put(id,books);
		responsedata.put("userinfo", responseFromUsers.getBody());
		
		return new ResponseEntity<>(responsedata,HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody UserDto user) {
		
		return userClient.addUser(user);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		
		libraryservice.deleteByUsername(id);
		return userClient.deleteUser(id);
	}
	
}
