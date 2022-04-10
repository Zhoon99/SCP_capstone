package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.request.UpdateProjectModify;
import kr.mmgg.scp.dto.request.UpdateProjectModifyMember;
import kr.mmgg.scp.dto.response.HomeViewDto;

import kr.mmgg.scp.dto.response.TeamDetailDto;
import kr.mmgg.scp.dto.response.TeamMembersDto;
import kr.mmgg.scp.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class HomeServicelmpl implements HomeService {
	private ProjectinUserRepository projectinUserRepository;
	private ProjectRepository projectRepository;

	// 홈화면 DTO
	@Transactional
	@Override
	public ResultDto<List<HomeViewDto>> homeView(Long userId) {
		List<ProjectInUser> piuUserIdList = projectinUserRepository.findByUserId(userId);
		if (piuUserIdList.isEmpty()) {
			throw new CustomException(ErrorCode.PROJECT_IN_USER_NOT_FOUND);
		}
		List<ProjectInUser> piuProjectIdList;
		HomeViewDto homeViewDto;
		List<HomeViewDto> homeViewDtoList = new ArrayList<HomeViewDto>();
		for (int i = 0; i < piuUserIdList.size(); i++) {
			homeViewDto = new HomeViewDto();
			List<Task> tList = new ArrayList<>();
			piuProjectIdList = projectinUserRepository.findByProjectId(piuUserIdList.get(i).getProjectId());

			// 할일 담는곳
			for (ProjectInUser pTask : piuProjectIdList) {
				if (!pTask.getTasks().isEmpty()) {
					tList.addAll(pTask.getTasks());
				}
			}
			// 플젝 아이디 이름 해당 사람의 코드 담기
			homeViewDto.setProjectId(piuUserIdList.get(i).getProjectId());
			homeViewDto.setProjectName(piuUserIdList.get(i).getProject().getProjectName());
			homeViewDto.setUserCode(piuUserIdList.get(i).getProjectinuserCommoncode());
			homeViewDto.setTasklist(tList);
			homeViewDtoList.add(homeViewDto);
		}
		ResultDto<List<HomeViewDto>> rDto = new ResultDto<List<HomeViewDto>>();
		return rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, homeViewDtoList, "projects");
	}

	// 프로젝트 생성
	@Override
	@Transactional
	public ResultDto<List<ProjectInUser>> projectCreate(CreateProjectDto dto) {
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
		projectinUserRepository.saveAll(piuList);
		ResultDto<List<ProjectInUser>> rDto = new ResultDto<List<ProjectInUser>>();
		return rDto.makeResult(CustomStatusCode.CREATE_SUCCESS);
	}

	// 프로젝트 수정
	@Override
	@Transactional
	public ResultDto<?> modifyProject(UpdateProjectModify updateProjectModify) {
		if (updateProjectModify.equals(null)) {
			throw new IllegalStateException("수정할 팀 정보를 가져오지 못했습니다.");
		}

		Project project = new Project();
		project.setProjectId(updateProjectModify.getProjectId());
		project.setProjectName(updateProjectModify.getProjectName());
		projectRepository.save(project);

		List<UpdateProjectModifyMember> newProjects = updateProjectModify.getUsers();
		List<ProjectInUser> existProjects = projectinUserRepository.findByProjectId(updateProjectModify.getProjectId());

		if (existProjects.isEmpty()) {
			throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
		}

		List<UpdateProjectModifyMember> newMembers = new ArrayList<>();

		for (UpdateProjectModifyMember i : newProjects) {
			for (ProjectInUser j : existProjects) {
				if (i.getUserId() == j.getUserId()) { // 업데이트
					ProjectInUser projectInUser = new ProjectInUser();
					projectInUser.setProjectinuserId(j.getProjectinuserId());
					projectInUser.setUserId(i.getUserId());
					projectInUser.setProjectId(updateProjectModify.getProjectId());
					projectInUser.setProjectinuserCommoncode(i.getCommonCode());
					projectInUser.setProjectinuserMaker(i.getMaker());
					projectinUserRepository.save(projectInUser);
					newMembers.add(i);
				}
			}
			if (!newMembers.contains(i)) { // 추가
				ProjectInUser projectInUser = new ProjectInUser();
				projectInUser.setUserId(i.getUserId());
				projectInUser.setProjectId(updateProjectModify.getProjectId());
				projectInUser.setProjectinuserCommoncode(i.getCommonCode());
				projectInUser.setProjectinuserMaker(i.getMaker());
				projectinUserRepository.save(projectInUser);
			}
		}
		ResultDto<?> rDto = new ResultDto<>();
		return rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS);
	}

	@Override
	public ResultDto<?> removeProject(Long projectId) {
		projectinUserRepository.deleteById(projectId);
		projectRepository.deleteById(projectId);

		ResultDto<?> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.DELETE_SUCCESS);
		return rDto;
	}

}
