package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	 private final UserRepository repository;
	
	@Override
	public List<UserAccount> getActiveUsersByLoginDateDesc() {
		
		return repository.findByUserDeleteFlagFalseAndUserIdNotOrderByLoginDateDesc(1);
	}

	@Override
	public Optional<UserAccount> findByUserId(Integer userId) {

		return repository.findByUserId(userId);
	}

}
