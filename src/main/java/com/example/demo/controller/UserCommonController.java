package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.UserLoginFrom;
import com.example.demo.form.UserSignUpFrom;
import com.example.demo.repository.LostCatRepository;
import com.example.demo.service.UserLocationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserCommonController {

//   http://localhost:8080/user/user-login
	
	private final UserLocationService userLocationService;
    private final LostCatRepository lostCatRepository;
	
//	ユーザーログインへ
	@GetMapping("/user/user-login")
	public String userLogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model) {
		
		if (error != null) {
	        model.addAttribute("errorMsg", "メールアドレスまたはパスワードが違います。");
	    }

	    if (logout != null) {
	        model.addAttribute("msg", "ログアウトしました。");
	    }
		
		 model.addAttribute("userLoginFrom", new UserLoginFrom());
		return "user/user-login";
	}
	
//	ユーザー登録へ
	@GetMapping("/user/user-signup")
	public String userSignUp(Model model) {
	    model.addAttribute("userSignUpForm", new UserSignUpFrom());
	    return "user/user-signup"; 
	}
	
//	ユーザー登録へ 戻る処理用
	@PostMapping("/user/user-signup")
	public String userSignUp(@ModelAttribute UserSignUpFrom form,
			Model model) {
	    model.addAttribute("userSignUpForm", form);
	    return "user/user-signup"; 
	}
	
	@GetMapping("/user/user-home")
	public String userHome(Model model, Authentication authentication) {
	    String mail = authentication.getName();
	    model.addAttribute("mail", mail);
	    return "user/user-home";
	}
	
//	ログアウト
	@GetMapping("/user/user-logout-complete")
	public String logoutComplete() {
	    return "user/user-logout-complete"; 
	}
}

	
