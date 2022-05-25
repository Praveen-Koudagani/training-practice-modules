package UIservices;

import CRUD_Operations.RetrievebyGroup;

public class DisplayByGroupUI {
public static void display() {
	String group1="";
 	//System.out.println("Enter group:");
	//String group1=sc.next();
 	RetrievebyGroup rg=new RetrievebyGroup();
 	String[] arg1= {group1};
 	rg.retrieve(arg1);
}
}
