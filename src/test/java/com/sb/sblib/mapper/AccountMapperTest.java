package com.sb.sblib.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 * Mapperのテストは所定のDB初期化SQLを実行し、実物のクエリが意図した通りに動くことを確認する。
 */
@SpringBootTest
@Sql({"classpath:dbinit/schema.sql", "classpath:dbinit/data.sql"})
public class AccountMapperTest {

	@Autowired
	private AccountMapper accountMapper;

	@Test
	void test01() {

		// テスト対象のメソッドを実行し、結果を確認する
		assertEquals("guest@sblib.com", accountMapper.findAll().get(0).getMailAddress());
		assertEquals("pass", accountMapper.findAll().get(0).getPassword());
	}
}
