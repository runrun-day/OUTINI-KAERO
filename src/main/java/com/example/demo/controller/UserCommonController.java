package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserLoginFrom;
import com.example.demo.form.UserSignUpFrom;

@Controller
public class UserCommonController {
	
//   http://localhost:8080/user/user-login
	
//	ユーザーログインへ
	@GetMapping("/user/user-login")
	private String userLogin(Model model) {
		 model.addAttribute("userForm", new UserLoginFrom());
		return "user/user-login";
	}
	
//	ユーザー登録へ
	@PostMapping("/user/user-signup")
	private String userSignUp(Model model) {
		model.addAttribute("userSignUpForm", new UserSignUpFrom());
		return "/user/user-signup";
	}
	
//	ユーザーホームへ
	@PostMapping("/user/user-home")
	private String userHome() {
		return "user/user-home";
	}
}

	
