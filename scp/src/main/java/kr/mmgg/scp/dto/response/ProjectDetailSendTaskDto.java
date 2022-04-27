package kr.mmgg.scp.dto.response;
import lombok.Data;

@Data
public class ProjectDetailSendTaskDto {
	private Long projectId;
	private Long userId;
	private Long projectinuserId;
	private String taskContent;
	private String taskDeadline;
	// private List<Task> tasklist;
}
