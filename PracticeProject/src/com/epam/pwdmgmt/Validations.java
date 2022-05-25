package com.epam.pwdmgmt;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
public int validCreate(String url,String name,String group,String pwd,int len,ArrayList<Account> acclist) {
	if(validatePassword(pwd,len)==1 && isgroupValid(group)==1 && isAccountUnique(url, group, acclist)==1 && isValidUrl(url)==1)
		return 1;
	return 0;
}
public int validatePassword(String password, int len) {
	if(password.length()==len){
		return 1;
	}
	return 0;
	
}

public int isAccountUnique(String url,String group,ArrayList<Account> Acclist) {
	int condition=0;
	for(Account acc:Acclist) {
		   if(acc.getGroup().equals(group) && acc.getUrl().equals(url)) {
			   condition=1;
		   }
	   }
	if(condition==1)
		return 0;
	
	return 1;
}
public int isgroupValid(String group) {
	if((!group.equals("")&&(group!=null)&&(group.matches("^[a-zA-Z]*$"))))
		return 1;
	return 0;
}
public int isValidUrl(String url) {
	String regex="((http|https)://)(www.)?"+"[a-zA-Z0-9@:%._\\+~#?&//=]"+"{2,256}\\b([-a-zA-Z0-9@:%"+"._\\+~#?&//=]*)";
	Pattern p=Pattern.compile(regex);
	if(url==null) {
		return 0;
	}
	Matcher m=p.matcher(url);
	if(m.matches()) {
		return 1;
	}
	return 0;
}

}
