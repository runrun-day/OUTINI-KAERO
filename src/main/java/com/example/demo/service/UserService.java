package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;

@Service
public interface UserService {

	//ユーザー登録
	boolean userSignUpAdd(UserAccount user);
	
	//メールアドレスの重複確認
	boolean userMailSearch(String mail);
	
	UserAccount findUserByMail(String mail);

	
	
}
