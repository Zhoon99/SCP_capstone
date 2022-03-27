package kr.mmgg.scp.dto;

import java.util.List;

import kr.mmgg.scp.entity.Task;
import lombok.Data;

@Data
public class ProjectDetailRequestTaskDto {
	private List<Task> tlist;
}
