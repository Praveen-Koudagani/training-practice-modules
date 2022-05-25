package com.epam.taskfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.uiservices.ReadPasswordUI;
@Component
public class ReadPassword implements Task{
	@Autowired
	ReadPasswordUI readpasswordui;
	
	@Override
	public void doTask() {

		readpasswordui.readPassword();
		
	}
}
