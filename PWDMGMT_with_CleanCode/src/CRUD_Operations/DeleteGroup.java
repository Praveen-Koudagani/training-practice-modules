package CRUD_Operations;

import java.util.HashMap;

public class DeleteGroup implements Delete {

	@Override
	public String delete(String inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String group=inp;
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		if(data.containsKey(group)) {
			data.remove(group);
			return "deleted group successfully";
		}
		return "no such group";
	}

}
