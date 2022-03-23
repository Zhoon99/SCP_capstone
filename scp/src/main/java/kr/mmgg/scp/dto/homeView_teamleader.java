package kr.mmgg.scp.dto;

import java.util.List;

import kr.mmgg.scp.entity.Task;
import lombok.Data;

@Data
public class homeView_teamleader {

	private String projectName;
	// 할일 3개, 사람 3개 뽑아오는 곳
	private List<Task> tasklist;

	private String userCode;
}
