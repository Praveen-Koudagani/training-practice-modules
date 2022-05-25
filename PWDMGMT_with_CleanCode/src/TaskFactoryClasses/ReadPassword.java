package TaskFactoryClasses;

import UIservices.ReadPasswordUI;
import UIservices.Task;

public class ReadPassword implements Task{

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		ReadPasswordUI.readPassword();
		
	}

}
