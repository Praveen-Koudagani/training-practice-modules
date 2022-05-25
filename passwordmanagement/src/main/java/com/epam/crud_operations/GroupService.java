package com.epam.crud_operations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.AccountRepository;
import com.epam.entities.Account;
import com.epam.entities.Master;
@Service
public class GroupService {
	@Autowired
	AccountRepository accountrepo;
	Master mastername = MasterProvider.getMaster();

	public List<Account> viewAccounts(String group) {
		List<Account> accounts = accountrepo.findByGroupnameAndMaster(group, mastername);
		/*
		 * accounts.stream().forEach(i -> System.out .println(i.getUrl() + " " +
		 * i.getGroupname() + " " + i.getUsername() + " " + i.getPassword()));
		 */
		return accounts;
	}

	public boolean updateGroupName(String groupname, String newgroupname) {
		boolean status = false;
		List<Account> accounts=accountrepo.findByGroupnameAndMaster(groupname, mastername);
		if(accounts.size()>0) {
			status=true;
			accounts.stream().forEach(account ->{ account.setGroupname(newgroupname);accountrepo.save(account);});
		}
		return status;
	}
	
	public boolean deleteByGroupAccount(String group) {
		boolean status = false;
			List<Account> accounts =accountrepo.findByGroupnameAndMaster(group, mastername); 
			if(accounts.size()>0) {
				status=true;
				accountrepo.deleteAll(accounts);
			}
		return status;

	}
}
