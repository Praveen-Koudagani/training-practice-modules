package com.epam.taskfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.uiservices.UpdateUI;
@Component
public class ModifyGroup implements Task{
	@Autowired
	UpdateUI updateui;
	
	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		updateui.modifyGroup();
	}
}
