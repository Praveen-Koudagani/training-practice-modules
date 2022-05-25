package com.epam.pwdmgmt;

import java.util.ArrayList;

public class DeletePasswordAccount {
		public String deleteAccount(ArrayList<Account> ad,String url,AccountsData d){
			//ArrayList<Account> acclist=new ArrayList<Account>();
			for(Account acc:ad) {
				   if(acc.getUrl().equals(url)) {
					   ad.remove(acc);
					   d.setAcclist(ad);
					   return "Deleted successfully";
				   }
			   }
			
			return "No such Account";
	}
	

}
