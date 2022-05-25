package com.epam.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
@Service
public class BCryptService {
	// private static final Logger log = LogManager.getLogger(BCryptService.class);

	    private int logRounds=4;
		public String hash(String password) {
	        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
	    }

	    public boolean verifyHash(String password, String hash) {
	        return BCrypt.checkpw(password, hash);
	    }
	    
}
