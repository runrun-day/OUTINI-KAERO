package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.LostCat;
import com.example.demo.repository.LostCatRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CatInfoController {

	 private final LostCatRepository lostCatRepository;
	
	// 迷子猫情報詳細
	    @GetMapping("/user/missing-cat-detail/{id}")
	    public String showCatDetail(@PathVariable("id") Integer catId, Model model) {
	        LostCat cat = lostCatRepository.findById(catId)
	                .orElseThrow(() -> new IllegalArgumentException("指定された迷子猫情報が見つかりません: " + catId));

	        model.addAttribute("cat", cat);
	        return "user/missing-cat-detail";
	    }
}
