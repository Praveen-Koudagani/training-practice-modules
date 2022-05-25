package com.epam.lms.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.lms.dto.UserDto;

@FeignClient(name="USER-APPLICATION")
public interface UserClient {
	@GetMapping("/users")
	public ResponseEntity<String> getListOfUsers();
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable String id);
	@PostMapping("/users")
	public ResponseEntity<String> addUser(@RequestBody UserDto user);
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id);
}
