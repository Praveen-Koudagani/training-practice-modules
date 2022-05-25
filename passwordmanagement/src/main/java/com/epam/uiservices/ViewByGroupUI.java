package com.epam.uiservices;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.crud_operations.GroupOperations;
import com.epam.passwordmanagement.InputObjectProvider;

@Component
public class ViewByGroupUI {
	private static final Logger LOGGER = LogManager.getLogger(ViewByGroupUI.class);
	public static Scanner sc = InputObjectProvider.getSc();
	@Autowired
	GroupOperations groupoperations;

	public void display() {

		LOGGER.info("Enter group:");
		String group = sc.next();

		try {
			groupoperations.viewAccounts(group);
		} catch (NoSuchElementException nse) {
			LOGGER.error(nse.getMessage());
		}
	}
}
