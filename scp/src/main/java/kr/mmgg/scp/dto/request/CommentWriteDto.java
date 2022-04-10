package kr.mmgg.scp.dto.request;

import lombok.Data;

@Data
public class CommentWriteDto {
	private Long taskId;
	private Long userId;
	private String commentContent;
}
