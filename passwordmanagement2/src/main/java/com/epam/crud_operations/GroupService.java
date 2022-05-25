package com.epam.crud_operations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.AccountRepository;
import com.epam.entities.Account;
import com.epam.entities.Master;
import com.epam.exceptions.GroupNotFoundException;
@Service
public class GroupService {
	@Autowired
	AccountRepository accountrepo;
	Master mastername = MasterProvider.getMaster();

	public List<Account> viewAccounts(String group) {
		return accountrepo.findByGroupnameAndMaster(group, mastername);
	}

	public boolean updateGroupName(String groupname, String newgroupname) {
		boolean status = false;
		List<Account> accounts=accountrepo.findByGroupnameAndMaster(groupname, mastername);
		if(accounts.size()>0) {
			status=true;
			accounts.stream().forEach(account ->{ account.setGroupname(newgroupname);accountrepo.save(account);});
		}
		else {
			throw new GroupNotFoundException("Group not exists");
		}
		return status;
	}
	
	public boolean deleteByGroupAccount(String group) {
		boolean status = false;
			List<Account> accounts =accountrepo.findByGroupnameAndMaster(group, mastername); 
			if(accounts.size()>0) {
				status=true;
				accountrepo.deleteAll(accounts);
			}else {
				throw new GroupNotFoundException("Group not exists");
			}
		return status;

	}
}
