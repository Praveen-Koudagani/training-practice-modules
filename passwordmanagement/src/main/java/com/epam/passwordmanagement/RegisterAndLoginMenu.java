package com.epam.passwordmanagement;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.crud_operations.MasterOperations;
import com.epam.crud_operations.MasterProvider;
import com.epam.crud_operations.SingleEntityManagerFactory;
import com.epam.entities.Master;
import com.epam.taskfactory.Task;
import com.epam.taskfactory.TaskFactory;
import com.epam.validations.Validations;

@Component
public class RegisterAndLoginMenu {
	private static final Logger LOGGER=LogManager.getLogger(RegisterAndLoginMenu.class);
	@Autowired
    Validations validations;
	static Scanner sc=InputObjectProvider.getSc();
    public static boolean loginstatus=false;
    static EntityManagerFactory factory;
    static EntityManager manager;
    @Autowired
    TaskFactory taskobj ;
	public void registerAsMaster() {
		
		this.setMasterRegistrationDetails();
		LOGGER.info("----Registered successfully---");
	}

	public void setMasterRegistrationDetails() {
		LOGGER.info("Enter Username:");
		String username1 = sc.next();
		String passwrd = passwordCheck();
		MasterOperations.createMaster(username1, passwrd);
	}

	public String passwordCheck() {
		String password="";
		while (true) {
			LOGGER.info("enter password");
			 password = sc.next();

			if (!this.validations.validatePassword(password)) {
				LOGGER.info("Password must have at least one of digit,lowercase,uppercase,special character");
				LOGGER.info("min of length 8");
			} else
				break;
		}
		return password;
	}

	public void login() {
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		
		LOGGER.info("Enter MasterUserName :");
		String username2 = sc.next();
		
		if (manager.find(Master.class,username2)!=null && username2.equals(manager.find(Master.class,username2).getUsername())) {
			LOGGER.info("Enter MasterPassword");
			String password2 = sc.next();
			if (password2.equals(manager.find(Master.class,username2).getPassword())) {
				LOGGER.info("-------Logined successfully--------");
				LOGGER.info("");
				MasterProvider.set(username2, password2);
                loginstatus=true;
				loginHelper();
				

			} else
				LOGGER.info("Inavalid Password");
		} else
			LOGGER.info("Invalid username");
		
	}

	public  void loginHelper() {
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
				//TaskFactory taskobj = new TaskFactory();
				Task task = this.taskobj.createTask(option2);
				task.doTask();}
				catch(NoSuchElementException nse) {
					LOGGER.error(nse.getMessage());
				}
			} else if (option2 == 8)
				break;

		}
	}

	public  void displayCRUDOperations() {
		LOGGER.info("<=============Task Menu===========>");
		LOGGER.info("  Choose option");
		LOGGER.info("  1.Add new account");
		LOGGER.info("  2.Read Password");
		LOGGER.info("  3.List All Accounts By Group");
		LOGGER.info("  4.Delete  Account");
		LOGGER.info("  5.Update Password Account");
		LOGGER.info("  6.Update Group details");
		LOGGER.info("  7.Delete Group");
		LOGGER.info("  8.Logout");
	}
}
