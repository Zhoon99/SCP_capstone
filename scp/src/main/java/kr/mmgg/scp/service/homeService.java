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

	public List<homeView_teamleader> homeView_leader(Long userId) {
		ArrayList<homeView_teamleader> list = new ArrayList<homeView_teamleader>();
		homeView_teamleader homeview_teamleader = new homeView_teamleader();
		ProjectinUserRepository project = projectinUserRepository;
		homeview_teamleader.setProjectName(null);
		homeview_teamleader.setTasklist(null);
		homeview_teamleader.setUserCode(null);
		list.add(homeview_teamleader);

		return list;
	}

}
