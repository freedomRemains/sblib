package com.sb.sblib.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sb.sblib.entity.Account;

@Mapper
public interface AccountMapper {

	@Select("""
			SELECT
			  TACCOUNT_ID, MAIL_ADDRESS, PASSWORD, IS_DELETED,
			  CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT
			FROM TACCOUNT
			""")
	List<Account> findAll();
}
