package CRUD_Operations;

import java.util.HashMap;

public class RetrievebyGroup implements Retrieval {

	@Override
	public String retrieve(String[] inp) {
		// TODO Auto-generated method stub
		Master masterobj=CreateMaster.getMasterobj();
		HashMap<String,HashMap<String,Account>> data=masterobj.getData();
		for(String grp1:data.keySet()) {
			HashMap<String,Account> acc=data.get(grp1);
			for(String url1:acc.keySet()) {
				String username=acc.get(url1).getUsername();
				String password=acc.get(url1).getPassword();
				System.out.println(grp1+"  ||  "+url1+"  ||  "+password+"  ||  "+username);
				System.out.println("");
			}
			
		}
		return "No groups";
	}

}
