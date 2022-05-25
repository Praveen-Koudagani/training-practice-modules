package com.epam.crud_operations;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.MasterRepository;
import com.epam.entities.Master;
@Service
public class MasterOperations {
     @Autowired
     MasterRepository masterrepo;
     private static final Logger logger=LogManager.getLogger( MasterOperations.class);
	@Transactional
	public boolean createMaster(String username, String password) {
		boolean status = false;
		Master master = new Master();
		master.setUsername(username);
		master.setPassword(password);
		Optional<Master> searchedMaster=masterrepo.findById(username);
		logger.info(searchedMaster.isEmpty());
        if(searchedMaster.isEmpty()) {
        	masterrepo.save(master);
        	status=true;
        }
		return status;
	}
	
	@Transactional
	public boolean isMasterPresent(String username ,String password) {
		boolean status = true;
		Optional<Master> searchedMaster=masterrepo.findById(username);
        if(searchedMaster.isEmpty()) {
        	status=false;
        }
		return status;
	}
}
