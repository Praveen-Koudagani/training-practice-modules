package com.epam.crud_operations;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.epam.entities.Account;
import com.epam.entities.Master;
@Service
public class GroupOperations {
	static EntityManagerFactory factory;
	static EntityManager manager;
	@Autowired
	AccountOperationsDao accountDao;
	public GroupOperations() {
		
	}
	public GroupOperations(AccountOperationsDao accountDao) {
	this.accountDao=accountDao;
	}
	@Transactional
	public List<Account> viewAccounts(String group) {
		List<Account> accounts=null;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		try {
		 accounts = manager.find(Master.class, master.getUsername()).getAccounts().stream()
				.filter(i -> i.getGroupname().equals(group)).collect(Collectors.toList());

		
			accounts.stream().forEach(i -> System.out
					.println(i.getUrl() + " " + i.getGroupname() + " " + i.getUsername() + " " + i.getPassword()));
		} catch (Exception e) {
			if (manager != null) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
		return accounts;
	}

	@Transactional
	public boolean updateGroupName(String groupname, String newgroupname) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery = "select a from Account a where a.groupname=?1 and a.master=?2";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, groupname);
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
        System.out.println(accounts.size());
		try {
			if(accounts.size()>0) {
			accounts.stream().forEach(account -> account.setGroupname(newgroupname));
			master.setAccounts(accounts);
			manager.getTransaction().begin();
			manager.merge(master);
			manager.getTransaction().commit();

			status = true;}
		} catch (Exception e) {
			if (manager != null) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
		return status;

	}

	@Transactional
	public boolean deleteByGroupAccount(String group1) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		
		try {
			List<Account> accounts = manager.find(Master.class, master.getUsername()).getAccounts().stream()
					.filter(i -> i.getGroupname().equals(group1)).collect(Collectors.toList());
			if (accounts != null) {
				for (Account account : accounts) {
					System.out.println(account.getUrl());
					accountDao.deleteAccount(account.getUrl());
					status = true;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + status);
			if (manager != null) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
		return status;

	}

}
