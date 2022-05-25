package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.epam.crud_operations.MasterProvider;
import com.epam.crud_operations.SingleEntityManagerFactory;
import com.epam.entities.Account;
import com.epam.entities.Master;
@Component
public class AccountDB implements DatabaseOperations {
	EntityManagerFactory factory;
	EntityManager manager;

	@Override
	public boolean insert(Account account) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery = "select a from Account a where a.master=?1";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, master);
		List<Account> accounts = query.getResultList();
		accounts.add(account);
		master.setAccounts(accounts);
		try {
			manager.getTransaction().begin();
			manager.merge(master);
			manager.getTransaction().commit();
			status = true;
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

	@Override
	public boolean update(Account account) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(account);
			manager.getTransaction().commit();

			status = true;
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

	@Override
	@Transactional
	public boolean delete(Account account) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		try {
			System.out.println(account.getUrl());
			Account account2=manager.find(Account.class, account.getId());
				manager.getTransaction().begin();
				manager.remove(account2);
				manager.getTransaction().commit();
				status = true;
		} catch (Exception e) {
			System.out.println("exception occured");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAll() {
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery = "select a from Account a where a.master=?1";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, master);
		return query.getResultList();
	}

}
