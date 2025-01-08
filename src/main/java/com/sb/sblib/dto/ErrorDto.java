package com.sb.sblib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * エラーDTO
 */
@Data
@Schema(description = "エラーDTO")
public class ErrorDto {

	@Schema(description = "項目ID", type = "string", example = "username")
	private String itemId;

	@Schema(description = "エラーメッセージ", type = "string", example = "ユーザ名を入力してください。")
	private String errMsg;
}
