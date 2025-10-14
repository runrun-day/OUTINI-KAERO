package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		UserAccount account = userRepository.findByMail(mail)
	            .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + mail));

	        return org.springframework.security.core.userdetails.User
	            .withUsername(account.getMail())
	            .password(account.getPassword())
	            .roles("USER")
	            .build();
	    }

}
