package com.epam.crud_operations;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.MasterRepository;
import com.epam.entities.Master;
@Service
public class MasterOperations {
     @Autowired
     MasterRepository masterrepo;
	@Transactional
	public boolean createMaster(String username, String password) {
		boolean status = false;
		Master master = new Master();
		master.setUsername(username);
		master.setPassword(password);
		Optional<Master> searchedmaster=masterrepo.findById(username);
	    System.out.println(searchedmaster.isEmpty());
        if(searchedmaster.isEmpty()) {
        	masterrepo.save(master);
        	status=true;
        }
		return status;
	}
	
	@Transactional
	public boolean isMasterPresent(String username ,String password) {
		boolean status = true;
		Optional<Master> searchedmaster=masterrepo.findById(username);
        if(searchedmaster.isEmpty()) {
        	status=false;
        }
		return status;
	}
}
