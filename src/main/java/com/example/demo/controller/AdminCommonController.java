package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.UserLoginFrom;

@Controller
public class AdminCommonController {

//	ユーザーログインへ
	@GetMapping("/admin/admin-login")
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
		return "admin/admin-login";
	}
	
	@GetMapping("/admin/admin-home")
	public String userHome(Model model, Authentication authentication) {
	    String mail = authentication.getName();

	    model.addAttribute("mail", mail);
	    return "admin/admin-home";
	}
	
//	ログアウト
	@GetMapping("/admin/admin-logout-complete")
	public String logoutComplete() {
	    return "admin/admin-logout-complete"; 
	}
}
