package com.epam.uiservices;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.crud_operations.AccountOperationsDao;
import com.epam.passwordmanagement.InputObjectProvider;
import com.epam.validations.Validations;
@Component
public class CreateUI {
	@Autowired
	private AccountOperationsDao accountDao;
	private static final Logger logger=LogManager.getLogger(CreateUI.class);
	static Scanner sc=InputObjectProvider.getSc();
	@Autowired
    Validations validations;
    public static String status;
    //public static AccountOperations accountOperations=new AccountOperations();
	public  void addAccount() {
        String group = this.groupCheck();
        String url1 = this.urlCheck(group);
        logger.info("Enter User name");
        String username=sc.next();
        String password =this.passwordCheck();
        if(this.accountDao.createAccount(group, url1, password, username))
        logger.info("added successfully");
        else
        	 logger.info("not successful");

}

private String passwordCheck() {
	String password;
	while(true) {
		logger.info("enter password");
	   password=sc.next();
  
	   if(!this.validations.validatePassword(password)) {
		   logger.info("Password must have at least one of digit,lowercase,uppercase,special character");
		   logger.info("min of length 8");
		   }
	   else
		   break;
	}
	return password;
}

private String urlCheck( String group) {
	String url1;
	while(true) {
		logger.info("Enter UrL");
	   url1=sc.next();
	   if(!this.validations.isValidUrl(url1)) {
		   logger.info("Enter valid url");
			/*
			 * } else if(!validations.isAccountUnique(url1, group)) {
			 * logger.info("this account already present in group");
			 */
		   
	   }else
		   break;}
	return url1;
}

private String groupCheck() {
	String group;
	while(true) {
		logger.info("enter group");
	    group=sc.next();
	if(!this.validations.isgroupValid(group)) {
		logger.info("Enter group name with alphabets");
		}
	else
		break;
	}
	return group;
}
}
