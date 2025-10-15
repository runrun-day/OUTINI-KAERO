package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
//		リクエストのURLを見てadminかuserかを判定
        HttpServletRequest request = 
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        UserAccount account;
        
		if (request.getRequestURI().startsWith("/admin")) {
            //管理者ログイン
            account = userRepository.findAdminByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("管理者が見つかりません: " + mail));
            return org.springframework.security.core.userdetails.User
                .withUsername(account.getMail())
                .password(account.getPassword())
                .roles("ADMIN")
                .build();
        } else {
            //ユーザーログイン
            account = userRepository.findUserByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + mail));
            return org.springframework.security.core.userdetails.User
                .withUsername(account.getMail())
                .password(account.getPassword())
                .roles("USER")
                .build();
        }
	    }

}
