package com.epam.uiservices;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.crudoperations.RetrieveByGroup;
import com.epam.models.Account;
import com.epam.passwordmanagementsystem.InputObjectProvider;

public class ViewByGroupUI {
	private static final Logger LOGGER=LogManager.getLogger(ViewByGroupUI.class);
	public static Scanner sc=InputObjectProvider.getSc();
	public static HashMap<String,Account> groupdata;
	public static void display() {
		
		LOGGER.info("Enter group:");
		String group=sc.next();
	 	RetrieveByGroup retrieve=new RetrieveByGroup();
	 	
	 	try {
	 		groupdata=retrieve.retrieve(group);
	 	for(String url:groupdata.keySet()) {
			String username=groupdata.get(url).getUsername();
			String password=groupdata.get(url).getPassword();
			LOGGER.info(group+"  "+url+"  "+password+"  "+username);
			
		}
	 	}
		catch(NoSuchElementException nse) {
			LOGGER.error(nse.getMessage());
		}
	}
}
