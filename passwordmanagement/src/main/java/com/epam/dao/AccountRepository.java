package com.epam.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entities.Account;
import com.epam.entities.Master;

public interface AccountRepository extends CrudRepository<Account, Long>{
public List<Account> findByGroupnameAndMaster(String groupname,Master master);
public List<Account> findByMaster(Master master);
public Optional<Account> findByUrlAndMaster(String url,Master master);
public Optional<Account> findByUrlAndMasterAndGroupname(String url,Master master,String groupname);
}
//findByLastnameAndFirstname
//findByLastnameOrFirstname