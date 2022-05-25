package com.epam.lms.controllers;

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

import com.epam.lms.entities.User;
import com.epam.lms.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userservice;

	@PostMapping()
	public ResponseEntity<Map<String, String>> createUser(@RequestBody User user) {
		
		Map<String, String> map = new HashMap<>();
		String status = userservice.addUser(user);
		
		map.put("message", status);

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String id) {
		Map<String, String> map = new HashMap<>();
		String status = userservice.deleteUser(id);
		map.put("message", status);

		return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {

		User user = userservice.getUserByID(id);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List> getUserList() {

		List<User> users = userservice.listAllUsers();

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/{username}")
	public ResponseEntity<Map<String, String>> updateUser(@RequestBody User user,@PathVariable String username) {
		Map<String, String> map = new HashMap<>();
		String status = userservice.updateUser(user,username);
		map.put("message", status);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
