package com.epam.pwdmgmt;

import java.util.ArrayList;

public class ModifyGroup {
	public String modifyGroupName(ArrayList<Account> ad,String group,String newgroup) {
		int condition=0;
		for(Account acc:ad) {
			   if(acc.getGroup().equals(group)) {
				   acc.setGroup(newgroup);
				   condition=1;
			   }
		   }
		if(condition==1)
			return "Modified successfully";
		
		return "No such group";
}
}
