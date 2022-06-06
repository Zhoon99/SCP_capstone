package kr.mmgg.scp.dto.response;

import lombok.Data;

@Data
public class HomeViewProjectDetailCommentListDto {
	private Long taskId;
//	private Long taskOwnerId;
//	private String taskOwner_string;
	private Long commentId;
	private String commentNickname;
	private Long commentuserId;
	private String commentTime;
	private String commentContent;
}
