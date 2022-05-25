package com.epam.crudoperations;

import java.util.HashMap;

import com.epam.models.Account;
import com.epam.models.Master;


public class DeleteAccount implements Delete {
	@Override
	public String delete(String url) {
		Master masterobj=CreateMaster.getMasterobj();
		String status="Deleted Successfully";
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		/*
		 * for(String grp1:data.keySet()) { HashMap<String,Account> acc=data.get(grp1);
		 * if(acc.containsKey(url)) { acc.remove(url); status="Deleted Successfully";
		 * break; }
		 * 
		 * }
		 */

		data.entrySet().stream().forEach(v->v.getValue().entrySet().removeIf(i->i.getKey().equals(url)));

	
		return status;
	}
}
