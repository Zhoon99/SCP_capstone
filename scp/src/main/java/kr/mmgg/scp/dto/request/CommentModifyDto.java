package kr.mmgg.scp.dto.request;

import lombok.Data;

@Data
public class CommentModifyDto {
	private Long commentId;
	private String commentModifyTime;
	private String commentContent;
}
