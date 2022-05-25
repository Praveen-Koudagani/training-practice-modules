package com.epam.pwdmgmt;
import java.util.ArrayList;


public class AccountsData {
private	ArrayList<Account> Acclist=new ArrayList<Account>();

public ArrayList<Account> getAcclist() {
	return Acclist;
}

public void setAcclist(ArrayList<Account> acclist) {
	Acclist = acclist;
}
public void add(Account obj) {
	this.Acclist.add(obj);
}
   
}
