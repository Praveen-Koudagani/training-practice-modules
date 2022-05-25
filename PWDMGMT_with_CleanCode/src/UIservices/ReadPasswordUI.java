package UIservices;

import java.util.Scanner;

import CRUD_Operations.retrievePassword;

public class ReadPasswordUI {
public static void readPassword() {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter account url");
	  String Accounturl=sc.next();
	  System.out.println("enter group");
	  String group5=sc.next();
	  retrievePassword rd=new retrievePassword();
	  String[] arg= {group5,Accounturl};
	  String temp=rd.retrieve(arg);
	  readPasswordOptions(sc, rd, arg, temp);
	  //sc.close();
}

private static void readPasswordOptions(Scanner sc, retrievePassword rd, String[] arg, String temp) {
	if(!temp.equals("No account present in groups")) {
		  System.out.println("The password is:"+temp);
		  System.out.println("enter option");
		  System.out.println("1.View decrypted password");
		  System.out.println("2.no need");
		  int n=sc.nextInt();
		  if(n==1)
		  {
			  String temp2=rd.retrieveDecrypted(arg);
			  System.out.println("Decrypted password:"+temp2);
		  }
	  }
	  else
		  System.err.println("no account present");
}
}
