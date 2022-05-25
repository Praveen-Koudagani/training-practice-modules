package CRUD_Operations;

import java.util.HashMap;

public class ModifyAccountPassword implements Update{

	@Override
	public String update(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String url=inp[0];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		for(String grp1:data.keySet()) {
			HashMap<String,Account> acc=data.get(grp1);
			if(acc.containsKey(url)) {
				acc.get(url).setPassword(inp[1]);
				return "Modified Successfully";
			}
			
		}
		return "no such Account";
	}
	public String updateURL(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String url=inp[0];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		for(String grp1:data.keySet()) {
			HashMap<String,Account> acc=data.get(grp1);
			if(acc.containsKey(url)) {
				acc.get(url).setUrl(inp[1]);
				Account account=acc.get(url);
				acc.put(inp[1],account);
				acc.remove(url);
				
				return "Modified Successfully";
			}
			
		}
		return "no such Account";
	}

}
