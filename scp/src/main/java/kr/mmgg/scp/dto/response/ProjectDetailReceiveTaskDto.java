package kr.mmgg.scp.dto.response;
import lombok.Data;

@Data
public class ProjectDetailReceiveTaskDto {
	private Long taskId;
	private Long projectinuserId;
	
	private String taskContent;
	private String taskOwner;
	private String taskRequester;
	
	private Integer taskComplete;
	private Integer taskAccept;
	
	private String taskRequesttime;
	private String taskDeadline;
	private String taskCreatetime;
}
