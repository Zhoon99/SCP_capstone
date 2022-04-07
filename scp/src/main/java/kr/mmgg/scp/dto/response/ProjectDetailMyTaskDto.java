package kr.mmgg.scp.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.mmgg.scp.entity.Task;
import lombok.Data;

@Data
public class ProjectDetailMyTaskDto {
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
