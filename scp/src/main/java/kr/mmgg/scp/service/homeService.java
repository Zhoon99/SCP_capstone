package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.homeView_teamleader;
import kr.mmgg.scp.dto.homeView_teammember;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
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
	
	public List<homeView_teamleader> homeView_leader(Long projectId) {
		ArrayList<homeView_teamleader> list = new ArrayList<homeView_teamleader>();
		homeView_teamleader homeview_teamleader = new homeView_teamleader();
		Project project = projectRepository.findByProjectId(projectId);
		homeview_teamleader.setProjectName(project.getProjectName());
		homeview_teamleader.setTasklist(null);
		list.add(homeview_teamleader);
		return list;
	}
	public List<homeView_teammember> homeView_member(Long projectId) {
		ArrayList<homeView_teammember> list = new ArrayList<homeView_teammember>();
		homeView_teammember homeview_teammember = new homeView_teammember();
		Project project = projectRepository.findByProjectId(projectId);
		homeview_teammember.setProjectName(project.getProjectName());
		homeview_teammember.setTasklist(null);
		list.add(homeview_teammember);
		return list;
	}
}
