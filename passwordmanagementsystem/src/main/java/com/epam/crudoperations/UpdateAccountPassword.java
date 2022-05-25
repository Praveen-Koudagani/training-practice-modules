package com.epam.crudoperations;

import java.util.HashMap;

import com.epam.models.Account;
import com.epam.models.Master;



public class UpdateAccountPassword implements Update{
	@Override
	public String update(String[] request) {
		String status="no such Account";
		Master masterobj=CreateMaster.getMasterobj();
		String url=request[0];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		for(String grp1:data.keySet()) {
			HashMap<String,Account> acc=data.get(grp1);
			if(acc.containsKey(url)) {
				acc.get(url).setPassword(request[1]);
				status="Modified Successfully";
			}
			
		}
		return status;
	}
	public String updateURL(String[] request) {
		String status="no such Account";
		Master masterobj=CreateMaster.getMasterobj();
		String url=request[0];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		for(String grp1:data.keySet()) {
			HashMap<String,Account> acc=data.get(grp1);
			if(acc.containsKey(url)) {
				acc.get(url).setUrl(request[1]);
				Account account=acc.get(url);
				acc.put(request[1],account);
				acc.remove(url);
				
				status="Modified Successfully";
			}
			
		}
		return status;
	}

}
