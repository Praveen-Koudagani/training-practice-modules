package com.epam.taskfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.uiservices.ViewByGroupUI;
@Component
public class DisplayByGroup implements Task{
	@Autowired
	ViewByGroupUI view;
	@Override
	public void doTask() {
		view.display();
		
	}
}
