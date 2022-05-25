package com.epam.crudoperations;

import java.util.HashMap;

import com.epam.models.Account;
import com.epam.models.Master;



public class DeleteGroup implements Delete {
	@Override
	public String delete(String group) {
		String status="no such group";
		Master masterobj=CreateMaster.getMasterobj();
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group)) {
			data.remove(group);
			status="deleted group successfully";
		}
		return status;
	}
}
