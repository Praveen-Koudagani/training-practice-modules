package com.epam.passwordmanagementsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main 
{
	private static final Logger LOGGER=LogManager.getLogger(Main.class);
	static Scanner sc=InputObjectProvider.getSc();
	public static void main( String[] args ) {
	mainMenu();
	LOGGER.info("Exited Successfully from application");
	
}
	public static void mainMenu() {
		while(true) {
		    int option=10;
					
		    LOGGER.info("<-----------     Main Menu     --------------->    ");
		    LOGGER.info("Choose Operation");
		    LOGGER.info("  1.Signup as master User");
		    LOGGER.info("  2.Login into Password Management system");
		    LOGGER.info("  3.Exit");
			try {
			option=sc.nextInt();}
			catch(InputMismatchException e) {
				LOGGER.error(e.getStackTrace()+"] ||Please enter number");
				sc.next();
			}
			if(option==1){
					RegisterAndLoginMenu.registerAsMaster();
			}
			else if(option==2) {
				RegisterAndLoginMenu.login();
			}
			else if(option==3)
						break;
				
		}
	}
}
