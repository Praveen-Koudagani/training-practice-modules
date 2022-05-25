package com.epam.uiservices;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.crud_operations.AccountOperationsDao;
import com.epam.crud_operations.GroupOperations;
import com.epam.passwordmanagement.InputObjectProvider;

@Component
public class DeleteUI {
	private static final Logger LOGGER=LogManager.getLogger(DeleteUI.class);
	public static Scanner sc=InputObjectProvider.getSc();
	@Autowired
	private AccountOperationsDao accountDao;
	@Autowired
	private GroupOperations group;
	public void deleteGroup() {
		
		LOGGER.info("Enter group name");
		String groupname=sc.next();
		if(group.deleteByGroupAccount(groupname))
		LOGGER.info("deleted group accounts");
		
	}
public void deleteAccount() {
	LOGGER.info("Enter Accounturl to delete password account");
    String url=sc.next();
    if(this.accountDao.deleteAccount(url))
    LOGGER.info("deleted account url");
}
}
