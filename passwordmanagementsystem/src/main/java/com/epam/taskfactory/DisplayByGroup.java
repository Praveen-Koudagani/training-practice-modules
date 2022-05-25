package com.epam.taskfactory;

import com.epam.uiservices.ViewByGroupUI;

public class DisplayByGroup implements Task{
	@Override
	public void doTask() {
		ViewByGroupUI.display();
		
	}
}
