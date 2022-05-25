package com.epam.uiservices;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.crud_operations.AccountOperationsDao;
import com.epam.passwordmanagement.InputObjectProvider;
@Component
public class ReadPasswordUI {

	private static final Logger LOGGER = LogManager.getLogger(ReadPasswordUI.class);
	public static Scanner sc = InputObjectProvider.getSc();
	public static String password;
	@Autowired
	private AccountOperationsDao accountDao;
	public  void readPassword() {
		LOGGER.info("Enter account url");
		String accounturl = sc.next();

		try {
			password=this.accountDao.readPassword(accounturl);
			LOGGER.info("The password is:".concat(password));
		} catch (Exception nse) {
			LOGGER.error("Exception:" + nse.getMessage());
		}
	}

}
