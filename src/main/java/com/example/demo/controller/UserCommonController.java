package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCommonController {
	
//   http://localhost:8080/user/user-login
	
//	ユーザーログインへ
	@GetMapping("/user/user-login")
	private String userLogin() {
		return "user/user-login";
	}
}

