package com.sb.sblib.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sb.sblib.entity.Account;
import com.sb.sblib.mapper.AccountMapper;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

	@Mock
	private AccountMapper accountMapper;

	@InjectMocks
	private AccountService accountService;

	@Test
	void test01() {

		// 正常系

		// accountMapperのfindAllメソッドの返却値を設定する
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setMailAddress("guest@sblib.com");
		account.setPassword("pass");
		accountList.add(account);
		when(accountMapper.findAll()).thenReturn(accountList);

		// テスト対象のメソッドを実行する
		assertTrue(accountService.auth("guest@sblib.com", "pass"));
	}

	@Test
	void test02() {

		// カバレッジ(認証失敗パターン)

		// accountMapperのfindAllメソッドの返却値を設定する
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setMailAddress("guest@sblib.com");
		account.setPassword("pass");
		accountList.add(account);
		when(accountMapper.findAll()).thenReturn(accountList);

		// テスト対象のメソッドを実行する
		assertFalse(accountService.auth("master@sblib.com", "pass"));
	}

	@Test
	void test03() {

		// カバレッジ(メールアドレスは合致するが、パスワードが合致しないパターン)

		// accountMapperのfindAllメソッドの返却値を設定する
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setMailAddress("guest@sblib.com");
		account.setPassword("pass");
		accountList.add(account);
		when(accountMapper.findAll()).thenReturn(accountList);

		// テスト対象のメソッドを実行する
		assertFalse(accountService.auth("guest@sblib.com", "ngpass"));
	}
}
