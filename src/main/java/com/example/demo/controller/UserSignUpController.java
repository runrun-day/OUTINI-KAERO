package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserAccount;
import com.example.demo.form.UserSignUpFrom;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserSignUpController {
	private final UserService service;
	
//	パスワードハッシュ化処理用
	private final PasswordEncoder passwordEncoder;
	
//	ユーザー登録確認へ
	@PostMapping("/user/user-signup-confirm")
	public String userSignUpConfirm(
			@Validated @ModelAttribute("userSignUpForm") UserSignUpFrom form,
			BindingResult result,
			Model model) {
		
//		メールアドレスの登録チェック
		if (service.userMailSearch(form.getMail())) {
			result.rejectValue("mail", "error.mail", "入力のメールアドレスは既に使用されています");
//	        model.addAttribute("errorMsg", "入力のメールアドレスは既に使用されています。");
	        return "user/user-signup";
		}
//		pass1とpass2の確認
		if (!form.getPassword().equals(form.getPassword2())) {
			 result.rejectValue("password2", "error.password2", "パスワードが一致しません");
			 return "user/user-signup";
		}
//		バリデーション エラーの場合
		 if (result.hasErrors()) {
			 return "user/user-signup";
		 } 
		model.addAttribute("userSignUpForm", form);
		return "user/user-signup-confirm";
	}
	
//	ユーザー登録完了へ
	@PostMapping("/user/user-signup-complete")
	public String userSignUpComplete(
			@Validated @ModelAttribute("userSignUpForm") UserSignUpFrom form,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			return "user/user-signup";// 入力がエラーの場合
		}
		
		UserAccount user = new UserAccount();
		user.setUserName(form.getUserName());
		user.setMail(form.getMail());
		user.setPassword(form.getPassword());
		
//		データベースへ登録
		boolean result2 =  service.userSignUpAdd(user);
		if (!result2) {
			model.addAttribute("errorMsg", "登録処理ができませんでした");
			return "user/user-signup";// 入力がエラーの場合
		}
//		登録成功時リダイレクト
		return "redirect:/user/user-signup-complete";
	}
	
	@GetMapping("/user/user-signup-complete")
	public String userSignUpCompletePage() {
	    return "user/user-signup-complete";
	}


}
