package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailRequestTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.ProjectUpdateGetInfoDto;
import kr.mmgg.scp.dto.response.ProjectUpdateGetInfoMemberDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.dateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectDetailImpl implements ProjectDetailService {

	private ProjectinUserRepository projectinUserRepository;
	private TaskRepository taskRepository;
	private UserRepository userRepository;

	// 프로젝트안의 전체 할일 가져오기
	@Transactional
	@Override
	public ResultDto<List<ProjectDetailAllTaskDto>> allTask(Long projectId) {
		List<ProjectInUser> plist = projectinUserRepository.findByProjectId(projectId);
		if (plist.isEmpty()) {
			throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
		}

		List<ProjectDetailAllTaskDto> list = new ArrayList<ProjectDetailAllTaskDto>();
		ProjectDetailAllTaskDto dto;
		for (int i = 0; i < plist.size(); i++) {
			dto = new ProjectDetailAllTaskDto();
			if (!plist.get(i).getTasks().isEmpty()) {
				dto.setProjectinuserId(plist.get(i).getProjectinuserId());
				for (int j = 0; j < plist.get(i).getTasks().size(); j++) {
					dto.setTaskId(plist.get(i).getTasks().get(j).getTaskId());
					dto.setTaskContent(plist.get(i).getTasks().get(j).getTaskContent());
					dto.setTaskOwner(plist.get(i).getTasks().get(j).getTaskOwner());
					dto.setTaskComplete(plist.get(i).getTasks().get(j).getTaskComplete());
					dto.setTaskAccept(plist.get(i).getTasks().get(j).getTaskAccept());
					dto.setTaskRequesttime(plist.get(i).getTasks().get(j).getTaskRequesttime());
					dto.setTaskDeadline(plist.get(i).getTasks().get(j).getTaskDeadline());
					dto.setCreatetime(plist.get(i).getTasks().get(j).getTaskCreatetime());
				}
				list.add(dto);
			}
		}
		ResultDto<List<ProjectDetailAllTaskDto>> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, list, "allTask");
		return rDto;
	}

	// 프로젝트의 자신의 할일 가져오기
	@Override
	@Transactional
	public List<ProjectDetailMyTaskDto> myTask(Long userId, Long projectId) {
		ProjectInUser piuUserIdAndProjectId = projectinUserRepository.findByUserIdAndProjectId(userId, projectId)
				.orElseThrow(() -> new CustomException(ErrorCode.PROJECT_OR_USER_NOT_FOUND));
		List<ProjectDetailMyTaskDto> list = new ArrayList<ProjectDetailMyTaskDto>();
		ProjectDetailMyTaskDto dto = null;
		for (int i = 0; i < piuUserIdAndProjectId.getTasks().size(); i++) {
			dto = new ProjectDetailMyTaskDto();
			dto.setProjectinuserId(piuUserIdAndProjectId.getTasks().get(i).getProjectinuserId());
			dto.setTaskId(piuUserIdAndProjectId.getTasks().get(i).getTaskId());
			dto.setTaskContent(piuUserIdAndProjectId.getTasks().get(i).getTaskContent());
			dto.setTaskOwner(piuUserIdAndProjectId.getTasks().get(i).getTaskOwner());
			dto.setTaskComplete(piuUserIdAndProjectId.getTasks().get(i).getTaskComplete());
			dto.setTaskAccept(piuUserIdAndProjectId.getTasks().get(i).getTaskAccept());
			dto.setTaskRequesttime(piuUserIdAndProjectId.getTasks().get(i).getTaskRequesttime());
			dto.setTaskDeadline(piuUserIdAndProjectId.getTasks().get(i).getTaskDeadline());
			dto.setCreatetime(piuUserIdAndProjectId.getTasks().get(i).getTaskCreatetime());
			list.add(dto);
		}
		return list;
	}

	// 해당 프로젝트안에서 받은 할일 확인하기
	@Override
	public ResultDto<?> receiveTask(Long projectId, Long projectinuserId) {
		List<Task> tlist = taskRepository.findByProjectinuserIdAndTaskAccept(projectinuserId, 0);
		if (tlist.isEmpty()) {
			throw new CustomException(ErrorCode.TASK_NOT_FOUND);
		}
		ArrayList<ProjectDetailReceiveTaskDto> pdrtList = new ArrayList<ProjectDetailReceiveTaskDto>();
		ProjectDetailReceiveTaskDto pdrtTask;
		for (int i = 0; i < tlist.size(); i++) {
			pdrtTask = new ProjectDetailReceiveTaskDto();
			if (tlist.get(i).getProjectinuser().getProjectId() == projectId) {
				pdrtTask.setProjectinuserId(tlist.get(i).getProjectinuserId());
				pdrtTask.setTaskId(tlist.get(i).getTaskId());
				pdrtTask.setTaskContent(tlist.get(i).getTaskContent());
				pdrtTask.setTaskOwner(tlist.get(i).getTaskOwner());
				pdrtTask.setTaskComplete(tlist.get(i).getTaskComplete());
				pdrtTask.setTaskAccept(tlist.get(i).getTaskAccept());
				pdrtTask.setTaskRequesttime(tlist.get(i).getTaskRequesttime());
				pdrtTask.setTaskDeadline(tlist.get(i).getTaskDeadline());
				pdrtTask.setCreatetime(tlist.get(i).getTaskCreatetime());
				pdrtList.add(pdrtTask);
			}
		}
		return rDto;
	}
	
	// SCP-304 보낸 요청 확인
	// 해당 프로젝트 안의 보낸 할일 확인하기
	@Override
	@Transactional
	public ResultDto<List<RequestTaskDto>> requestTask(Long projectId, Long userid) {
		List<ProjectInUser> plist = projectinUserRepository.findByProjectId(projectId);
		// 유저를 가져오고 유저가 없을 시 에러
		User user = userRepository.findByUserId(userid)
				.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
		if (plist.isEmpty()) {
			// 프로젝트가 없을 시 에러
			throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
		}

		List<RequestTaskDto> list = new ArrayList<>();
		RequestTaskDto dto;
		// 해당 프로젝트의 모든 할일 가져오기
		for (int i = 0; i < plist.size(); i++) {
			if (!plist.get(i).getTasks().isEmpty()) {
				// requester와 proejctinuserid가 같으면 저장
				// TODO: 닉네임이 겹칠 경우 큰일남......
				for (Task task : plist.get(i).getTasks()) {
					if (task.getTaskRequester().equals(user.getUserNickname())) {
						dto = new RequestTaskDto();
						dto.setProjectinuserId(task.getProjectinuserId());
						dto.setTaskId(task.getTaskId());
						dto.setTaskContent(task.getTaskContent());
						dto.setTaskOwner(task.getTaskOwner());
						dto.setTaskComplete(task.getTaskComplete());
						dto.setTaskAccept(task.getTaskAccept());
						dto.setTaskRequesttime(task.getTaskRequesttime());
						dto.setTaskDeadline(task.getTaskDeadline());
						dto.setCreatetime(task.getTaskCreatetime());
						System.out.println(dto.toString());
						list.add(dto);
					}
				}
			}
		}
		ResultDto<List<RequestTaskDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, list, "tasklist");
		return rDto;
	}
	
	// SCP-305 프로젝트 할일 보내는 작업
	// 해당 프로젝트 안의 할일 요청하기
	@Override
	@Transactional
	public ResultDto<?> sendTask(ProjectDetailSendTaskDto dto) {
		Task task = new Task();
		dateTime datetime = new dateTime();
		task.setTaskId(null);
		task.setTaskOwner(userRepository.findById(dto.getUserId()).get().getUserNickname()); // 받는 사람
		task.setTaskRequester(projectinUserRepository.findById(dto.getProjectinuserId()).get().getUser().getUserNickname()); // 보낸 사람
		task.setProjectinuserId(dto.getProjectinuserId());
		task.setTaskContent(dto.getTaskContent());
		task.setTaskCreatetime(datetime.dateTime());
		task.setTaskRequesttime(datetime.dateTime());
		task.setTaskDeadline(dto.getTaskDeadline());
		task.setTaskAccept(0);
		task.setTaskComplete(0);

		if (taskRepository.save(task) == null) {
			// TODO: 에러 핸들러 만들기
			throw new CustomException(ErrorCode.TASK_NOT_FOUND);
		} else {
			ResultDto<?> rDto = new ResultDto<>();
	        return rDto.makeResult(CustomStatusCode.CREATE_SUCCESS, null, null);
		}
	}
	
	// SCP-305 프로젝트 할일 요청시 프로젝트 안 사람들 불러오기
	// sendtask 유저 막기
	@Override
	@Transactional
	public ResultDto<List<UserDto>> gUsers(Long projectId) {
		List<ProjectInUser> projectInUsers = projectinUserRepository.findByProjectId(projectId);
		// 프로젝트
		if (projectInUsers.isEmpty()) {
			throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
		}
		List<UserDto> users = new ArrayList<>();
		UserDto user;
		for (ProjectInUser projectInUser : projectInUsers) {
			user = new UserDto(projectInUser.getUser());
			users.add(user);
		}
		
		ResultDto<List<UserDto>> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, users, "userlist");
		return rDto;
	}

	// 할일 완료여부 체크
	@Override
	@Transactional
	public void whetherTask(Long userId, Long taskId) {
		Task task = taskRepository.findByTaskId(taskId)
				.orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
		if (userId == task.getProjectinuser().getUserId()) {
			if (task.getTaskComplete() == 0) {
				task.setTaskComplete(1);
			} else {
				task.setTaskComplete(0);
			}
			taskRepository.save(task);
		} else {
			throw new CustomException(ErrorCode.TASK_NOT_MATCH);
		}
	}

	// 해당 프로젝트 안의 할일 수락 및 거절 하기
	// TODO: 에러 처리 해야함
	@Override
	public ResultDto<?> receiveTask(Long taskId, Integer selected) {
		ResultDto<?> rDto = new ResultDto<>();
		if (selected == -1) {
			Task task = taskRepository.findByTaskId(taskId).orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
			task.setTaskAccept(selected);
			taskRepository.save(task);
			rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS, null, null);
	        return rDto;
		} else if (selected == 1) {
			Task task = taskRepository.findByTaskId(taskId).orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
			task.setTaskAccept(selected);
			taskRepository.save(task);
			rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS, null, null);
	        return rDto;
		} else {
			throw new CustomException(ErrorCode.INTERNAL_ERROR);
		}
	}

	// 업데이트 페이지에 들어갈 정보
	@Override
	@Transactional
	public ResultDto<ProjectUpdateGetInfoDto> updateProjectGetInfo(Long projectid) {
		List<ProjectInUser> pInUsers = projectinUserRepository.findByProjectId(projectid);
		// TODO: 에러추가
		if (pInUsers.isEmpty()) {
			throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
		}
		ProjectUpdateGetInfoDto pUpdateGetDto = new ProjectUpdateGetInfoDto();
		List<ProjectUpdateGetInfoMemberDto> users = new ArrayList<>();
		for (ProjectInUser pInUser : pInUsers) {
			ProjectUpdateGetInfoMemberDto user = new ProjectUpdateGetInfoMemberDto();
			user.setProjectinuserId(pInUser.getProjectinuserId());
			user.setNickName(pInUser.getUser().getUserNickname());
			user.setUserId(pInUser.getUser().getUserId());
			user.setProjectinuserCommoncode(pInUser.getProjectinuserCommoncode());
			users.add(user);
		}
		pUpdateGetDto.setProjectName(pInUsers.get(0).getProject().getProjectName());
		pUpdateGetDto.setUsers(users);
		ResultDto<ProjectUpdateGetInfoDto> rDto = new ResultDto<ProjectUpdateGetInfoDto>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, pUpdateGetDto, "projectInfo");
		return rDto;
	}

	@Override
	@Transactional
	public ResultDto<?> updateProjectDeleteMember(Long ProjectinuserId) {
		ProjectInUser pInUser = projectinUserRepository.findById(ProjectinuserId)
				.orElseThrow(() -> new CustomException(ErrorCode.PROJECT_IN_USER_NOT_FOUND));
		projectinUserRepository.delete(pInUser);
		return new ResultDto<>().makeResult(CustomStatusCode.MODIFY_SUCCESS, null, null);
	}
}
