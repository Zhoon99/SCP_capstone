package kr.mmgg.scp.dto.response;

import java.util.List;

import kr.mmgg.scp.entity.Task;
import lombok.Data;

@Data
public class ProjectDetailRequestTaskDto {
	private List<Task> tasklist;
}
