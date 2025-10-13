package com.example.demo.controller;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserAccount;
import com.example.demo.form.UserLoginFrom;
import com.example.demo.form.UserSignUpFrom;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserCommonController {
	
	private final UserService service;
	
//   http://localhost:8080/user/login
	
//	ユーザーログインへ
	@GetMapping("/user/user-login")
	public String userLogin(Model model) {
		 model.addAttribute("UserLoginFrom", new UserLoginFrom());
		return "user/user-login";
	}
	
//	ユーザー登録へ
	@GetMapping("/user/user-signup")
	public String userSignUp(Model model) {
	    model.addAttribute("userSignUpForm", new UserSignUpFrom());
	    return "user/user-signup"; 
	}
	
//	ユーザー登録へ
	@PostMapping("/user/user-signup")
	public String userSignUp(@ModelAttribute UserSignUpFrom form,
			Model model) {
	    model.addAttribute("userSignUpForm", form);
	    return "user/user-signup"; 
	}
	
//	ユーザーホームへ
//	ログインできない事象あり
	@PostMapping("/user/user-home")
	public String userHome(@ModelAttribute UserLoginFrom form,
			Model model) {
		String mail = form.getMail();
		String pass =form.getPassword();
		UserAccount account = service.userAccountSearch(mail,pass);
		
		if (account == null) {
            model.addAttribute("errorMsg", "メールアドレスまたはパスワードが違います。");
            return "user/user-login";
        }
		 // Spring Security に認証情報を登録
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                		account.getMail(), null,
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                );

        SecurityContextHolder.getContext().setAuthentication(authToken);
		
		return "user/user-home";
	}
}

	
