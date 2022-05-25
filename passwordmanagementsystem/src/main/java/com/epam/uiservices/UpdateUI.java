package com.epam.uiservices;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.crudoperations.UpdateAccountPassword;
import com.epam.crudoperations.UpdateGroupDetails;
import com.epam.passwordmanagementsystem.InputObjectProvider;
import com.epam.validations.Validations;

public class UpdateUI {
	private static final Logger LOGGER = LogManager.getLogger(UpdateUI.class);
	public static Scanner sc=InputObjectProvider.getSc();
	static Validations validation = new Validations();
	public static void modifyGroup() {
		
		LOGGER.info("Enter old group name ");
		String group = sc.next();
		String newgroup;
		while (true) {
			LOGGER.info("Enter new group name ");
			newgroup = sc.next();
			if (!validation.isgroupValid(newgroup)) {
				LOGGER.info("Enter group name with alphabets");
			} else
				break;
		}
		UpdateGroupDetails updategroup = new UpdateGroupDetails();
		String[] arg1 = { group, newgroup };
		LOGGER.info(updategroup.update(arg1));
	}

	public static void modifyAccountPasseord() {
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
				updatePasswordcall();
			} else if (option == 2) {
				updateURLcall();
			} else if (option == 3)
				break;

		}
	}

	private static void updateURLcall() {
		LOGGER.info("enter url");
		String url2 = sc.next();
		String newurl= urlCheck();
		UpdateAccountPassword map = new UpdateAccountPassword();
		String[] arg1 = { url2, newurl };
		LOGGER.info(map.updateURL(arg1));
	}

	private static void updatePasswordcall() {
		LOGGER.info("enter url");
		String url1 = sc.next();
		String newpassword = passwordCheck();
		UpdateAccountPassword map = new UpdateAccountPassword();
		String[] arg1 = { url1, newpassword };
		LOGGER.info(map.update(arg1));
	}

	private static String urlCheck() {
		 String newurl;
		while (true) {

			LOGGER.info("Enter new UrL");
			newurl = sc.next();
			if (!validation.isValidUrl(newurl)) {
				LOGGER.info("Enter valid url");
			} else
				break;
		}
		return newurl;
	}

	private static String passwordCheck() {
		String newpassword;
		while (true) {
			LOGGER.info("enter new password");
			newpassword = sc.next();

			if (!validation.validatePassword(newpassword)) {
				LOGGER.info("Password must have at least one of digit,lowercase,uppercase,special character");
				   LOGGER.info("min of length 8");
			} else
				break;
		}
		return newpassword;
	}
}
