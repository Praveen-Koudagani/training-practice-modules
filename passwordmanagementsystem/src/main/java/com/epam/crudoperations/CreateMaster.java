package com.epam.crudoperations;

import com.epam.models.Master;

public class CreateMaster {
	//Data object -> master Dao
static Master masterobj;

public static Master getMasterobj() {
	return masterobj;
}

public static void createMasterobj() {
	Master master=new Master();
	CreateMaster.masterobj=master;
}
}
