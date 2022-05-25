package CRUD_Operations;

import java.util.HashMap;

public class CreateAccount implements Create{

	@Override
	public String create(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		String group=inp[3];
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		Account acc1=new Account(inp[0],inp[2],inp[1],inp[3]);
		if(data.containsKey(group)) {
			if(!data.get(group).containsKey(inp[1]))
			data.get(group).put(inp[1],acc1);
			else
				return "Account already present in group";
		}
		else {
			HashMap<String,Account> obj=new HashMap<String,Account>();
			obj.put(inp[0],acc1);
			data.put(group,obj);
		}
		return "Success";
	}

}
