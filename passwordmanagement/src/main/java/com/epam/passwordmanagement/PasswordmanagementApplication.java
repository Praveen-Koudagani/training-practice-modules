package com.epam.passwordmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.epam")
@EntityScan("com.epam")
@ComponentScan(basePackages = { "com.epam" })
public class PasswordmanagementApplication {

	private static final Logger LOGGER = LogManager.getLogger(PasswordmanagementApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PasswordmanagementApplication.class, args);
		/*
		 * ApplicationContext context = new
		 * AnnotationConfigApplicationContext(PasswordmanagementApplication.class);
		 * registerlogin=context.getBean(RegisterAndLoginMenu.class); new
		 * PasswordmanagementApplication().mainMenu();
		 * LOGGER.info("Exited Successfully from application");
		 */
	}
}