package kr.mmgg.scp.service;

import java.util.List;

import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.entity.Task;

public interface ProjectDetailService {
	public List<ProjectDetailAllTaskDto> allTask();
//	public List<ProjectDetailMyTaskDto> myTask();
}
