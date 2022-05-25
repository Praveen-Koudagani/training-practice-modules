package com.epam.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.lms.entities.User;
import com.epam.lms.exceptions.UserDuplicationException;
import com.epam.lms.exceptions.UserNotFoundException;
import com.epam.lms.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

	public List<User> listAllUsers() {
		return (List<User>) userRepo.findAll();
	}

	public User getUserByID(String id) {
		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	public String addUser(User user) {
		String status = "added successfully";
		Optional<User> user2 = userRepo.findByUsername(user.getUsername());
		if (user2.isEmpty()) {
			userRepo.save(user);
		} else {
			throw new UserDuplicationException("User alreay exists");
		}

		return status;
	}

	public String deleteUser(String id) {
		userRepo.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
		String status = "delete success";
			userRepo.deleteById(id);
		return status;
	}

	public String updateUser(User user, String username) {

		User user2 = userRepo.findById(username).orElseThrow(() -> new UserNotFoundException("User not found"));
		String status = "update success";
		user2.setEmail(user.getEmail());
		userRepo.save(user2);
		return status;
	}
}
