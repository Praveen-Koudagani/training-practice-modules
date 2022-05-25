package CRUD_Operations;

import java.util.HashMap;

public class CreateGroup implements Create {

	@Override
	public String create(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String GroupName=inp[0];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(GroupName))
			return "Group Already Exists you can Add accounts";
		else
			data.put(GroupName, new HashMap<String,Account>());
		return "Added";
	}

}
