package com.epam.passwordmanagementsystem;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.crudoperations.CreateMaster;
import com.epam.models.Master;
import com.epam.taskfactory.Task;
import com.epam.taskfactory.TaskFactory;
import com.epam.uiservices.RegisterMaster;
import com.epam.validations.Validations;

public class RegisterAndLoginMenu {
	private static final Logger LOGGER=LogManager.getLogger(RegisterAndLoginMenu.class);
	public static Master master;
	public static Validations validations = new Validations();
	static Scanner sc=InputObjectProvider.getSc();
    public static boolean loginstatus=false;
	public static void registerAsMaster() {
		RegisterMaster.register();
		master = CreateMaster.getMasterobj();
		setMasterRegistrationDetails();
		LOGGER.info("----Registered successfully---");
	}

	public static void setMasterRegistrationDetails() {
		LOGGER.info("Enter Username:");
		String username1 = sc.next();
		master.setUsername(username1);
		String passwrd = passwordCheck();
		master.setPassword(passwrd);
	}

	public static String passwordCheck() {
		String password="";
		while (true) {
			LOGGER.info("enter password");
			 password = sc.next();

			if (!validations.validatePassword(password)) {
				LOGGER.info("Password must have at least one of digit,lowercase,uppercase,special character");
				LOGGER.info("min of length 8");
			} else
				break;
		}
		return password;
	}

	public static void login() {
		LOGGER.info("Enter MasterUserName :");
		String username2 = sc.next();
		if (username2.equals(master.getUsername())) {
			LOGGER.info("Enter MasterPassword");
			String password2 = sc.next();
			if (password2.equals(master.getPassword())) {
				LOGGER.info("-------Logined successfully--------");
				LOGGER.info("");
                loginstatus=true;
				loginHelper();

			} else
				LOGGER.info("Inavalid Password");
		} else
			LOGGER.info("Invalid username");
		
	}

	public static void loginHelper() {
		while (true) {
			int option2 = 10;
			displayCRUDOperations();
			try {
				option2 = sc.nextInt();
			} catch (InputMismatchException e) {
				LOGGER.info("Please enter number");
				sc.next();
			}

			if (option2 != 8) {
				try {
				TaskFactory taskobj = new TaskFactory();
				Task task = taskobj.createTask(option2);
				task.doTask();}
				catch(NoSuchElementException nse) {
					LOGGER.error(nse.getMessage());
				}
			} else if (option2 == 8)
				break;

		}
	}

	public static void displayCRUDOperations() {
		LOGGER.info("<=============Task Menu===========>");
		LOGGER.info("  Choose option");
		LOGGER.info("  1.Create Password for an account");
		LOGGER.info("  2.Read Password");
		LOGGER.info("  3.List Password account and Groups");
		LOGGER.info("  4.Delete Password account");
		LOGGER.info("  5.Update password account");
		LOGGER.info("  6.Update Group details");
		LOGGER.info("  7.Delete Group");
		LOGGER.info("  8.Logout");
	}
}
