package kr.mmgg.scp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class HomeViewProjectDetailDto {
	private Long taskId; // 할일 P 번호
	private Long taskOwnerId;
	private String taskOwner_string; // 할일 담당자
	private String taskRequester_string; // 할일 요청자
	private String taskContent; // 할일 내용
	private String taskDeadline; // 할일 마감 시간
	private List<HomeViewProjectDetailCommentListDto> commentList; // 댓글이 여러개 달릴것을 가정하여 리스트로 작성
}
