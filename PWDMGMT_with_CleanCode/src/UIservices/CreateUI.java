package UIservices;

import java.util.Scanner;

import CRUD_Operations.CreateAccount;

public class CreateUI {
public static void createAccount() {
	
		Scanner sc=new Scanner(System.in);
        Validations v1=new Validations();
        String Password="";
	    String url1="",group="";
        group = groupCheck(sc, v1, group);
        url1 = urlCheck(sc, v1, url1, group);
        System.out.println("Enter User name");
        String Username=sc.next();
        Password = passwordCheck(sc, v1, Password);

        CreateAccount acc=new CreateAccount();
        String[] arg={url1,Password,Username,group};
 
        System.out.println(acc.create(arg));
  	//sc.close();

}

private static String passwordCheck(Scanner sc, Validations v1, String Password) {
	while(true) {
	   System.out.println("enter password");
	   Password=sc.next();
  
	   if(v1.validatePassword(Password)==0) {
		   System.err.println("Enter valid password of length>8");
		   continue;}
	   else
		   break;
	}
	return Password;
}

private static String urlCheck(Scanner sc, Validations v1, String url1, String group) {
	while(true) {
	   System.out.println("Enter UrL");
	   url1=sc.next();
	   if(v1.isValidUrl(url1)==0) {
		   System.err.println("Enter valid url");
		   continue;}
	   else if(v1.isAccountUnique(url1, group)==0) {
		   System.err.println("this account already present in group");
		   continue;
	   }else
		   break;}
	return url1;
}

private static String groupCheck(Scanner sc, Validations v1, String group) {
	while(true) {
	System.out.println("enter group");
	group=sc.next();
	if(v1.isgroupValid(group)==0) {
		System.err.println("Enter group name with alphabets");
		continue;}
	else
		break;
	}
	return group;
}
}
