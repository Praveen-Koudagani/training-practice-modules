package com.epam.taskfactorytest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.epam.taskfactory.*;
import com.epam.taskfactory.TaskFactory;

 class TaskFactoryTest {
	TaskFactory taskfactory;
	@BeforeEach
	public void setUp() {
		taskfactory=new TaskFactory();
	}
	@Test
	void testForOption1() {
	assertTrue(taskfactory.createTask(1) instanceof CreateAccount );
	}
	@Test
	void testForOption2() {
	assertTrue(taskfactory.createTask(2) instanceof ReadPassword );

	}
	@Test
	void testForOption3() {
	assertTrue(taskfactory.createTask(3) instanceof DisplayByGroup );

	}
	@Test
	void testForOption4() {
	assertTrue(taskfactory.createTask(4) instanceof DeleteAccount );

	}
	@Test
	void testForOption5() {
	assertTrue(taskfactory.createTask(5) instanceof ModifyAccount );

	}
	@Test
	void testForOption6() {
	assertTrue(taskfactory.createTask(6) instanceof ModifyGroup );

	}
	@Test
	void testForOption7() {
	assertTrue(taskfactory.createTask(7) instanceof DeleteGroup );

	}
	@Test
	void testForOption8() {
		String message="";
		try {
			assertFalse(taskfactory.createTask(10) instanceof DeleteGroup );
		}catch(NoSuchElementException nse) {
			message=nse.getMessage();
		}
	assertTrue(message.contains("No value present"));

	}

}
