package com.epam.taskfactory;

import com.epam.uiservices.CreateUI;

public class CreateAccount implements Task{
	@Override
	public void doTask() {
		CreateUI.addAccount();
		
	}

}
