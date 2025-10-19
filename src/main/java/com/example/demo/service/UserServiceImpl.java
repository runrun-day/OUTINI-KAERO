package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

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

	@Override
	public UserAccount findUserByMail(String mail) {
		Optional<UserAccount> optUser = repository.findByMail(mail);

		UserAccount user;
		if (optUser.isPresent()) {
		    user = optUser.get();
		    
		    return user;
		} else {
		    throw new IllegalArgumentException("ユーザーが見つかりません: " + mail);
	}

	}
}
