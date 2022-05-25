package com.epam.uiservices;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.crudoperations.DeleteAccount;
import com.epam.crudoperations.DeleteGroup;
import com.epam.passwordmanagementsystem.InputObjectProvider;


public class DeleteUI {
	private static final Logger LOGGER=LogManager.getLogger(DeleteUI.class);
	public static Scanner sc=InputObjectProvider.getSc();
	static DeleteGroup deletegroup;
	static DeleteAccount deleteaccount;
	public static void deleteGroup() {
		
		LOGGER.info("Enter group name");
		String group1=sc.next();
		deletegroup=new DeleteGroup();
		LOGGER.info(deletegroup.delete(group1));
		
	}
public static void deleteAccount() {
	LOGGER.info("Enter Accounturl to delete password account");
    String url=sc.next();
    deleteaccount=new DeleteAccount();
    LOGGER.info(deleteaccount.delete(url));
}
}
