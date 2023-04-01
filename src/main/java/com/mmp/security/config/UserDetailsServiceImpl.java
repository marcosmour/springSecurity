package com.mmp.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmp.security.model.UserModel;
import com.mmp.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado" + userName));
		return userModel;
	}

}
