package com.sb.sblib.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// TODO ログイン成功時にセッション情報を設定する、といった処理を行う場合はここに記述する。

		// ログイン後画面に遷移する(F5キーによるフォーム再送を防ぐため、リダイレクトで POST -> GET にリクエストを振り替える)
		response.sendRedirect("/top");
	}
}
