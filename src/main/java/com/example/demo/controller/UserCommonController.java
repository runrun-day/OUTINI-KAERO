package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.LostCat;
import com.example.demo.form.UserLoginFrom;
import com.example.demo.form.UserSignUpFrom;

@Controller
public class UserCommonController {

//   http://localhost:8080/user/user-login
	
	
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
	    model.addAttribute("UserSearchForm", new UserSignUpFrom());
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
	public String userHome(HttpSession session, Model model, Authentication authentication) {
	    String mail = authentication.getName();
	    
//	    カスタム挙動Handlerで保存したセッション呼び出し
	    List<LostCat> cats = (List<LostCat>) session.getAttribute("cats");
	    model.addAttribute("cats", cats); 
	    
	    model.addAttribute("mail", mail);
	    return "user/user-home";
	}
	
//	ログアウト
	@GetMapping("/user/user-logout-complete")
	public String logoutComplete() {
	    return "user/user-logout-complete"; 
	}
}

	
