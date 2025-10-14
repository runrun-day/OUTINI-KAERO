package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.entity.LostCat;
import com.example.demo.repository.LostCatRepository;
import com.example.demo.service.UserLocationService;

import lombok.RequiredArgsConstructor;


//Spring Securityでログイン成功時のカスタム挙動
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final UserLocationService userLocationService;
    private final LostCatRepository lostCatRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String mail = authentication.getName();

//		フロントから位置情報を取得
//		緯度
        String latitude = request.getParameter("lat");
//		経度
        String longitude = request.getParameter("lon");

        if (latitude != null && longitude != null) {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);

            // 位置情報を保存
            userLocationService.saveUserLocation(mail, lat, lon);

            // 迷子猫検索（必要ならリクエスト属性に入れる）
            List<LostCat> nearbyCats = lostCatRepository.findNearbyCats(lat, lon);
            request.getSession().setAttribute("cats", nearbyCats);
        }

        // 成功後にリダイレクト
        response.sendRedirect("/user/user-home");

	}

}
