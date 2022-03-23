package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mmgg.scp.dto.homeView_teamleader;
import kr.mmgg.scp.dto.homeView_teammember;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class homeService {
	private ProjectinUserRepository projectinUserRepository;
	private TaskRepository taskRepository;
	private ProjectRepository projectRepository;

	@Transactional
	public List<homeView_teamleader> homeView_leader(Long userId) {
		List<ProjectInUser> list = projectinUserRepository.findByUserId(userId);
		List<Task> tList = new ArrayList<>();
		List<homeView_teamleader> dtoList = new ArrayList<homeView_teamleader>();
		for (int i = 0; i < list.size(); i++) {
			tList = taskRepository.findTop3ByProjectinuserId(list.get(i).getProjectinuserId());
			homeView_teamleader dto = new homeView_teamleader();
			dto.setProjectName(list.get(i).getProject().getProjectName());
			dto.setUserCode(list.get(i).getProjectinuserCommoncode());
			dto.setProjectId(list.get(i).getProjectId());
			dto.setTasklist(tList);
			dtoList.add(dto);
			System.out.println(dto.toString());
		}
		
		
		return null;
	}

}
