package com.epam.crudoperations;

import java.util.HashMap;
import java.util.Optional;

import com.epam.models.Account;
import com.epam.models.Master;


public class RetrieveByGroup {
	public HashMap<String,Account> retrieve(String group) {
		
		Master masterobj=CreateMaster.getMasterobj();
		/*
		 * HashMap<String,Account> groupdata; if(masterobj.getData().containsKey(group))
		 * { groupdata=masterobj.getData().get(group); }
		 */
		Optional<HashMap<String,Account>> op=Optional.ofNullable(masterobj.getData().get(group));
        return op.orElseThrow();
	}
}
