package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectDetailImpl implements ProjectDetailService {

	private ProjectinUserRepository projectinUserRepository;

	@Transactional
	@Override
	public List<ProjectDetailAllTaskDto> allTask(Long projectId) {
		List<ProjectInUser> plist = projectinUserRepository.findByProjectId(projectId);
		ArrayList<ProjectDetailAllTaskDto> list = new ArrayList<ProjectDetailAllTaskDto>();
		ProjectDetailAllTaskDto dto;
		for (int i = 0; i < plist.size(); i++) {
			dto = new ProjectDetailAllTaskDto();
			if (!plist.get(i).getTasks().isEmpty()) {
				dto.setTasklist(plist.get(i).getTasks());
				list.add(dto);
			}
		}
		return list;
	}
}
