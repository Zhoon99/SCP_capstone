package kr.mmgg.scp.dto.response;

import lombok.Data;

@Data
public class HomeViewProjectDetailCommentListDto {
	private Long commentId;
	private Long taskId;
	private String userName;
	private String commentTime;
	private String commentContent;
}
