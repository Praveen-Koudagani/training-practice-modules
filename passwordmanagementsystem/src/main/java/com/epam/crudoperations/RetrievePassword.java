package com.epam.crudoperations;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.models.Account;
import com.epam.models.Master;


public class RetrievePassword implements Retrieve {
	@Override
	public String retrieve(String[] request) {
		
		Master masterobj=CreateMaster.getMasterobj();
		String group=request[0];
		String url=request[1];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		
		/*
		 * if(data.containsKey(group)) { if(data.get(group).containsKey(url)) { Account
		 * acc=data.get(group).get(url); status= acc.getDecryptedPassword(); } }
		 */
	    String password;
	    try {
		Optional<List<String>> result=Optional.ofNullable(data.entrySet().stream().filter(i->i.getKey().equals(group))
			 .map(k->k.getValue().get(url).getDecryptedPassword()).collect(Collectors.toList()));
		
        password=result.get().get(0);}
		catch(IndexOutOfBoundsException | NullPointerException e) {
			password="no account";
		}
        return password;
	}
	public String retrieveDecrypted(String[] request) {
		
		Master masterobj=CreateMaster.getMasterobj();
		String group=request[0];
		String url=request[1];
		String status="No account present in groups";
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group)) {
			if(data.get(group).containsKey(url)) {
				Account acc=data.get(group).get(url);
				status= acc.getDecryptedPassword();
			}
		}
		return status;
	}

}
