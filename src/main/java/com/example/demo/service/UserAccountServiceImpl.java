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

	@Override
	public UserAccount deleteUser(Integer userId) {
		Optional<UserAccount> user = repository.findByUserId(userId);
		UserAccount u;
		if (user.isPresent()) {
		    u = user.get();
		    u.setUserDeleteFlag(true);
		    return repository.save(u);
		} else {
		    throw new IllegalArgumentException("ユーザーが見つかりません: " + userId);
		}
	}

}
