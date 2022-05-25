package com.epam.crud_operations;

import com.epam.entities.Master;

public class MasterProvider {
	private MasterProvider() {
		
	}
static Master master=new Master();
public static void set(String name,String password) {
	
	master.setUsername(name);
	master.setPassword(password);
}
public static Master getMaster() {
	return master;
}
}
