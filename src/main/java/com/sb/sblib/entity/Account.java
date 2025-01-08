package com.sb.sblib.entity;

import org.joda.time.DateTime;

import lombok.Data;

/**
 * アカウントのエンティティ
 */
@Data
public class Account {

	private Integer taccountId;
	private String mailAddress;
	private String password;
	private String isDeleted;
	private String createdBy;
	private DateTime createdAt;
	private String updatedBy;
	private DateTime updatedAt;
}
