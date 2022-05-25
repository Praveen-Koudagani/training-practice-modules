package com.epam.validations;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.crudoperations.CreateMaster;
import com.epam.models.Account;
import com.epam.models.Master;

public class Validations {
	public boolean validatePassword(String password) {
		boolean status=false;
		String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(password);
		if(m.matches()) {
			status=true;
		}
		return status;
	}

	public boolean isAccountUnique(String url,String group) {
		Master masterobj=CreateMaster.getMasterobj();
		boolean status=true;
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group)) {
			if(data.get(group).containsKey(url)) {
			status=false;}
		}	
		return status;
	}
	public boolean isgroupValid(String group) {
		boolean status=false;
		if(group.matches("^[a-zA-Z]*$"))
			status=true;
		return status;
	}
	public boolean isValidUrl(String url) {
		boolean status=false;
		String regex="((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\b([-a-zA-Z0-9@:%_.\\+~#?&//=]*)";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(url);
		if(m.matches()) {
			status=true;
		}
		return status;
	}
}
