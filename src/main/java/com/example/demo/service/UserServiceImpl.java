package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public boolean userSignUpAdd(UserAccount user) {	
		return repository.userSignUpAdd(user);
	}

	@Override
	public boolean userMailSearch(String mail) {
		return repository.userMailSearch(mail);
	}

	@Override
	public UserAccount userAccountSearch(String mail, String Password) {
		
		 UserAccount u = repository.userAccountSearch(mail,passwordEncoder.encode(Password));
		 
		 if (u == null) {
	            return null; // 失敗
	        }
            return u; // 認証成功

	}


}
