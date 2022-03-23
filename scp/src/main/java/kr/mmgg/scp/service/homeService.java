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
		List<ProjectInUser> plist;
		List<Task> tlist;
		ArrayList<homeView_teamleader> dtoList = new ArrayList<homeView_teamleader>();
		for (int i = 0; i < list.size(); i++) {
			plist = projectinUserRepository.findByProjectId(list.get(i).getProjectId());
			for (int j = 0; j < plist.size(); j++) {
				tlist = taskRepository.findByProjectinuserId(plist.get(j).getProjectinuserId());
					homeView_teamleader dto = new homeView_teamleader();
					dto.setProjectName(plist.get(j).getProject().getProjectName());
					dto.setProjectId(plist.get(j).getProjectId());
					dto.setTasklist(tlist);
					dto.setUserCode(plist.get(j).getProjectinuserCommoncode());
					if(!dto.getTasklist().isEmpty()) {
						dtoList.add(dto);
					}
			}
		}
		for (int i = 0; i < dtoList.size(); i++) {
			for (int j = 0; j < dtoList.get(i).getTasklist().size(); j++) {
				System.out.println(i+"번째 프로젝트 : "+dtoList.get(i).getProjectName());
				System.out.println(i+"번째 할일 담당자 : "+dtoList.get(i).getTasklist().get(j).getTaskOwner());
				System.out.println(i+"번째 할일 요청자 : "+dtoList.get(i).getTasklist().get(j).getTaskRequester());
				System.out.println(i+"번째 할일 : "+dtoList.get(i).getTasklist().get(j).getTaskContent());
				System.out.println("-----------------------------------------------------------");
			}
		}
		return dtoList;
	}

}
