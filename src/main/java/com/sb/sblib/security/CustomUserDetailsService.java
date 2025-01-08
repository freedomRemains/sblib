package com.sb.sblib.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// ここでユーザ名でDBを検索する
		String password = "pass"; // DBから取得したパスワードを設定する(続くSpringSecurityの認証処理に必要)

		// DBを検索してもusernameに該当するユーザが見つからない場合は UsernameNotFoundException をスローする
		// throw new UsernameNotFoundException("ユーザ名もしくはパスワードが間違っています。"); // TODO メッセージはちゃんとプロパティ経由で取得する

		// 認証に成功したら、ユーザのロール・権限を取得してリストにする
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

		// ユーザ情報を呼び出し側に返却する
		return new User(username, password, authorityList);
	}
}
