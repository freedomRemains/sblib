package com.sb.sblib.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MsgUtil {

	private final MessageSource messageSource;

	/**
	 * メッセージ取得(可変パラメータなし)
	 * 
	 * @param key プロパティファイルのキー
	 * @param params 可変パラメータ
	 * @return 取得したメッセージ
	 */
	public String get(String key) {
		return messageSource.getMessage(key, null, Locale.JAPAN);
	}

	/**
	 * メッセージ取得(可変パラメータあり)
	 * 
	 * @param key プロパティファイルのキー
	 * @param params 可変パラメータ
	 * @return 取得したメッセージ
	 */
	public String get(String key, String... params) {
		return messageSource.getMessage(key, params, Locale.JAPAN);
	}
}
