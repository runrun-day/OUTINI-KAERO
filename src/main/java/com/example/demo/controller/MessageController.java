package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Message;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {

//	ユーザー情報
	private final UserService Uservice;
//	 メッセージ
	private final MessageService Mservice;
	 
//	 メッセージリスト
	@GetMapping("/user/message-list")
	 public String listReceivedMessages(Model model, Authentication authentication) {
//		セキュリティ認証からメールアドレス取得
        String mail = authentication.getName();
//      メールアドレスからユーザー情報取得
        UserAccount user = Uservice.findUserByMail(mail);
            
//      ユーザー情報から受信メッセージ一覧取得
        List<Message> received = Mservice.getReceivedMessages(user);
        model.addAttribute("messageList", received);
        return "user/message-list";
    }
	
//	メッセージ詳細
	@GetMapping("/user/message-detail/{id}")
	public String showReceivedMessage(@PathVariable("id") Integer messageId,
	                                  Model model,
	                                  Authentication authentication) {
		
		
//		messageIdからメッセージ詳細取得
		Message msg = Mservice.findMessageById(messageId);

	    model.addAttribute("message", msg);
	    return "user/message-detail";
	}
	
//	削除確認
	// 情報提供内容確認
    @PostMapping("/user/message-delete-confirm")
    public String messageDeleteConfirm(@RequestParam("id") Integer messageId,
            Model model,
            Authentication authentication) {
//		messageIdからメッセージ詳細取得
    	Message msg = Mservice.findMessageById(messageId);

	    model.addAttribute("message", msg);
	    
	    
    		return "user/message-delete-confirm";
    }
	
//    メッセージ削除処理(フラグtrue)
    @PostMapping("/user/message-delete-submit")
    public String submitMessage(@RequestParam("id") Integer messageId,
    		Model model, RedirectAttributes redirectAttributes) {

    	Message msg;
//    	DB登録処理
        try {
//    		削除フラグ変更
        	msg = Mservice.deleteMessage(messageId);

// 			リダイレクト
            redirectAttributes.addFlashAttribute("title", "削除完了");
            redirectAttributes.addFlashAttribute("msg", "メッセージを削除しました。");
    		
//    	リダイレクト
            return "redirect:/user/delete-complete";
            
        } catch (Exception ex) {
        	
            model.addAttribute("errorMsg", "エラーが発生しました。");
            
            return "user/message-detail";
	        }
}
    
//  リダイレクト先
    @GetMapping("/user/delete-complete")
    public String deleteComplete(Model model, RedirectAttributes redirectAttributes) {
        return "user/complete"; 
    }
}
