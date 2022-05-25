package CRUD_Operations;

import java.util.HashMap;

public class retrievePassword implements Retrieval{

	@Override
	public String retrieve(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String group=inp[0];
		String url=inp[1];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		//System.out.println(data.toString());
		if(data.containsKey(group)) {
			if(data.get(group).containsKey(url)) {
				Account acc=data.get(group).get(url);
				//System.out.println("inner if");
				return acc.getPassword();
			}
		}
		return "No account present in groups";
	}
	public String retrieveDecrypted(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String group=inp[0];
		String url=inp[1];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group)) {
			if(data.get(group).containsKey(url)) {
				Account acc=data.get(group).get(url);
				return acc.getDecryptedPassword(acc.getPassword());
			}
		}
		return "No account present in groups";
	}

}
