package kr.mmgg.scp.dto.response;

import java.util.List;

import kr.mmgg.scp.entity.Task;
import lombok.Data;

@Data
public class ProjectDetailAllTaskDto {
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
