package com.epam.securitydefence;


public class Security {
	public String encrypt(String pwd) {
		return AES.encrypt(pwd);
	}
	public String decrypt(String encryptedpwd ){
		 return AES.decrypt(encryptedpwd);
	}
}
