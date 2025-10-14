package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	securityFilterの設定
	 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
    		CustomAuthenticationSuccessHandler successHandler) throws Exception {
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
//                ログイン処理後のカスタム挙動
                .successHandler(successHandler) 
//                成功すれば /user/user-home
//                .defaultSuccessUrl("/user/user-home", true)
//                失敗すれば /user/user-login?error=true にリダイレクト
                .failureUrl("/user/user-login?error=true")
                // name属性="mail" 
                .usernameParameter("mail")
                // name属性="password"
                .passwordParameter("password") 
                .permitAll()
            )
        
	        .logout(logout -> logout
	                .logoutUrl("/user/logout")
//	                ログアウト完了のページ
	                .logoutSuccessUrl("/user/user-logout-complete")
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
