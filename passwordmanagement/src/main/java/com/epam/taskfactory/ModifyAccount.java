package com.epam.taskfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.uiservices.UpdateUI;
@Component
public class ModifyAccount implements Task{
	@Autowired
	UpdateUI updateui;
	
	@Override
	public void doTask() {

		updateui.modifyAccountPasseord();
	}
}
