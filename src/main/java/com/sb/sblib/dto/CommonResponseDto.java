package com.sb.sblib.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 共通レスポンスDTO
 */
@Data
@Schema(description = "共通レスポンスDTO")
public class CommonResponseDto {

	@Schema(description = "処理結果", type = "integer", example = "0")
	private Integer result;

	@Schema(description = "エラーリスト", type = "list")
	private List<ErrorDto> errList;

	public CommonResponseDto() {
		errList = new ArrayList<ErrorDto>();
	}
}
