package com.epam.lms.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.lms.entities.User;

public interface UserRepository extends CrudRepository<User,String>{
	public Optional<User> findByUsername(String username);

}
