package UIservices;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CRUD_Operations.Account;
import CRUD_Operations.CreateMaster;
import CRUD_Operations.Master;

public class Validations {
	public int validatePassword(String password) {
		if(password.length()>=8) {
			return 1;
		}
		return 0;
		
	}

	public int isAccountUnique(String url,String group) {
		Master masterobj=CreateMaster.getMasterobj();
		int condition=0;
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group)) {
			if(data.get(group).containsKey(url))
				condition=1;
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
