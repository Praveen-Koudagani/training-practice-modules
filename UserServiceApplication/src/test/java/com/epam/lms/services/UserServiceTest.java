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

import com.epam.lms.entities.User;
import com.epam.lms.exceptions.UserDuplicationException;
import com.epam.lms.exceptions.UserNotFoundException;
import com.epam.lms.repo.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	UserService userservice;
	@Mock
	UserRepository userrepo;
	User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setUsername("pavi");
		user.setEmail("pavi@gmail.com");
	}
	
	@Test
	void testListAllUsers() {
		List<User> books=new ArrayList<>();
		books.add(this.user);
		when((userrepo.findAll())).thenReturn(books);
		assertEquals(1, userservice.listAllUsers().size());
	}

	@Test
	void testGetUserByID() {
		User user2 = new User();
		user2.setUsername("ram");
		when((userrepo.findById("ram"))).thenReturn(Optional.ofNullable(user2));
		when((userrepo.findById("pavi"))).thenReturn(Optional.ofNullable(null));
		assertEquals(user2, userservice.getUserByID("ram"));
		assertThrows(UserNotFoundException.class, () -> userservice.getUserByID("pavi"));
	}

	@Test
	void testAddUser() {
		User user2 = new User();
		user2.setUsername("ram");
		when((userrepo.findByUsername("ram"))).thenReturn(Optional.ofNullable(user2));
		when((userrepo.findByUsername("pavi"))).thenReturn(Optional.ofNullable(null));
		assertEquals("added successfully", userservice.addUser(this.user));
		assertThrows(UserDuplicationException.class, () -> userservice.addUser(user2));
	}

	@Test
	void testDeleteUser() {
		User user2 = new User();
		user2.setUsername("ram");
		when((userrepo.findById("ram"))).thenReturn(Optional.ofNullable(user2));
		when((userrepo.findById("pavi"))).thenReturn(Optional.ofNullable(null));
		assertEquals("delete success", userservice.deleteUser("ram"));
		assertThrows(UserNotFoundException.class, () -> userservice.deleteUser("pavi"));
	}

	@Test
	void testUpdateUser() {
		User user2 = new User();
		user2.setUsername("ram");
		when((userrepo.findById("ram"))).thenReturn(Optional.ofNullable(user2));
		when((userrepo.findById("pavi"))).thenReturn(Optional.ofNullable(null));
		assertEquals("update success", userservice.updateUser(user,"ram"));
		assertThrows(UserNotFoundException.class, () -> userservice.updateUser(user2,"pavi"));
	}

}
