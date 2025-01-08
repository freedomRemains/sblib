package com.sb.sblib.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * アカウントのDTO
 */
@Data
@Schema(description = "アカウントのDTO")
public class AccountDto {

	@Schema(description = "共通レスポンスDTO", type = "CommonResponseDto")
	private CommonResponseDto commonResponseDto;

	@Schema(description = "通知リスト", type = "CommonResponseDto")
	private List<NoticeDto> noticeList;

	@Schema(description = "アカウントID", type = "integer", example = "100100100")
	private Integer taccountId;

	@Schema(description = "メールアドレス", type = "string", example = "guest@sblib.com")
	private String mailAddress;

	/**
	 * コンストラクタ
	 */
	public AccountDto() {
		commonResponseDto = new CommonResponseDto();
		commonResponseDto.setResult(Integer.valueOf(0));
		noticeList = new ArrayList<NoticeDto>();
	}
}
