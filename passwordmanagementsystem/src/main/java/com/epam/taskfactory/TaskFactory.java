package com.epam.taskfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class TaskFactory {
	public Task createTask(int option)
    {
        Map<Integer, Task> taskobjects=new HashMap<>();
        taskobjects.put(1,new CreateAccount());
        taskobjects.put(2,new ReadPassword());
        taskobjects.put(3,new DisplayByGroup());
        taskobjects.put(4,new DeleteAccount());
        taskobjects.put(5,new ModifyAccount());
        taskobjects.put(6,new ModifyGroup());
        taskobjects.put(7,new DeleteGroup());
        Optional<Task> op=Optional.ofNullable(taskobjects.get(option));
        return op.orElseThrow();

    }
}
