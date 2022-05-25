package CRUD_Operations;

import java.util.HashMap;

public class DeleteAccount implements Delete {

	@Override
	public String delete(String inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String url=inp;
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		for(String grp1:data.keySet()) {
			HashMap<String,Account> acc=data.get(grp1);
			if(acc.containsKey(url)) {
				acc.remove(url);
				return "Deleted Successfully";
			}
			
		}
		return "no such Account";
	}

}
