package com.epam.taskfactory;

import com.epam.uiservices.UpdateUI;

public class ModifyAccount implements Task{
	@Override
	public void doTask() {

		UpdateUI.modifyAccountPasseord();
	}
}
