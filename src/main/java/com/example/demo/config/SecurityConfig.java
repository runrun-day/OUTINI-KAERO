package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
//	ユーザー用
	@Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http,
    		CustomAuthenticationSuccessHandler successHandler) throws Exception {
		http
        .securityMatcher("/user/**") // user配下のみに適用や
//        アクセス制御（認可）
        .authorizeHttpRequests(auth -> auth
//        	誰でもアクセス可やで
            .requestMatchers(
            		"/user/user-login",
            		"/user/user-signup",
            		"/user/user-signup-confirm",
                "/user/user-signup-complete",
                "/user/user-logout-complete",
            		"/css/**", "/js/**", "/images/**").permitAll()
//           それ以外はログイン必須やで
            .anyRequest().authenticated()
        )
//        フォームログイン設定
        .formLogin(form -> form
//        	ログイン画面のURL
            .loginPage("/user/user-login")
//           Spring Security がログイン処理を行うURL
            .loginProcessingUrl("/user/login")
//           name="mail" をユーザー名として使う
            .usernameParameter("mail")
//           name="password" をパスワードとして使う
            
//           ログイン成功後の遷移先(今回カスタムしたのでパス)
//           .defaultSuccessUrl("/user/user-home", true)
            
            .passwordParameter("password")
//          ログイン処理成功後のカスタム処理
            .successHandler(successHandler)
//           失敗時の遷移先
            .failureUrl("/user/user-login?error=true")
            .permitAll()
        )
//      ログアウト設定
        .logout(logout -> logout
//        	ログイン処理成功後のカスタム処理
            .logoutUrl("/user/logout")
//           ログアウト後の遷移先
            .logoutSuccessUrl("/user/user-logout-complete")
            .permitAll()
        );
    return http.build();
}
	
//	管理者用
	@Bean
    @Order(2)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/admin/**") // admin配下
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                		"/admin/admin-login",
                		"/admin/admin-logout-complete",
                		"/css/**", "/js/**", "/images/**"
                		).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/admin/admin-login")
                .loginProcessingUrl("/admin/login")
                .usernameParameter("mail")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/admin-home", true)
                .failureUrl("/admin/admin-login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/admin-logout-complete")
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
