package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LostCat;
import com.example.demo.entity.Message;
import com.example.demo.entity.UserAccount;
import com.example.demo.form.MessageFrom;
import com.example.demo.repository.LostCatRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CatInfoController {

//	ユーザー情報
	private final UserRepository userRepository;
	
//	迷子猫情報
	 private final LostCatRepository lostCatRepository;
//	 メッセージ情報
	 private final MessageRepository messageRepository;
	
	// 迷子猫情報詳細
	    @GetMapping("/user/missing-cat-detail/{id}")
	    public String showCatDetail(@PathVariable("id") Integer catId, Model model) {
	        LostCat cat = lostCatRepository.findById(catId)
	                .orElseThrow(() -> new IllegalArgumentException("指定された迷子猫情報が見つかりません: " + catId));

	        model.addAttribute("cat", cat);
	        return "user/missing-cat-detail";
	    }
	    
	 // 情報提供フォーム
	    @GetMapping("/user/user-message-create/{id}")
	    public String messageCreate(@PathVariable("id") Integer catId, Model model) {
	        LostCat cat = lostCatRepository.findById(catId)
	                .orElseThrow(() -> new IllegalArgumentException("指定された迷子猫情報が見つかりません: " + catId));

	        model.addAttribute("cat", cat);
	        MessageFrom mFrom = new MessageFrom();
	        mFrom.setCatId(catId); //hidden用
	        model.addAttribute("messageFrom", mFrom);
	        return "user/user-message-create";
	    }
	    
	 // 情報提供内容確認
	    @PostMapping("/user/user-message-confirm")
	    public String messageCreate(@Validated @ModelAttribute MessageFrom mFrom,
	    		BindingResult result, Model model,
	    		Authentication authentication) {
	    	
//	    		hiddenから取得
	    		Integer catId = mFrom.getCatId();
	    		if (catId == null) {
	    	        throw new IllegalArgumentException("catId が送信されていません");
	    	    }
	    		
	        LostCat cat = lostCatRepository.findById(catId)
	                .orElseThrow(() -> new IllegalArgumentException("指定された迷子猫情報が見つかりません: " + catId));

//			バリデーションエラーの場合
			 if (result.hasErrors()) {
				 model.addAttribute("cat", cat);
				 return "user/user-message-create";
			 }
//			ユーザー情報
	        String mail = authentication.getName();
	        model.addAttribute("mail", mail);
//		    外語猫情報
	        model.addAttribute("cat", cat);
//	        フォーム内容
	        model.addAttribute("messageFrom", mFrom);
	        return "user/user-message-confirm";
	    }
	    
	    @PostMapping("/user/user-message-submit")
	    public String submitMessage(@ModelAttribute MessageFrom messageFrom,
	                                Model model,Authentication authentication) {
//			ユーザー情報get
	        String mail = authentication.getName();
	        Optional<UserAccount> optionalUser = userRepository.findByMail(mail);
	    	
		    	UserAccount user;
		    	
		    	if (optionalUser.isPresent()) {
		    	    user = optionalUser.get();
		    	} else {
		    	    throw new IllegalArgumentException("ユーザーが見つかりません");
		    	}
//			迷子情報get
	        Integer catId = messageFrom.getCatId();
	        LostCat cat = lostCatRepository.findById(catId)
	                .orElseThrow(() -> new IllegalArgumentException("指定された迷子猫情報が見つかりません: " + catId));

//			DB登録処理
	        try {
	        		Message msg = new Message();
	            msg.setLostCat(cat);
	            msg.setTitle(messageFrom.getTitle());
	            msg.setExplanation(messageFrom.getExplanation());
	            msg.setRenamePicture(messageFrom.getPic()); 
	            msg.setMessageDate(LocalDateTime.now());
	            msg.setFromUser(user);
	            messageRepository.save(msg);

//				リダイレクト
	            return "redirect:/user/message-complete";
	        } catch (Exception ex) {
	            model.addAttribute("errorMsg", "エラーが発生しました。再度ご入力ください。");
	            // 再度猫情報を渡す
	            model.addAttribute("cat", cat);
	            model.addAttribute("messageFrom", messageFrom);
	            return "user/user-message-create";
	        }
	    }
	    
	    @GetMapping("/user/message-complete")
	    public String messageCompleteShow(Model model) {
	    	
	    	model.addAttribute("title","送信完了");
	    	model.addAttribute("msg","メッセージの送信完了がしました。");
	    	
	    	return "user/complete";
	    }
}
