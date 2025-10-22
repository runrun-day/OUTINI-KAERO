package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Message;
import com.example.demo.entity.UserAccount;
import com.example.demo.form.UserSearchForm;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminUserController {
	private final MessageService Mservice;
	private final UserAccountService Uservice;

//	ユーザー検索ページへ
	@GetMapping("/admin/admin-user-search")
	 public String adminUserSearch(Model model) {
		model.addAttribute("userSearchForm", new UserSearchForm());
	    
		return "admin/admin-user-search";
	}
//	ユーザー検索
	@PostMapping("/admin/admin-user-search")
	 public String adminShowUser(Model model,UserSearchForm form) {
		Integer userId = form.getUserId();
		
//		空欄検索時全ユーザー表示
		if(userId == null) {
//			Service側でuserId=1を除外するよう記載済み
			List<UserAccount> users = Uservice.getActiveUsersByLoginDateDesc();
			model.addAttribute("userList", users);
			
			return "admin/admin-user-search";
		}
//		入力がある場合
		Optional<UserAccount> user = Uservice.findByUserId(userId);
		if (user.isPresent()) {
            model.addAttribute("user", user.get());
		}else {
            model.addAttribute("errorMsg", "ユーザーが見つかりません");
        }

		return "admin/admin-user-search";
	}
	
//	ユーザー情報詳細
	@GetMapping("/admin/user-detail")
	public String userDetail(@RequestParam Integer userId, Model model) {
	    Optional<UserAccount> userOpt = Uservice.findByUserId(userId);
	    if (userOpt.isPresent()) {
	        model.addAttribute("user", userOpt.get());
	        return "admin/admin-user-detail";
	    } else {
	        model.addAttribute("errorMsg", "ユーザーが見つかりません");
	        return "admin/admin-user-search";
	    }
	}
	
//	選択ユーザーのメッセージを確認
	@GetMapping("/admin/admin-message-confirm")
	public String showUserMessage(@RequestParam("userId") Integer userId, Model model) {
		List<Message> messages = Mservice.getMessagesByUserId(userId);
		
		Optional<UserAccount> userOpt = Uservice.findByUserId(userId);
	    if (userOpt.isPresent()) {
	        model.addAttribute("user", userOpt.get());
	        model.addAttribute("messages", messages);
	        return "admin/admin-message-confirm";
	    } else {
	        model.addAttribute("errorMsg", "ユーザーが見つかりません");
	        return "admin/admin-user-search";
	        
	    }
	    
	}
}
