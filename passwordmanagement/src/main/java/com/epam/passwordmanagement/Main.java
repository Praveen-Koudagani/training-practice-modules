package com.epam.passwordmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
	static Scanner sc = InputObjectProvider.getSc();
	static RegisterAndLoginMenu registerlogin;

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		registerlogin=context.getBean(RegisterAndLoginMenu.class);
		new Main().mainMenu();
		LOGGER.info("Exited Successfully from application");

	}

	public  void mainMenu() {

		while (true) {
			int option = 10;

			LOGGER.info("<-----------     Main Menu     --------------->    ");
			LOGGER.info("Choose Operation");
			LOGGER.info("  1.Signup as master User");
			LOGGER.info("  2.Login into Password Management system");
			LOGGER.info("  3.Exit");
			try {
				option = sc.nextInt();
			} catch (InputMismatchException e) {
				LOGGER.error(e.getStackTrace() + "] ||Please enter number");
				sc.next();
			}
			if (option == 1) {
				Main.registerlogin.registerAsMaster();
			} else if (option == 2) {
				Main.registerlogin.login();
			} else if (option == 3) {
				break;
			}

		}
	}
}
