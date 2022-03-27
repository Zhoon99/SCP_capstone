package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mmgg.scp.dto.HomeViewDto;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeService {
	private ProjectinUserRepository projectinUserRepository;

	// 홈화면 DTO
	@Transactional
	public List<HomeViewDto> homeView_leader(Long userId) {
		List<ProjectInUser> piuUserIdList = projectinUserRepository.findByUserId(userId);
		List<ProjectInUser> piuProjectIdList;
		HomeViewDto homeViewDto;
		ArrayList<HomeViewDto> homeViewDtoList = new ArrayList<HomeViewDto>();
		for (int i = 0; i < piuUserIdList.size(); i++) {
			homeViewDto = new HomeViewDto();
			piuProjectIdList = projectinUserRepository.findByProjectId(piuUserIdList.get(i).getProjectId());
			// 할일 담는곳
			for (ProjectInUser pTask : piuProjectIdList) {
				if (!pTask.getTasks().isEmpty()) {
					homeViewDto.setTasklist(pTask.getTasks());
				}
			}
			// 플젝 아이디 이름 해당 사람의 코드 담기
			homeViewDto.setProjectId(piuUserIdList.get(i).getProjectId());
			homeViewDto.setProjectName(piuUserIdList.get(i).getProject().getProjectName());
			homeViewDto.setUserCode(piuUserIdList.get(i).getProjectinuserCommoncode());
			homeViewDtoList.add(homeViewDto);
		}
		return homeViewDtoList;
	}

}
