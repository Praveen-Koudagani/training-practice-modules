package UIservices;

import java.util.Scanner;

import CRUD_Operations.DeleteAccount;
import CRUD_Operations.DeleteGroup;

public class DeleteUI {
	public static void deleteGroup() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter group name");
		String group1=sc.next();
		DeleteGroup dg=new DeleteGroup();
		System.out.println(dg.delete(group1));
		//sc.close();
	}
public static void deleteAccount() {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter Accounturl to delete password account");
    String url=sc.next();
    DeleteAccount da=new DeleteAccount();
    System.out.println(da.delete(url));
    //sc.close();
}
}
