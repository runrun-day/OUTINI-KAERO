package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	securityFilterの設定
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authz -> authz
	                .requestMatchers(
	                    "/user/user-login",
	                    "/user/user-signup",
	                    "/user/user-signup-confirm",
	                    "/user/user-signup-complete",
	                    "/user/user-logout-complete",
	                    "/admin/admin-login",
	                    "/css/**", "/js/**", "/images/**"
	                ).permitAll()
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	                .loginPage("/user/user-login")
	                .loginProcessingUrl("/user/login")
	                .defaultSuccessUrl("/home", true)
	                .failureUrl("/user/user-login?error=true")
	                .permitAll()
	            );
	        return http.build();
		}

//	 ハッシュ化用
	 @Bean 
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
