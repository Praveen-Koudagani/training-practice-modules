package com.epam.pwdmgmt;
import java.util.ArrayList;

public class ReadPassword
{
   public String getPassword(ArrayList<Account> ad,String accounturl,String accusername){
	   for(Account acc:ad) {
		   if(acc.getUrl().equals(accounturl) && acc.getUsername().equals(accusername)) {
			   return acc.getPassword();
		   }
	   }
	   return "No such Account";
   }
   public String getDecryptedPassword(ArrayList<Account> ad,String accounturl,String accusername){
	   for(Account acc:ad) {
		   if(acc.getUrl().equals(accounturl) && acc.getUsername().equals(accusername)) {
			   return acc.getDecryptedPassword();
		   }
	   }
	   return "No such Account";
   }
}
