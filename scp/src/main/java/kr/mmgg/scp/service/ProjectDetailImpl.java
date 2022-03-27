package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.repository.ProjectinUserRepository;

@Service
public class ProjectDetailImpl implements ProjectDetailService {
	@Autowired
	private ProjectinUserRepository projectinUserRepository;

	@Override
	public List<ProjectDetailAllTaskDto> allTask() {
		// TODO Auto-generated method stub
		return null;
	}

	// 프로젝트의 자신의 할일 가져오기
	@Override
	@Transactional
	public ProjectDetailMyTaskDto myTask(Long userId, Long projectId) {
		ProjectInUser piuUserIdAndProjectId = projectinUserRepository.findByUserIdAndProjectId(userId, projectId);
		ProjectDetailMyTaskDto pdMyTask = new ProjectDetailMyTaskDto();
		pdMyTask.setTaskList(piuUserIdAndProjectId.getTasks());
		System.out.println(pdMyTask);
		return pdMyTask;
	}
}
