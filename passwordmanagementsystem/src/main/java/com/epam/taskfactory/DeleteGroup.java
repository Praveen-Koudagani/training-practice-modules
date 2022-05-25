package com.epam.taskfactory;

import com.epam.uiservices.DeleteUI;

public class DeleteGroup implements Task{
	@Override
	public void doTask() {

		DeleteUI.deleteGroup();
	}
}
