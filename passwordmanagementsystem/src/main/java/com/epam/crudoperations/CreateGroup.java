package com.epam.crudoperations;

import java.util.HashMap;

import com.epam.models.Account;
import com.epam.models.Master;


public class CreateGroup implements Create {
	@Override
	public String create(String[] request) {

		Master masterobj=CreateMaster.getMasterobj();
		String groupName=request[0];
		String status="Added";
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(groupName))
			status="Group Already Exists you can Add accounts";
		else
			data.put(groupName, new HashMap<>());
		return status;
	}
}
