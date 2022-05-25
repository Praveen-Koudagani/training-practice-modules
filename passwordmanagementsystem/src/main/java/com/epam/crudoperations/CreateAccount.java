package com.epam.crudoperations;

import java.util.HashMap;

import com.epam.models.Account;
import com.epam.models.Master;


public class CreateAccount implements Create{
	@Override
	public String create(String[] request) {
		Master masterobj=CreateMaster.getMasterobj();
		String status="Success";
		String url=request[0];
		String password=request[1];
		String username=request[2];
		String group=request[3];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		Account acc1=new Account(url,username,password,group);
		if(data.containsKey(group)) {
			if( !data.get(group).containsKey(url) ) {
			data.get(group).put(url,acc1);}
			else
				status="Account already present in group";
		}
		else {
			HashMap<String,Account> obj=new HashMap<>();
			obj.put(url,acc1);
			data.put(group,obj);
		}
		return status;
	}
}
