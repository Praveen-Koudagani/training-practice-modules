package com.epam.pwdmgmt;

import java.util.ArrayList;

public class ModifyPasswordAndAccount {
	public String modifyUrl(ArrayList<Account> ad,String url,String newurl) {
		int condition=0;
		for(Account acc:ad) {
			   if(acc.getUrl().equals(url)) {
				   acc.setUrl(newurl);
				   condition=1;
			   }
		   }
		if(condition==1)
			return "Modified successfully";
		
		return "No such Account";
}
	public String modifyPassword(ArrayList<Account> ad,String url,String newpwd) {
		int condition=0;
		for(Account acc:ad) {
			   if(acc.getUrl().equals(url)) {
				   acc.setPassword(newpwd);
				   condition=1;
			   }
		   }
		if(condition==1)
			return "Password Modified successfully";
		
		return "No such Account";
}

}
