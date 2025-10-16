package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Message;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {

//	ユーザー情報
	private final UserRepository userRepository;
	
//	 メッセージ情報
	 private final MessageRepository messageRepository;
	 
//	 メッセージリスト
	@GetMapping("/user/message-list")
	 public String listReceivedMessages(Model model, Authentication authentication) {
//		セキュリティ認証からメールアドレス取得
        String mail = authentication.getName();
//      メールアドレスからユーザー情報取得
        UserAccount user = userRepository.findByMail(mail)
            .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません: " + mail));

//      ユーザー情報から受信メッセージ一覧取得
        List<Message> received = messageRepository.findReceivedByUser(user);
        model.addAttribute("messageList", received);
        return "user/message-list";
    }
	
//	メッセージ詳細
	@GetMapping("/user/message-detail/{id}")
	public String showReceivedMessage(@PathVariable("id") Integer messageId,
	                                  Model model,
	                                  Authentication authentication) {
//		messageIdからメッセージ詳細取得
	    Message msg = messageRepository.findByMessageId(messageId)
	        .orElseThrow(() -> new IllegalArgumentException("メッセージが見つかりません: " + messageId));

	    model.addAttribute("message", msg);
	    return "user/message-detail";
	}
	
//	削除確認
	// 情報提供内容確認
    @PostMapping("/user/message-delete-confirm/{id}")
    public String messageCreate(@PathVariable("id") Integer messageId,
            Model model,
            Authentication authentication) {
//		messageIdからメッセージ詳細取得
	    Message msg = messageRepository.findByMessageId(messageId)
	        .orElseThrow(() -> new IllegalArgumentException("メッセージが見つかりません: " + messageId));

	    model.addAttribute("message", msg);
	    
	    
    		return "user/message-delete-confirm";
    }
	
//    メッセージ削除処理(フラグtrue)作成中
    @PostMapping("/user/message-delete-submit")
    public String submitMessage(Model model,Authentication authentication,
    		Integer messageId) {

    	
    		return "user/message-delete-confirm";
    }
}
