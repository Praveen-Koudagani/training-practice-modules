package com.epam.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.crud_operations.MasterOperations;
@WebMvcTest(PMTController.class)
@ContextConfiguration(classes = {PMTController.class})
class PMTControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	MasterOperations masterservice;
	@Test
	void testLoginAndRegister() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	void testTaskmenu() throws Exception {
		mockMvc.perform(get("/taskmenu")).andExpect(status().isOk()).andExpect(view().name("taskmenu"));
	}

	@Test
	void testLoginAndRegister2() throws Exception {
		mockMvc.perform(get("/addMaster")).andExpect(status().isOk()).andExpect(view().name("register"));
	}

	@Test
	void testCreateMaster() throws Exception {
		when(masterservice.createMaster("pavi","Pavi@1234")).thenReturn(true);
		when(masterservice.createMaster(null,null)).thenThrow(new RuntimeException("error"));
		mockMvc.perform(post("/addMaster2?username=pavi&password=Pavi@1234").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("registersuccess"))
		;
		mockMvc.perform(post("/addMaster2?").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("error"))
		;
	
	}

	@Test
	void testMasterLogin() throws Exception {
		when(masterservice.isMasterPresent("pavi","Pavi@1234")).thenReturn(true);
		when(masterservice.isMasterPresent("Ravi","Ravi@1234")).thenReturn(false);
		when(masterservice.isMasterPresent(null,null)).thenThrow(new RuntimeException("error"));
		mockMvc.perform(post("/MasterLogin?username=pavi&password=Pavi@1234").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("taskmenu"))
		;
		mockMvc.perform(post("/MasterLogin?username=Ravi&password=Ravi@1234").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("error"))
		;
		mockMvc.perform(post("/MasterLogin?").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk()).andExpect(view().name("error"))
		;
		
	}

}
