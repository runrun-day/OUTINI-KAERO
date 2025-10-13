package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserAccount;

@Repository
public interface UserRepository {

	boolean userSignUpAdd(UserAccount user);
	
	boolean userMailSearch(String mail);
}
