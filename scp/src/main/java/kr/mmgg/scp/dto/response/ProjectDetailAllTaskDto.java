package kr.mmgg.scp.dto.response;

import lombok.Data;

@Data
public class ProjectDetailAllTaskDto {
		private Long taskId;
		private Long projectinuserId;
		private String taskOwner_string;
		
		private String taskContent;
//		private String taskOwner;
		private	Long taskRequester;
		private String taskRequester_string;
		
		private Integer taskComplete;
		private Integer taskAccept;
		
		private String taskRequesttime;
		private String taskDeadline;
		private String taskCreatetime;
}
