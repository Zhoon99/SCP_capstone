package kr.mmgg.scp.dto.response;

import lombok.Data;

@Data
public class RequestTaskDto {
	private Long taskId;
	private Long projectinuserId;

	private String taskContent;
	private String taskOwner;

	private Integer taskComplete;
	private Integer taskAccept;

	private String taskRequesttime;
	private String taskDeadline;
	private String Createtime;
}
