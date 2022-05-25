package com.epam.crud_operations;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingleEntityManagerFactory {
private static final EntityManagerFactory factory=Persistence.createEntityManagerFactory("PasswordManagementJPADB");

public static EntityManagerFactory getFactory() {
	return factory;
}
}
