package com.epam.crud_operations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.AccountRepository;
import com.epam.dto.AccountDto;
import com.epam.entities.Account;
import com.epam.entities.Master;
import com.epam.exceptions.AccountDuplicationException;
import com.epam.exceptions.AccountNotFoundException;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountrepo;
	Master mastername = MasterProvider.getMaster();
	private static final Logger logger=LogManager.getLogger(AccountService.class);

	
	public boolean createAccount(AccountDto accountdto) {
		boolean status = false;
		ModelMapper mapper = new ModelMapper();
		Account account = mapper.map(accountdto, Account.class);
		account.setMaster(mastername);
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMasterAndGroupname(account.getUrl(), mastername,
				account.getGroupname());
		
		if (!searchedaccount.isPresent()) {
			accountrepo.save(account);
			status = true;
		}
		else {
			throw new AccountDuplicationException("Account already exists");
		}
		return status;
	}

	public boolean deleteAccount(String url) {
		boolean status = false;
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		logger.info(searchedaccount);
		if (searchedaccount.isPresent()) {
			long id = searchedaccount.get().getId();
			accountrepo.deleteById(id);
			status = true;
		}
		else {
			throw new AccountNotFoundException("account not exists");
		}

		return status;

	}

	public List<Account> viewAllAccounts() {
		return (List<Account>) accountrepo.findAll();
	}

	public String readPassword(String url) {
		String password;
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		if (searchedaccount.isPresent()) {
			password=searchedaccount.get().getPassword();
		}
		else {
			throw new AccountNotFoundException("account not exists");
		}
		return password;

	}

	public boolean updateAccountPassword(String url, String password) {
		boolean status = false;
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		if (searchedaccount.isPresent()) {
			Account account = searchedaccount.get();
			account.setPassword(password);
			accountrepo.save(account);
			status = true;
		}
		else {
			throw new AccountNotFoundException("account not exists");
		}
		return status;
	}

	@Transactional
	public boolean updateAccountUsername(String url, String username) {
		boolean status = false;
		Optional<Account> searchedaccount = accountrepo.findByUrlAndMaster(url, mastername);
		if (searchedaccount.isPresent()) {
			Account account=searchedaccount.get();
			account.setUsername(username);
			accountrepo.save(account);
			status = true;
		}
		else {
			throw new AccountNotFoundException("account not exists");
		}
		return status;

	}
}
