package com.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.crud_operations.MasterOperations;
import com.epam.crud_operations.MasterProvider;
import com.epam.entities.Master;

@Controller
@RequestMapping
public class PMTController {
	@Autowired
	MasterOperations masterservice;

	@RequestMapping("/")
	public String loginAndRegister() {
		return "index";
	}

	@RequestMapping("/taskmenu")
	public String taskmenu() {
		return "taskmenu";
	}

	@RequestMapping("/addMaster")
	public String loginAndRegister2() {
		return "register";
	}

	@PostMapping("/addMaster2")
	public ModelAndView createMaster(String username,String password) {
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println(username + "----------------------------------" +password);
			boolean status = masterservice.createMaster(username, password);
			if (status) {
				System.out.println("Master saved successfully..........");
				mv.setViewName("registersuccess");
				mv.addObject("username", username);
				mv.addObject("password",password);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}

	@PostMapping("/MasterLogin")
	public ModelAndView masterLogin(String username,String password) {
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println(username + "----------------------------------" +password);
			boolean status = masterservice.isMasterPresent(username, password);
			if (status) {
				System.out.println("Master Logined successfully..........");
				MasterProvider.set(username, password);
				mv.setViewName("taskmenu");
				mv.addObject("username", username);
				mv.addObject("password",password);
			} else {
				System.out.println("Master Exists.........." + status);
				mv.setViewName("error");
			}
		} catch (Exception ex) {
			System.out.println("errorMessage" + ex.getMessage());
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
}
