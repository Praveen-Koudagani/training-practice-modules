package TaskFactoryClasses;

import UIservices.DeleteUI;
import UIservices.Task;

public class DeleteGroup implements Task{

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		DeleteUI.deleteGroup();
	}

}
