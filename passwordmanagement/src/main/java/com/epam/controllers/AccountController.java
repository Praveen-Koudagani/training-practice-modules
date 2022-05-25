package com.epam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.crud_operations.AccountService;
import com.epam.crud_operations.GroupService;
import com.epam.dto.AccountDto;
import com.epam.entities.Account;

@Controller
public class AccountController {

	@Autowired
	AccountService accountservice;
	@Autowired
	GroupService groupservice;

	@RequestMapping("/createAccount")
	public String addAccount() {
		return "createAccount";
	}
	@RequestMapping("/readPassword")
	public String readPassword() {
		return "readPassword";
	}
	@RequestMapping("/DeleteGroup")
	public String deleteGroup() {
		return "DeleteGroup";
	}
	@RequestMapping("/DeleteAccount")
	public String deleteAccount() {
		return "DeleteAccount";
	}
	@RequestMapping("/Delete")
	public String delete() {
		return "DeleteAccount";
	}
	@RequestMapping("/viewGroup")
	public String viewGroup() {
		return "groupInput";
	}
	@RequestMapping("/updateGroup")
	public String updateGroup() {
		return "updateGroupInput";
	}
	@RequestMapping("/updateUserName")
	public String updateUserName() {
		return "updateUsername";
	}

	@RequestMapping("/updatePassword")
	public String updatePassword() {
		return "updateAccountPassword";
	}
	
	
	@PostMapping("/createAccount2")
	public ModelAndView addAccount(AccountDto account) {
		ModelAndView mv = new ModelAndView();
		String message="account already exists";
		try {
			System.out.println(account.getUsername() + "----------------------------------" + account.getPassword());
			boolean status = accountservice.createAccount(account);
			if (status) {
				message="account saved successfully";
				mv.setViewName("success");
				mv.addObject("message",message);
				System.out.println("account saved successfully..........");
			}else {
				mv.setViewName("success");
				mv.addObject("message",message);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@PostMapping("/readPassword2")
	public ModelAndView readPassword(String url) {
		ModelAndView mv = new ModelAndView();
		try {
			String password = accountservice.readPassword(url);
			if (password!="") {
				mv.setViewName("success");
				mv.addObject("message",password);
				System.out.println("password retrieved successfully.........."+password);
			}
			else {
				mv.setViewName("success");
				mv.addObject("message",password);
			}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@GetMapping("/viewAll2")
	public ModelAndView viewAll() {
		ModelAndView mv = new ModelAndView();
		//try {
			List<Account> accounts = accountservice.viewAllAccounts();
			//if(accounts.isEmpty()) {
				System.out.println("all acts retrieved successfully");
		//}

			mv.setViewName("viewAll");
			mv.addObject("accounts", accounts);
		//} catch (Exception ex) {
		//	mv.addObject("errorMessage", ex.getMessage());
		//	mv.setViewName("error");
		//}
		return mv;
	}
	@PostMapping("/viewGroup2")
	public ModelAndView viewGroup(String group) {
		ModelAndView mv = new ModelAndView();
		try {
			List<Account> accounts = groupservice.viewAccounts(group);
			if(accounts.size()>0) {
				mv.setViewName("viewGroup");
				mv.addObject("accounts", accounts);
				System.out.println("all acts retrieved successfully");
			}

			
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@PostMapping("/DeleteGroup2")
	public ModelAndView deleteGroup(String group) {
		ModelAndView mv = new ModelAndView();
		String message="Group not exists";
		try {
			boolean status = groupservice.deleteByGroupAccount(group);
			if(status) {
				System.out.println("all  deleted successfully");
				message=group+" deleted successfully";
				mv.setViewName("success");
				mv.addObject("message",message);
			}else {
				mv.setViewName("success");
				mv.addObject("message", message);
			}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@PostMapping("/DeleteAccount2")
	public ModelAndView deleteAccount(String url) {
		ModelAndView mv = new ModelAndView();
		String message="Account not exists";
		try {
			boolean status = accountservice.deleteAccount(url);
			if(status) {
				System.out.println(" deleted successfully");
				message="deleted successfully";
				mv.setViewName("success");
				mv.addObject("message",message);
			}
			else {
			mv.setViewName("success");
			mv.addObject("message", message);}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@GetMapping("/Delete2")
	public ModelAndView deleteRow(String url) {
		ModelAndView mv = new ModelAndView();
		System.out.println("in delete"+url);
		try {
			boolean status = accountservice.deleteAccount(url);
			if(status) {
				System.out.println(" deleted successfully");
				mv.setViewName("viewAll");
				mv.addObject("accounts", accountservice.viewAllAccounts());
			}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@GetMapping("/DeleteFromGroup2")
	public ModelAndView deleteRowGroup(String url,String group) {
		ModelAndView mv = new ModelAndView();
		
		try {
			boolean status = accountservice.deleteAccount(url);
			if(status) {
				System.out.println(" deleted successfully");
				mv.setViewName("viewGroup");
				mv.addObject("accounts", groupservice.viewAccounts(group));
			}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	
	@PostMapping("/updateGroup2")
	public ModelAndView updateGroup(String group,String newgroup) {
		ModelAndView mv = new ModelAndView();
		String message="group not exists";
		try {
			boolean status = groupservice.updateGroupName(group, newgroup);
			if(status) {
				System.out.println("group updated  successfully");
				message="group updated  successfully";
				mv.setViewName("success");
				mv.addObject("message",message);
			}else {
				mv.setViewName("success");
				mv.addObject("message",message);
			}

			
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@PostMapping("/updateUserName2")
	public ModelAndView updateUserName(String url,String newname) {
		ModelAndView mv = new ModelAndView();
		String message="Account not exists";
		try {
			boolean status = accountservice.updateAccountUsername(url, newname);
			if(status) {
				System.out.println("name updated  successfully");
				message="name updated  successfully";
				mv.setViewName("success");
				mv.addObject("message",message);
			}else {
				mv.setViewName("success");
				mv.addObject("message",message);
			}

			
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@PostMapping("/updatePassword2")
	public ModelAndView updatePassword(String url,String newpassword) {
		ModelAndView mv = new ModelAndView();
		String message="Account not exists";
		try {
			boolean status = accountservice.updateAccountPassword(url, newpassword);
			if(status) {
				System.out.println("password updated  successfully");
				message="password updated  successfully";
				mv.setViewName("success");
				mv.addObject("message",message);
			}else {
				mv.setViewName("success");
				mv.addObject("message",message);
			}

			
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
}
