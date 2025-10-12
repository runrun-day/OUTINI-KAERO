package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserSignUpFrom;

@Controller
public class UserSignUpController {

//	ユーザー登録へ
	@PostMapping("/user/user-signup-confirm")
	private String userSignUp(Model model) {
		model.addAttribute("userSignUpForm", new UserSignUpFrom());
		return "/user/user-signup-confirm";
	}
}
