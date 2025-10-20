package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;

@Service
public interface UserAccountService {
	
	List<UserAccount> getActiveUsersByLoginDateDesc() ;
	
	Optional<UserAccount> findByUserId(Integer userId);
}
