package com.sb.sblib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 通知DTO
 */
@Data
@Schema(description = "通知DTO")
public class NoticeDto {

	@Schema(description = "通知ID", type = "string", example = "http://localhost:8080/notice/id=12345")
	private String noticeId;

	@Schema(description = "通知メッセージ", type = "string", example = "本日23:00よりシステムメンテナンスを実施します。")
	private String noticeMsg;
}
