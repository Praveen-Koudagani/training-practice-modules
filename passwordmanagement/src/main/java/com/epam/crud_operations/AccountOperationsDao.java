package com.epam.crud_operations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.AccountDB;
import com.epam.entities.Account;
import com.epam.entities.Master;

@Service
public class AccountOperationsDao {
	static EntityManagerFactory factory;
	static EntityManager manager;
	 @Autowired
	 AccountDB accountdb ;

		/*
		 * public AccountOperationsDao() {
		 * 
		 * }
		 * 
		 * public AccountOperationsDao(AccountDB accountdb) { this.accountdb =
		 * accountdb; }
		 */
	@Transactional
	public boolean createAccount(String groupname, String url, String password, String username) {
		boolean status = false;
		if (isUnique(groupname, url)) {

			Account account = new Account();
			account.setGroupname(groupname);
			account.setPassword(password);
			account.setUrl(url);
			account.setUsername(username);
			if (this.accountdb.insert(account)) {
				status = true;
			}
		}
		return status;
	}

	public boolean isUnique(String group, String accounturl) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery = "select a from Account a where a.master=?1 and a.url=?2 and a.groupname=?3";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, master);
		query.setParameter(2, accounturl);
		query.setParameter(3, group);
		List<Account> accounts = query.getResultList();
		if (accounts.size() <= 0) {
			status = true;
		}
		return status;
	}

	@Transactional
	public boolean deleteAccount(String url) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		String qlquery = "select a from Account a where a.url=?1 and a.master=?2";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, url);
		query.setParameter(2, MasterProvider.getMaster());
		List<Account> accounts = query.getResultList();
		Account account = null;
		if (accounts.size() > 0)
			account = accounts.get(0);
		try {

			if (this.accountdb.delete(account)) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("account not found");
		} finally {
			if (manager != null) {
				manager.close();
			}
		}

		return status;

	}

	@Transactional
	public boolean updateAccountPassword(String url, String password) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery = "select a from Account a where a.url=?1 and a.master=?2";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, url);
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
		Account account = null;
		if (accounts.size() > 0) {
			account = accounts.get(0);
			account.setPassword(password);
		}
		try {
			if (accountdb.update(account)) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("account not found");
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
		return status;
	}

	@Transactional
	public boolean updateAccountUsername(String url, String username) {
		boolean status = false;
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery = "select a from Account a where a.url=?1 and a.master=?2";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, url);
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
		Account account = null;
		if (accounts.size() > 0) {
			account = accounts.get(0);
			account.setUsername(username);
		}
		try {

			if (this.accountdb.update(account)) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("account not found");
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
		return status;

	}

	@Transactional
	public List<Account>  viewAllAccounts() {
		boolean status = false;
		List<Account> accounts = this.accountdb.findAll();

		try {
			accounts.stream().forEach(i -> System.out
					.println(i.getUrl() + " " + i.getGroupname() + " " + i.getUsername() + " " + i.getPassword()));
			status = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return accounts;
	}

	@Transactional
	public String readPassword(String url) {
		String status = "not present";
		factory = SingleEntityManagerFactory.getFactory();
		manager = factory.createEntityManager();
		Master master = MasterProvider.getMaster();
		String qlquery = "select a from Account a where a.url=?1 and a.master=?2";
		Query query = manager.createQuery(qlquery, Account.class);
		query.setParameter(1, url);
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
		Account account = null;
		if (accounts.size() > 0) {
			account = accounts.get(0);
			status = account.getPassword();
		}

		if (manager != null) {
			manager.close();
		}

		return status;

	}

}
