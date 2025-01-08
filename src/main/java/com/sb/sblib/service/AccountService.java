package com.sb.sblib.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sb.sblib.entity.Account;
import com.sb.sblib.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	/** アカウントマッパー */
	private final AccountMapper accountMapper;

	/**
	 * アカウント認証
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 認証成功ならtrue、認証失敗ならfalse
	 */
	public boolean auth(String mailAddress, String password) {
		List<Account> accountList = accountMapper.findAll();
		for (Account account : accountList) {
			if (account.getMailAddress().equals(mailAddress)
					&& account.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
