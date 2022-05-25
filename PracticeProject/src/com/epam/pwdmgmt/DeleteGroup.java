package com.epam.pwdmgmt;

import java.util.ArrayList;
import java.util.Iterator;


public class DeleteGroup {
	public String deleteAccount(ArrayList<Account> ad,String group,AccountsData d) {
		int condition=0;
		Iterator<Account> itr=ad.iterator();
		while(itr.hasNext()) {
			Account acc=itr.next();
			   if(acc.getGroup().equals(group)) {
				   itr.remove();
				   condition=1;
				   
			   }
		   }
		if(condition==1) {
		d.setAcclist(ad);
		return "Deleted successfully";}
		else
		return "No such Group found";
}
}
