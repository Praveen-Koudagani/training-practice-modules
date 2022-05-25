package com.epam.pwdmgmt;

import java.util.ArrayList;

public class DisplaybyGroup {
	public ArrayList<Account> getlistbygroup(ArrayList<Account> ad,String group){
		ArrayList<Account> acclist=new ArrayList<Account>();
		for(Account acc:ad) {
			   if(acc.getGroup().equals(group)) {
				   acclist.add(acc);
			   }
		   }
		
		return acclist;
}
}
