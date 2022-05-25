package com.epam.taskfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskFactory {
	@Autowired
	CreateAccount createaccount;
	@Autowired
	 ReadPassword readpassword;
	@Autowired
	 DisplayByGroup displaybygroup;
	@Autowired
	 DeleteAccount deleteaccount;
	@Autowired
	 ModifyAccount modifyaccount;
	@Autowired
	 ModifyGroup  modifygroup;
	@Autowired
	 DeleteGroup deletegroup;
	public Task createTask(int option)
    {
		
        Map<Integer, Task> taskobjects=new HashMap<>();
        taskobjects.put(1,createaccount);
        taskobjects.put(2,readpassword);
        taskobjects.put(3,displaybygroup);
        taskobjects.put(4,deleteaccount);
        taskobjects.put(5,modifyaccount);
        taskobjects.put(6,modifygroup);
        taskobjects.put(7,deletegroup);
        Optional<Task> op=Optional.ofNullable(taskobjects.get(option));
        return op.orElseThrow();

    }
}
