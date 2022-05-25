package com.epam.restcontrollers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epam.crud_operations.MasterOperations;

@RestController
@RequestMapping("/masters")
public class MasterController {
	@Autowired
	MasterOperations masterservice;
	private static final Logger logger = LogManager.getLogger(MasterController.class);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addMaster/{username}/{password}")
	public  ResponseEntity<Map<String, String>> createMaster(@PathVariable String username,@PathVariable String password) {
		Map<String, String> map = new HashMap<>();
			boolean status = masterservice.createMaster(username, password);
			if (status) {
				logger.info("Master saved successfully..........");
				map.put("message","Successfully added");
				map.put("username", username);
				map.put("password", password);
			}else {
				map.put("message","user exists");
			}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
