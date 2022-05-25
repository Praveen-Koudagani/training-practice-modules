package com.epam.taskfactory;

import com.epam.uiservices.DeleteUI;

public class DeleteAccount implements Task {
	@Override
	public void doTask() {

		DeleteUI.deleteAccount();
	}
}
