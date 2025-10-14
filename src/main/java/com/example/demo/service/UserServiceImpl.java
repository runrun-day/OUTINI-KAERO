package com.example.demo.service;

import java.time.LocalDateTime;

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
		 try {
	            // パスワードをハッシュ化して保存
	            user.setPassword(passwordEncoder.encode(user.getPassword()));
	            user.setLoginDate(LocalDateTime.now()); // Java側でセット
	            repository.save(user); // INSERT
	            return true;
	        } catch (Exception e) {
	            System.err.println("Insert failed: " + e.getMessage());
	            return false;
	        }
	    }
	
	@Override
	public boolean userMailSearch(String mail) {
		return repository.existsByMail(mail);
	}

}
