package UIservices;

import java.util.Scanner;

import CRUD_Operations.ModifyAccountPassword;
import CRUD_Operations.ModifyGroup;

public class UpdateUI {
	public static void modifyGroup() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter old group name ");
		String group1=sc.next();
		System.out.println("Enter new group name ");
		String newgroup=sc.next();
		ModifyGroup mg=new ModifyGroup();
		String[] arg1= {group1,newgroup};
		System.out.println(mg.update(arg1));
		//sc.close();
	}
public static void modifyAccountPasseord() {
	Scanner sc=new Scanner(System.in);
	Validations v1=new Validations();
	while(true){
		System.out.println("----Choose option----");
		System.out.println("  1.update Password");
		System.out.println("  2.update AccountURL");
		System.out.println("  3.<-back");
		int option5=sc.nextInt();
		if(option5==1) {
			updatePasswordcall(sc, v1);     }		
		else if(option5==2) {	
			updateURLcall(sc, v1);
			}
	    else if(option5==3)
			break;

	}  
	//sc.close();
}
private static void updateURLcall(Scanner sc, Validations v1) {
	System.out.println("enter url");
	String url2=sc.next();
	String newurl="";
	newurl = urlCheck(sc, v1, newurl);
	ModifyAccountPassword map=new ModifyAccountPassword();
	String[] arg1= {url2,newurl};
	System.out.println(map.updateURL(arg1));
}
private static void updatePasswordcall(Scanner sc, Validations v1) {
	System.out.println("enter url");
	String url1=sc.next();
	String newpassword="";
	newpassword = passwordCheck(sc, v1, newpassword);
	ModifyAccountPassword map=new ModifyAccountPassword();
	String[] arg1= {url1,newpassword};
	System.out.println(map.update(arg1));
}
private static String urlCheck(Scanner sc, Validations v1, String newurl) {
	while(true) {
	  
		System.out.println("Enter new UrL");
		newurl=sc.next();
		if(v1.isValidUrl(newurl)==0) {
			System.err.println("Enter valid url");
			continue;}
		else
			break;
	}
	return newurl;
}
private static String passwordCheck(Scanner sc, Validations v1, String newpassword) {
	while(true) {
		System.out.println("enter new password");
		newpassword=sc.next();
	     
		if(v1.validatePassword(newpassword)==0) {
			System.err.println("Enter valid password");
			continue;}
		else
			break;
	}
	return newpassword;
}
}
