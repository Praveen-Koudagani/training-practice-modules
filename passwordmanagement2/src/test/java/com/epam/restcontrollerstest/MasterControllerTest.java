package com.epam.restcontrollerstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.epam.crud_operations.MasterOperations;
import com.epam.dto.AuthenticationRequest;
import com.epam.restcontrollers.MasterController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
 class MasterControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	MasterOperations masterservice;
	@Autowired
	private WebApplicationContext context;

	private String token;

	@Autowired
	private ObjectMapper mapper;

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@BeforeEach
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		String username = "praveen";
		String password = "Pavi@1234";

		AuthenticationRequest request = new AuthenticationRequest();
		request.setUsername(username);
		request.setPassword(password);

		MvcResult result = mockMvc.perform(post("/login").content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		token = response.substring(8, response.length() - 2);
		System.out.println("token " + token);
	}

	@Test
	void testCreateMaster() throws Exception {
		when(masterservice.createMaster("pavi", "Pavi@1234")).thenReturn(true);
		when(masterservice.createMaster("shyam", "Shyam@1234")).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/masters/addMaster/pavi/Pavi@1234")
						.header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		System.out.println(urlres);
		HashMap<String, String> data = this.mapFromJson(urlres, HashMap.class);
		assertEquals("pavi", data.get("username"));
		mockMvc.perform(post("/masters/addMaster/shyam/Shyam@1234")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());

	}

	/*
	 * @Test void testMasterLogin() throws Exception {
	 * when(masterservice.isMasterPresent("pavi","Pavi@1234")).thenReturn(true);
	 * when(masterservice.isMasterPresent("Ravi","Ravi@1234")).thenReturn(false);
	 * MvcResult result=mockMvc.perform(post(
	 * "/masters/MasterLogin?username=pavi&password=Pavi@1234")
	 * .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk(
	 * )).andReturn(); String urlres=result.getResponse().getContentAsString();
	 * System.out.println(urlres); HashMap<String,String>
	 * data=this.mapFromJson(urlres,HashMap.class);
	 * assertEquals("pavi",data.get("username"));
	 * mockMvc.perform(post("/masters/MasterLogin?username=Ravi&password=Ravi@1234")
	 * .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk(
	 * ));
	 * 
	 * }
	 */
}
