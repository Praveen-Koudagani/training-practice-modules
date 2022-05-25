package com.epam.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epam.crud_operations.AccountService;
import com.epam.crud_operations.GroupService;
import com.epam.dto.AccountDto;
import com.epam.entities.Account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
@RestController
@RequestMapping("/accounts")
@Api("Operations of PMT webapp.")
public class AccountController {
	private static final Logger logger=LogManager.getLogger(AccountController.class);
	@Autowired
	AccountService accountservice;
	@Autowired
	GroupService groupservice;
	@PostMapping("/createAccount2")
	@ApiOperation(value = "Adds new account.if exists already diplays message.", response = Map.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<Map<String, String>> addAccount(AccountDto account) {
		Map<String, String> map = new HashMap<>();
		String message = "account already exists";
			logger.info(account.getUsername(),account.getPassword());
			boolean status = accountservice.createAccount(account);
			if (status) {
				message = "account saved successfully";
				map.put("message", message);
				logger.info("account saved successfully..........");
			} else {
				map.put("message", message);
			}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping("/readPassword2")
	@ApiOperation(value = "View a Password of specific account by taking url as input", response = Map.class)
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<Map<String, String>> readPassword(String url) {
		Map<String, String> map = new HashMap<>();
			String password = accountservice.readPassword(url);
			if (!password.equals("not present")) {
				map.put("message","read successfully");
				map.put("password", password);
				logger.info(password);
			} else {
				map.put("message","account not exists");
				map.put("password", password);
			}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/viewAll2")
	@ApiOperation(value = "View a list of available accounts data.", response = List.class)
	@ApiResponses(value = {@ApiResponse(code=200,message = "successfully reterived the list.")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<List<Account>> viewAll() {
		List<Account> accounts = accountservice.viewAllAccounts();
		logger.info("all acts retrieved successfully");

		return  new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@PostMapping("/viewGroup2/{group}")
	@ApiOperation(value = "View a list of available accounts data in group.", response = Map.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Map<String,Object>> viewGroup(@PathVariable String group) {
		
		Map<String,Object> map = new HashMap<>();
			List<Account> accounts = groupservice.viewAccounts(group);
			if (accounts.size() > 0) {
				map.put("accounts", accounts);
				map.put("message","all accounts retrieved successfully");
				logger.info("all acts retrieved successfully");
			}
			else {
				map.put("message","no account found");
			}

		
			return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/DeleteGroup2/{group}")
	@ApiOperation(value = "Deletes a list of accounts data in group.", response = Map.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<Map<String, Object>> deleteGroup(@PathVariable String group) {
		Map<String, Object> map = new HashMap<>();
		String message = "Group not exists";
			boolean status = groupservice.deleteByGroupAccount(group);
			if (status) {
				logger.info("all  deleted successfully");
				message ="deleted successfully";
				map.put("message", message);
			} else {
				map.put("message", message);
			}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/DeleteAccount2")
	@ApiOperation(value = "deletes a account of specified url.", response = Map.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<Map<String, Object>> deleteAccount(String url) {
		Map<String, Object> map = new HashMap<>();
		String message = "Account not exists";
		
			boolean status = accountservice.deleteAccount(url);
			if (status) {
				logger.info("deleted successfully");
				message = "deleted successfully";
				map.put("message", message);
			} else {
				map.put("message", message);
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
	}




	@PutMapping("/updateGroup2/{group}/{newgroup}")
	@ApiOperation(value = "updates group name to new groupname.", response = Map.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Map<String, Object>> updateGroup(@PathVariable String group,@PathVariable String newgroup) {
		Map<String, Object> map = new HashMap<>();
		String message = "group not exists";
		
			boolean status = groupservice.updateGroupName(group, newgroup);
			if (status) {
				logger.info("group updated  successfully");
				message = "group updated  successfully";
			
				map.put("message", message);
			} else {
			
				map.put("message", message);
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping("/updateUserName2")
	@ApiOperation(value = "updates username to newname of specified account.", response = Map.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Map<String, Object>> updateUserName(String url, String newname) {
		Map<String, Object> map = new HashMap<>();
		String message = "Account not exists";
		
			boolean status = accountservice.updateAccountUsername(url, newname);
			if (status) {
				logger.info("name updated  successfully");
				message = "name updated  successfully";
				
				map.put("message", message);
			} else {
				
				map.put("message", message);
			}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping("/updatePassword2")
	@ApiOperation(value = "updates password of account specified by url.", response = Map.class)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  ResponseEntity<Map<String, Object>> updatePassword( String url,String newpassword) {
		Map<String, Object> map = new HashMap<>();
		String message = "Account not exists";
		
			boolean status = accountservice.updateAccountPassword(url, newpassword);
			if (status) {
				logger.info("password updated  successfully");
				message = "password updated  successfully";
				
				map.put("message", message);
			} else {
				
				map.put("message", message);
			}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
