package CRUD_Operations;

import java.util.HashMap;

public class ModifyGroup implements Update{

	@Override
	public String update(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String group1=inp[0];
		String newgroupname=inp[1];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group1)) {
			data.put(newgroupname,data.get(group1));
			data.remove(group1);
			return "Modified";
		}
		return "No such Group";
	}

}
