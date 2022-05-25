package UIservices;

import java.util.Scanner;

import CRUD_Operations.CreateMaster;
import CRUD_Operations.Master;

public class RegisterAndLoginMenu {
public static void registerAsMaster() {
	Master master;
	Scanner sc=new Scanner(System.in);
	Validations v1=new Validations();
	RegisterMaster.register();
	master=CreateMaster.getMasterobj();
	String Passwrd = getMasterRegistrationDetails(master, sc, v1);
	
	master.setPassword(Passwrd);
	System.out.println("----Registered successfully---");	
}
private static String getMasterRegistrationDetails(Master master, Scanner sc, Validations v1) {
	System.out.println("Enter Username:");
	String username1=sc.next();
	master.setUsername(username1);
	String Passwrd="";
	Passwrd = passwordCheck(sc, v1, Passwrd);
	return Passwrd;
}
private static String passwordCheck(Scanner sc, Validations v1, String Passwrd) {
	while(true) {
         System.out.println("enter password");
         Passwrd=sc.next();
         
        	 if(v1.validatePassword(Passwrd)==0){
        		 System.err.println("Enter valid password with min 8 char");
             continue;}
         else
        	 break;
         }
	return Passwrd;
}
public static void login() {
	Master master;
	Scanner sc=new Scanner(System.in);
	master=CreateMaster.getMasterobj();
	System.out.println("Enter MasterUserId :");
	String username2=sc.next();
	System.out.println("Enter MasterPassword");
	String password2=sc.next();

	if(username2.equals(master.getUsername())) {
	     if(password2.equals(master.getPassword())) {
	    	 System.out.println("-------Logined successfully--------");
	    	 System.out.println("");
	    	 
	    	 while(true) {	    	 
			displayCRUDOperations();
			
				int option2=sc.nextInt();
				if(option2!=8) {
				TaskFactory taskobj=new TaskFactory();
				Task task=taskobj.createTask(option2);
				task.doTask();}
				else
					break;
			
	    	 }
	    	 
	     }
	     else
				System.err.println("Inavalid Password");
	}
	else
		System.err.println("Invalid username");	
}
private static void displayCRUDOperations() {
	System.out.println("<=============Task Menu===========>");
	System.out.println("  Choose option");
	System.out.println("  1.Create Password for an account");
	System.out.println("  2.Read Password");
	System.out.println("  3.List Password account and Groups");
	System.out.println("  4.Delete Password account");
	System.out.println("  5.Modify/Update password account");
	System.out.println("  6.Modify Group details");
	System.out.println("  7.Delete Group");
	System.out.println("  8.Logout");
}
}
