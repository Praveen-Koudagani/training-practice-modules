package TaskFactoryClasses;

import UIservices.CreateUI;
import UIservices.Task;

public class CreateAccount implements Task {

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		CreateUI.createAccount();
		
	}

}
