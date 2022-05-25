package ProjectMain;

import java.util.Scanner;
import UIservices.RegisterAndLoginMenu;


public class PWDMGMT_with_CleanCode {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		
		int option;
		while(true) {

					
			System.out.println("<-----------     Main Menu     --------------->    ");
			System.out.println("Enter number for Respective Operation");
			System.out.println("  1.Register as master User");
			System.out.println("  2.Login as Master User into Password Management system");
			System.out.println("  3.To exit Application");
			
			option=PWDMGMT_with_CleanCode.sc.nextInt();
			if(option==1){
					RegisterAndLoginMenu.registerAsMaster();
			}
			else if(option==2) {
				RegisterAndLoginMenu.login();
			}
			else if(option==3)
						break;
				
		}
			
		System.out.println("Exited Successfully from application");
		//sc.close();
		
}
}
