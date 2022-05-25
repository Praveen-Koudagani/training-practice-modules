package com.epam.uiservices;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.crud_operations.AccountOperationsDao;
import com.epam.crud_operations.GroupOperations;
import com.epam.passwordmanagement.InputObjectProvider;
import com.epam.validations.Validations;
@Component
public class UpdateUI {
	private static final Logger LOGGER = LogManager.getLogger(UpdateUI.class);
	public static Scanner sc=InputObjectProvider.getSc();
	@Autowired
	GroupOperations groupoperations;
	@Autowired
	private Validations validation;
	@Autowired
	private AccountOperationsDao accountDao;
	public  void modifyGroup() {
		
		LOGGER.info("Enter old group name ");
		String group = sc.next();
		String newgroup;
		while (true) {
			LOGGER.info("Enter new group name ");
			newgroup = sc.next();
			if (!this.validation.isgroupValid(newgroup)) {
				LOGGER.info("Enter group name with alphabets");
			} else
				break;
		}
		if(groupoperations.updateGroupName(group, newgroup))//
		LOGGER.info("updated");
	}

	public  void modifyAccountPasseord() {
		int option = 0;
		while (true) {
			LOGGER.info("----Choose option----");
			LOGGER.info("  1.update Password");
			LOGGER.info("  2.update AccountURL");
			LOGGER.info("  3.<-back");
			try {
				option = sc.nextInt();
			} catch (InputMismatchException e) {
				LOGGER.error(e.getStackTrace() + "] ||Please enter number");
				sc.next();
			}
			if (option == 1) {
				this.updatePasswordcall();
			} else if (option == 2) {
				this.updateURLcall();
			} else if (option == 3)
				break;

		}
	}

	private  void updateURLcall() {
		LOGGER.info("enter url");
		String url2 = sc.next();
		LOGGER.info("enter new username");
		String username= sc.next();
		if(this.accountDao.updateAccountUsername(url2,username))
		LOGGER.info("updated");
	}

	private void updatePasswordcall() {
		LOGGER.info("enter url");
		String url1 = sc.next();
		String newpassword = passwordCheck();
		if(this.accountDao.updateAccountPassword(url1, newpassword))
		LOGGER.info("updated");
	}

	private  String urlCheck() {
		 String newurl;
		while (true) {

			LOGGER.info("Enter new UrL");
			newurl = sc.next();
			if (!this.validation.isValidUrl(newurl)) {
				LOGGER.info("Enter valid url");
			} else
				break;
		}
		return newurl;
	}

	private  String passwordCheck() {
		String newpassword;
		while (true) {
			LOGGER.info("enter new password");
			newpassword = sc.next();

			if (!this.validation.validatePassword(newpassword)) {
				LOGGER.info("Password must have at least one of digit,lowercase,uppercase,special character");
				   LOGGER.info("min of length 8");
			} else
				break;
		}
		return newpassword;
	}
}
