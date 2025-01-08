package com.sb.sblib.util;

import java.util.List;

import org.apache.poi.util.StringUtil;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

	/** メッセージソース */
	private final MsgUtil msg;

	/** スマートバリデータ */
	private final SmartValidator smartValidator;

	public boolean validate(Object target, BindingResult bindingResult, List<String> errMsgList) {

		// バリデーションエラーがある場合
		smartValidator.validate(target, bindingResult);
		if (bindingResult.hasErrors()) {

			// 全てのバリデーションエラーを処理するまでループ
			for (FieldError fieldError : bindingResult.getFieldErrors()) {

				// エラーメッセージリストにエラーを追加する
				errMsgList.add(getErrMsg(fieldError));
			}

			// エラーがあれば戻り値trueで呼び出し側に復帰する
			return true;
		}

		// エラーがなければ戻り値falseで呼び出し側に復帰する
		return false;
	}

	private String getErrMsg(FieldError fieldError) {

		// メッセージリソースからエラーメッセージを拾えるか確認する
		String errMsg = null;
		String[] codes = fieldError.getCodes();
		if (codes == null) {

			// メッセージリソースにエラーメッセージが定義されていなければ、デフォルトのエラーメッセージを返却する
			return fieldError.getDefaultMessage();
		}
		for (String code : codes) {
			try {
				errMsg = msg.get(code);
			} catch (NoSuchMessageException e) {
				continue;
			}
			if (StringUtil.isNotBlank(errMsg)) {
				Object[] arguments = fieldError.getArguments();
				if (arguments == null) {
					break;
				}
				for (Object arg : arguments) {
					System.out.println(arg);
				}
				return "【" + msg.get(fieldError.getField()) + "】" + errMsg;
			}
		}

		// メッセージリソースにエラーメッセージが定義されていなければ、デフォルトのエラーメッセージを返却する
		return fieldError.getDefaultMessage();
	}
}
