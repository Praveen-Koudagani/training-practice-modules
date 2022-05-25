package com.epam.taskfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.uiservices.CreateUI;
@Component
public class CreateAccount implements Task{
	@Autowired
	CreateUI createui;
	@Override
	public void doTask() {
		createui.addAccount();
		
	}

}
