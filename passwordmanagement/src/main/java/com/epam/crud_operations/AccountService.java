package com.epam.crud_operations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.AccountRepository;
import com.epam.dto.AccountDto;
import com.epam.entities.Account;
import com.epam.entities.Master;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountrepo;
	Master mastername = MasterProvider.getMaster();

	// String groupname, String url, String password, String username
	public boolean createAccount(AccountDto accountdto) {
		boolean status = false;
		ModelMapper mapper = new ModelMapper();
		Account account = mapper.map(accountdto, Account.class);
		/*
		 * Account account = new Account(); account.setGroupname(groupname);
		 * account.setPassword(password); account.setUrl(url);
		 * account.setUsername(username);
		 */
		account.setMaster(mastername);
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMasterAndGroupname(account.getUrl(), mastername,
				account.getGroupname());
		System.out.println("hi"+searchedaccount);
		if (!searchedaccount.isPresent()) {
			accountrepo.save(account);
			status = true;
		}
		return status;
	}

	public boolean deleteAccount(String url) {
		boolean status = false;
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		System.out.println(searchedaccount);
		if (searchedaccount.isPresent()) {
			//long id = accountrepo.findByUrlAndMaster(url, mastername).getId();
			long id = searchedaccount.get().getId();
			accountrepo.deleteById(id);
			status = true;
		}

		return status;

	}

	public List<Account> viewAllAccounts() {
		return (List<Account>) accountrepo.findAll();
	}

	public String readPassword(String url) {
		String password = "not present";
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		if (searchedaccount.isPresent()) {
			password=searchedaccount.get().getPassword();
			//password = accountrepo.findByUrlAndMaster(url, mastername).getPassword();
		}
		return password;

	}

	public boolean updateAccountPassword(String url, String password) {
		boolean status = false;
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		if (searchedaccount.isPresent()) {
			//Account account = accountrepo.findByUrlAndMaster(url, mastername);
			Account account = searchedaccount.get();
			account.setPassword(password);
			accountrepo.save(account);
			status = true;
		}
		return status;
	}

	@Transactional
	public boolean updateAccountUsername(String url, String username) {
		boolean status = false;
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		if (searchedaccount.isPresent()) {
			//Account account = accountrepo.findByUrlAndMaster(url, mastername);
			Account account=searchedaccount.get();
			account.setUsername(username);
			accountrepo.save(account);
			status = true;
		}
		return status;

	}
}
