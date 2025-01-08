package com.sb.sblib.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sb.sblib.security.CustomLoginSuccessHandler;
import com.sb.sblib.security.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final CustomLoginSuccessHandler customLoginSuccessHandler;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// セキュリティ設定を行う
		http.userDetailsService(customUserDetailsService) // ログイン時の処理をカスタマイズする
		.formLogin(login -> login // フォームによるログイン
				.loginPage("/login") // ログインページのURLパターン
				.permitAll() // 上記のURLは全て許可する
				.successHandler(customLoginSuccessHandler) // ログイン成功時の処理をカスタマイズする
		)
		.logout(logout -> logout
				.logoutSuccessUrl("/login") // ログアウト成功時の遷移先URLパターン
				.invalidateHttpSession(true) // セッション情報をクリアする
		)
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()) // cssなどの静的リソースは
				.permitAll() // 全てアクセスを許可する
				.anyRequest().authenticated() // それ以外の全てのリクエストは認証が必要
		);

		// セキュリティ設定をビルドし、呼び出し側に返却する
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		// パスワードエンコーダなし
		return NoOpPasswordEncoder.getInstance();

		// パスワードエンコーダとしてBcryptPasswordEncoderを使用する
		//return new BCryptPasswordEncoder();
	}
}
