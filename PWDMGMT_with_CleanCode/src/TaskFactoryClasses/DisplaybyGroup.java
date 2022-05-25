package TaskFactoryClasses;

import UIservices.DisplayByGroupUI;
import UIservices.Task;

public class DisplaybyGroup implements Task {

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		DisplayByGroupUI.display();
		
	}

}
