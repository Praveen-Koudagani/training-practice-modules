
import java.util.Scanner;
import java.util.*;

import com.epam.pwdmgmt.*;
public class PasswordMG {

public static void main(String[] args) {
	
	Scanner sc=new Scanner(System.in);
	AccountsData data=new AccountsData();
	MasterUser master=new MasterUser();
	Validations v1=new Validations();
	while(true) {
		System.out.println("-----------      Menu     ---------------     ");
	System.out.println("Enter number for Respective Operation");
	System.out.println("  1.Register as master User");
	System.out.println("  2.Login as Master User into Password Management system");
	System.out.println("  3.To exit Application");
	int option=sc.nextInt();
	switch(option) {
	case 1:
		System.out.println("Enter Username:");
		String username1=sc.next();
		master.setUsername(username1);
		String Passwrd;
		while(true) {
	         System.out.println("Enter length of password");
	         int len1=sc.nextInt();
	         System.out.println("enter password");
	         Passwrd=sc.next();
	         
	        	 if(v1.validatePassword(Passwrd, len1)==0){
	        		 System.err.println("Enter valid password");
	             continue;}
	         else
	        	 break;
	         }
		
		master.setPassword(Passwrd);
		System.out.println("----Registered successfully---");
		System.out.println("");
		option=20;
		break;
	
	case 2:
		System.out.println("Enter MasterUserId :");
		String username2=sc.next();
		System.out.println("Enter MasterPassword");
		String password2=sc.next();
		System.out.println("username"+username2+"password");
		if(username2.equals(master.getUsername())) {
		     if(password2.equals(master.getPassword())) {
		    	 System.out.println("-------Logined successfully--------");
		    	 System.out.println("");
		    	 while(true) {
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
				
				
					int option2=sc.nextInt();
					switch(option2) {
					case 1:  {

						      String Password;
							String url1;
						      
						      System.out.println("enter group");
						      String group=sc.next();
						      while(true) {
						    	  ArrayList<Account> acclist=data.getAcclist();
						    	  
						      System.out.println("Enter UrL");
					         url1=sc.next();
					         if(v1.isValidUrl(url1)==0) {
					        	 System.err.println("Enter valid url");
					             continue;}
					         else if(v1.isAccountUnique(url1, group,acclist)==0) {
					        	 System.err.println("this account already present in group");
					        	 continue;
					         }else
					        	 break;}
					         System.out.println("Enter User name");
					         String Username=sc.next();
					         while(true) {
					         System.out.println("Enter length of password");
					         int len1=sc.nextInt();
					         System.out.println("enter password");
					         Password=sc.next();
					         
					        	 if(v1.validatePassword(Password, len1)==0) {
					        		 System.err.println("Enter valid password");
					             continue;}
					         else
					        	 break;
					         }
					         
                             
					         Account acc=new Account(url1,Username,Password,group);
					         data.add(acc);
					         System.out.println("Account added successfully");}
					         break;
					case 2:
						     System.out.println("enter account url");
						     String Accounturl=sc.next();
						     System.out.println("enter user name");
						     String AccountUsername=sc.next();
						     ReadPassword rd=new ReadPassword();
						     ArrayList<Account> acclist=data.getAcclist();
						     String temp=rd.getPassword(acclist, Accounturl, AccountUsername);
						     if(!temp.equals("")) {
						     System.out.println("The password is:"+temp);
						     System.out.println("enter option");
						     System.out.println("1.View decrypted password");
						     System.out.println("2.no need");
						     int n=sc.nextInt();
						     if(n==1)
						     {
						    	 String temp2=rd.getDecryptedPassword(acclist, Accounturl, AccountUsername);
						    	 System.out.println("Decrypted password:"+temp2);
						     }
						     }
						     else
						    	 System.err.println("no account present");
						     break;
					case 3:{
						     System.out.println("Enter group:");
						     String group1=sc.next();
						     ArrayList<Account> acclist1=data.getAcclist();
						     DisplaybyGroup dp=new DisplaybyGroup();
						     ArrayList<Account> reslist2=dp.getlistbygroup(acclist1, group1);
						     if(reslist2.size()>0)
						     for(Account acc2:reslist2) {
								   System.out.println(acc2.getPassword()+" "+acc2.getUrl()+" "+acc2.getGroup());
							   }
						     else
						    	 System.err.println("No group present with that name");
					}
					break;
					
					case 4:{
						     System.out.println("Enter Accounturl to delete password account");
						     String url=sc.next();
						     ArrayList<Account> acclist2=data.getAcclist();
						     DeletePasswordAccount dpa=new DeletePasswordAccount();
						     System.out.println(dpa.deleteAccount(acclist2, url,data));
						     
					}
					break;
					
					case 5:{while(true) {
						System.out.println("----Choose option----");
						System.out.println("  1.update Password");
						System.out.println("  2.update AccountURL");
						System.out.println("  3.<-back");
						int option5=sc.nextInt();
						switch(option5) {
						case 1:{
							   System.out.println("enter url");
						       String url1=sc.next();
						       String newpassword;
						       while(true) {
							         System.out.println("Enter length of password");
							         int len1=sc.nextInt();
							         System.out.println("enter new password");
							         newpassword=sc.next();
							         
							        	 if(v1.validatePassword(newpassword, len1)==0) {
							        		 System.err.println("Enter valid password");
							             continue;}
							         else
							        	 break;
							         }
						       ArrayList<Account> acclist2=data.getAcclist();
						       ModifyPasswordAndAccount mpa=new ModifyPasswordAndAccount();
						       System.out.println(mpa.modifyPassword(acclist2,url1, newpassword));
						       }break;
						       
							
						case 2:{System.out.println("enter url");
						Validations v=new Validations();
					       String url2=sc.next();
					       String newurl;
					       //System.out.println("Enter new url");
					       //String newurl=sc.next();
					       while(true) {
						    	  
						      System.out.println("Enter new UrL");
					         newurl=sc.next();
					         if(v.isValidUrl(newurl)==0) {
					        	 System.err.println("Enter valid url");
					             continue;}
                                  else
					        	 break;}
					       ArrayList<Account> acclist2=data.getAcclist();
					       ModifyPasswordAndAccount mpa=new ModifyPasswordAndAccount();
					       System.out.println(mpa.modifyUrl(acclist2, url2, newurl));}
						break;
						}
						if(option5==3)
							break;
					
					}  }
					break;
					
					case 6:{
						System.out.println("Enter old group name ");
						String group=sc.next();
						System.out.println("Enter new group name ");
						String newgroup=sc.next();
						ArrayList<Account> acclist2=data.getAcclist();
						ModifyGroup mg=new ModifyGroup();
						System.out.println(mg.modifyGroupName(acclist2, group, newgroup));
					}
					break;
					case 7:{
						System.out.println("Enter group name");
						String group=sc.next();
						ArrayList<Account> acclist2=data.getAcclist();
					     DeleteGroup dg=new DeleteGroup();
					     System.out.println(dg.deleteAccount(acclist2, group,data));
					     
						
					}
					break;
					
					
					}if(option2==8)break;
				}
			}
			else
				System.err.println("Inavalid Password");
		}
		else
			System.err.println("Invalid username");
		break;
		}
	
	
	if(option==3)
		break;
	
	}
	System.out.println("Exited Successfully from application");
	sc.close();
}
}

