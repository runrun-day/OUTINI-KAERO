package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	
	@Override
	public boolean userSignUpAdd(UserAccount user) {	
		return repository.userSignUpAdd(user);
	}

	@Override
	public boolean userMailSearch(String mail) {
		return repository.userMailSearch(mail);
	}

}
