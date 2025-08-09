package com.sb.sblib.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.util.StringUtil;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidationUtil {

    /** メッセージソース */
    private final MsgUtil msg;

    /** スマートバリデータ */
    private final SmartValidator smartValidator;

    public Map<String, String> validate(Object target, BindingResult bindingResult) {

        // バリデーションエラーがある場合
        var errMsgMap = new LinkedHashMap<String, String>();
        smartValidator.validate(target, bindingResult);
        if (bindingResult.hasErrors()) {

            // 全てのバリデーションエラーを処理するまでループ
            for (FieldError fieldError : bindingResult.getFieldErrors()) {

                // エラーメッセージマップにエントリを追加する
                putErrMsgMap(fieldError, errMsgMap);
            }
        }

        // エラーメッセージマップを呼び出し側に返却する
        return errMsgMap;
    }

    private void putErrMsgMap(FieldError fieldError, Map<String, String> errMsgMap) {

        // エラーが起きたフィールドのコードを取得する
        String[] codes = fieldError.getCodes();
        if (codes == null) {

            // メッセージリソースにエラーメッセージが定義されていなければ、デフォルトのエラーメッセージを返却する
            errMsgMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            return;
        }

        // どんな場合でも必ず最初のコードをマップのキーとする
        // コードは次のようになっており、エラーが起きた項目の位置情報が最も分かりやすいのは、
        // 必ず先頭のコードとなっている。(少なくともSpringBootがこの仕組みを返るまでは、
        // 必ず先頭のコードをマップのキーとする)
        // coce[0] NotBlank.validationForm.childList[0].childName
        // code[1] NotBlank.validationForm.childList.childName
        // code[2] NotBlank.childList[0].childName
        // code[3] NotBlank.childList.childName
        // code[4] NotBlank.java.lang.String
        // code[5] NotBlank
        String mapKey = codes[0];

        // 全てのコードを処理するまでループ
        var searchedCodes = new StringBuilder();
        for (String code : codes) {
            try {
                // コードでエラーメッセージを取得する
                String errMsg = msg.get(code);
                if (StringUtil.isNotBlank(errMsg)) {

                    // エラーメッセージを検知した場合は、マップにエントリを追加して呼び出し側に復帰する
                    errMsgMap.put(mapKey, errMsg);
                    return;
                }
            } catch (NoSuchMessageException e) {

                // メッセージリソースに該当メッセージがない場合は、見つからなかった検索キーを記録する
                if (searchedCodes.length() > 0) {
                    searchedCodes.append(", ");
                }
                searchedCodes.append(code);
                continue;
            }
        }

        // メッセージリソースにエラーメッセージが定義されていなければ、デフォルトのエラーメッセージを返却する
        log.info(msg.get("validation.noMsg", searchedCodes.toString()));
        errMsgMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        return;
    }
}
