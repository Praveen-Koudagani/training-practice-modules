package com.epam.passwordmanagement;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class UnClosableInputStream extends ByteArrayInputStream{

	public UnClosableInputStream(byte[] buf) {
		super(buf);
		
	}
	@Override
	public void close() throws IOException{
		//do nothing
	}
	
	
}
