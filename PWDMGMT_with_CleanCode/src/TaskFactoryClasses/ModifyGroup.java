package TaskFactoryClasses;

import UIservices.Task;
import UIservices.UpdateUI;

public class ModifyGroup implements Task{

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		UpdateUI.modifyGroup();
	}

}
