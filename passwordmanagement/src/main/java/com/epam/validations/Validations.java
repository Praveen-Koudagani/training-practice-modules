package com.epam.validations;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.epam.crud_operations.MasterProvider;
import com.epam.crud_operations.SingleEntityManagerFactory;
import com.epam.entities.Account;
import com.epam.entities.Master;
@Component
public class Validations {
	static EntityManagerFactory factory;
	static EntityManager manager;
	public boolean validatePassword(String password) {
		boolean status=false;
		String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(password);
		if(m.matches()) {
			status=true;
		}
		return status;
	}

	public boolean isAccountUnique(String accounturl,String group) {
		boolean status=false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery="select a from Account a where a.master=?1 and a.url=?2 and a.groupname=?3";
		Query query=manager.createQuery(qlquery,Account.class);
		query.setParameter(1,master);
		query.setParameter(2,accounturl);
		query.setParameter(3,group);
		List<Account> accounts=query.getResultList();
		if(accounts.size()<=0) {
			status=true;
		}
		return status;
	}
	public boolean isgroupValid(String group) {
		boolean status=false;
		if(group.matches("^[a-zA-Z]*$"))
			status=true;
		return status;
	}
	public boolean isValidUrl(String url) {
		boolean status=false;
		String regex="((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\b([-a-zA-Z0-9@:%_.\\+~#?&//=]*)";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(url);
		if(m.matches()) {
			status=true;
		}
		return status;
	}
}
