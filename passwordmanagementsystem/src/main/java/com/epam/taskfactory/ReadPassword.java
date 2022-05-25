package com.epam.taskfactory;

import com.epam.uiservices.ReadPasswordUI;

public class ReadPassword implements Task{
	@Override
	public void doTask() {

		ReadPasswordUI.readPassword();
		
	}
}
