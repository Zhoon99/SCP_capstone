package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.response.HomeViewDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.ErrorCode;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeServicelmpl implements HomeService {
	private ProjectinUserRepository projectinUserRepository;
	private ProjectRepository projectRepository;

	// 홈화면 DTO
	@Transactional
	@Override
	public List<HomeViewDto> homeView(Long userId) {
		List<ProjectInUser> piuUserIdList = projectinUserRepository.findByUserId(userId);
		if (piuUserIdList.isEmpty()) {
			throw new CustomException(ErrorCode.PROJECT_IN_USER_NOT_FOUND);
		}
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

	// 프로젝트 생성
	@Override
	@Transactional
	public List<ProjectInUser> projectCreate(CreateProjectDto dto) {
		List<ProjectInUser> piuList = new ArrayList<>();
		Project project = new Project();
		// 프로젝트 생성
		project.setProjectName(dto.getTitle());
		Project newProject = projectRepository.save(project);
		// 프로젝트사람 생성
		for (int i = 0; i < dto.getMember().size(); i++) {
			ProjectInUser projectInUser = new ProjectInUser();
			projectInUser.setProjectId(newProject.getProjectId());
			projectInUser.setUserId(dto.getMember().get(i).getUserId());
			projectInUser.setProjectinuserMaker(dto.getMember().get(i).getProjectinuserMaker());
			projectInUser.setProjectinuserCommoncode(dto.getMember().get(i).getProjectinuserCommoncode());
			piuList.add(projectInUser);
		}

		return projectinUserRepository.saveAll(piuList);
	}

}
