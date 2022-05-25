package CRUD_Operations;

public class CreateMaster {
static Master masterobj;

public static Master getMasterobj() {
	return masterobj;
}

public static void createMasterobj() {
	Master master=new Master();
	CreateMaster.masterobj=master;
}

}
