package com.epam.uiservices;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.crudoperations.RetrievePassword;
import com.epam.passwordmanagementsystem.InputObjectProvider;


public class ReadPasswordUI {

	private static final Logger LOGGER=LogManager.getLogger(ReadPasswordUI.class);
	public static Scanner sc=InputObjectProvider.getSc();
	static RetrievePassword retrievepassword;
	public static String password;
	public static void readPassword() {
		
		LOGGER.info("Enter account url");
		  String accounturl=sc.next();
		  LOGGER.info("enter group");
		  String group=sc.next();
		  retrievepassword=new RetrievePassword();
		  String[] arg= {group,accounturl};
		  try {
		  password=retrievepassword.retrieve(arg);
		  LOGGER.info("The password is:".concat(password));
		  }catch(NoSuchElementException nse) {
			  LOGGER.error("Exception:"+nse.getMessage());
		  }
	}

}
