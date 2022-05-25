package com.epam.uiservices;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.crudoperations.CreateAccount;
import com.epam.passwordmanagementsystem.InputObjectProvider;
import com.epam.validations.Validations;

public class CreateUI {
	private static final Logger logger=LogManager.getLogger(CreateUI.class);
	static Scanner sc=InputObjectProvider.getSc();
    private static Validations validations=new Validations();
    public static CreateAccount createaccount;
    public static String status;
	public static void addAccount() {
        String group = CreateUI.groupCheck();
        String url1 = CreateUI.urlCheck(group);
        logger.info("Enter User name");
        String username=sc.next();
        String password =CreateUI.passwordCheck();
        createaccount=new CreateAccount();
        String[] arg={url1,password,username,group};
        status=createaccount.create(arg);
        logger.info(status);

}

private static String passwordCheck() {
	String password;
	while(true) {
		logger.info("enter password");
	   password=sc.next();
  
	   if(!validations.validatePassword(password)) {
		   logger.info("Password must have at least one of digit,lowercase,uppercase,special character");
		   logger.info("min of length 8");
		   }
	   else
		   break;
	}
	return password;
}

private static String urlCheck( String group) {
	String url1;
	while(true) {
		logger.info("Enter UrL");
	   url1=sc.next();
	   if(!validations.isValidUrl(url1)) {
		   logger.info("Enter valid url");
		   }
	   else if(!validations.isAccountUnique(url1, group)) {
		   logger.info("this account already present in group");
		   
	   }else
		   break;}
	return url1;
}

private static String groupCheck() {
	String group;
	while(true) {
		logger.info("enter group");
	    group=sc.next();
	if(!validations.isgroupValid(group)) {
		logger.info("Enter group name with alphabets");
		}
	else
		break;
	}
	return group;
}
}
