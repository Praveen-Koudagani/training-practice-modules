package com.epam.crudoperations;

import java.util.HashMap;

import com.epam.models.Account;
import com.epam.models.Master;


public class UpdateGroupDetails implements Update{
	@Override
	public String update(String[] request) {
		
		Master masterobj=CreateMaster.getMasterobj();
		String group1=request[0];
		String newgroupname=request[1];
		String status="No such Group";
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group1)) {
			data.put(newgroupname,data.get(group1));
			data.remove(group1);
			status="Modified";
		}
		return status;
	}
}
