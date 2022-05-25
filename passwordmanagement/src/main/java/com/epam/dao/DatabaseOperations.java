package com.epam.dao;

import java.util.List;

import com.epam.entities.Account;

public interface DatabaseOperations {
public boolean insert(Account acc);
public boolean update(Account acc);
public boolean delete(Account acc);
public List<Account> findAll();
}
