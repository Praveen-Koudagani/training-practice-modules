package com.epam.crud_operations;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.dao.AuthGroupRepository;
import com.epam.dao.MasterRepository;
import com.epam.dto.UserPrincipal;
import com.epam.entities.AuthGroup;
import com.epam.entities.Master;

@Service
public class PMTAppUserDetailsService implements UserDetailsService {

	private final MasterRepository repository;
	
	private final AuthGroupRepository authGroupRepository;

	public PMTAppUserDetailsService(MasterRepository masterRepository,AuthGroupRepository authGroupRepository) {
		super();
		this.repository = masterRepository;
		this.authGroupRepository = authGroupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Master> user = repository.findById(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Cannot find username : " + username);
		}
		List<AuthGroup> authGroups =  authGroupRepository.findByUsername(username);
		
		return new UserPrincipal(user.get(),authGroups);
	}

}
