package UIservices;

import TaskFactoryClasses.*;

public class TaskFactory {
	public Task createTask(int option)
    {
        
        if (1==option) {
            return new CreateAccount();
        }
        else if (2==option) {
            return new ReadPassword();
        }
        else if (4==option) {
            return new DeleteAccount();
        }
        else if (7==option) {
            return new DeleteGroup();
        }
        else if (3==option) {
            return new DisplaybyGroup();
        }
        else if (5==option) {
            return new ModifyAccount();
        }
        else if (6==option) {
            return new ModifyGroup();
        }
        return null;
    }
}
