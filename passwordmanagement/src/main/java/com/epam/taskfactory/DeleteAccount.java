package com.epam.taskfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.uiservices.DeleteUI;
@Component
public class DeleteAccount implements Task {
	@Autowired
	DeleteUI deleteui;
	@Override
	public void doTask() {

		deleteui.deleteAccount();
	}
}
