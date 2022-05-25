package com.epam.crud_operations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.dao.MasterRepository;
import com.epam.entities.Master;
@ExtendWith(MockitoExtension.class)
class MasterOperationsTest {

	@Mock
	MasterRepository masterrepo;
	@InjectMocks
	
	MasterOperations masterservice;

	Master master;
	@BeforeEach
	public void setUp() {
		master=new Master();
		master.setUsername("Ravi");
		master.setPassword("Ravi@1234");
	}
	@Test
	void testCreateMaster() {
		when(masterrepo.findById("praveen")).thenReturn(Optional.ofNullable(null));
		when(masterrepo.findById("Ravi")).thenReturn(Optional.ofNullable(master));
		assertTrue(masterservice.createMaster( "praveen", "Pavi@1234"));
		assertFalse(masterservice.createMaster("Ravi","Ravi@1234"));
	}

	@Test
	void testIsMasterPresent() {
		when(masterrepo.findById("Ravi")).thenReturn(Optional.ofNullable(master));
		when(masterrepo.findById("praveen")).thenReturn(Optional.ofNullable(null));
		assertFalse(masterservice.isMasterPresent( "praveen","Pavi@1234"));
		assertTrue(masterservice.isMasterPresent( "Ravi","Ravi@1234"));
	}

}
