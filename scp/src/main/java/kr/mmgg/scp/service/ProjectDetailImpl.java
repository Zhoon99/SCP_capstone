package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailRequestTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.util.CustomException;
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
	public List<ProjectDetailAllTaskDto> allTask(Long projectId) {
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
		return list;
	}

	// 프로젝트의 자신의 할일 가져오기
	@Override
	@Transactional
	public ProjectDetailMyTaskDto myTask(Long userId, Long projectId) {
		ProjectInUser piuUserIdAndProjectId = projectinUserRepository.findByUserIdAndProjectId(userId,
				projectId).orElseThrow(() -> new CustomException(ErrorCode.PROJECT_OR_USER_NOT_FOUND));
		ProjectDetailMyTaskDto pdMyTask = new ProjectDetailMyTaskDto();
		pdMyTask.setTaskList(piuUserIdAndProjectId.getTasks());
		// System.out.println(piuUserIdAndProjectId);
		return pdMyTask;
	}

	// 해당 프로젝트안에서 받은 할일 확인하기
	@Override
	public List<ProjectDetailReceiveTaskDto> receiveTask(Long projectId, Long projectinuserId) {
		List<Task> tlist = taskRepository.findByProjectinuserIdAndTaskAccept(projectinuserId, 0);
		if (tlist.isEmpty()) {
			throw new CustomException(ErrorCode.TASK_NOT_FOUND);
		}
		ArrayList<ProjectDetailReceiveTaskDto> pdrtList = new ArrayList<ProjectDetailReceiveTaskDto>();
		ProjectDetailReceiveTaskDto pdrtTask;
		for (int i = 0; i < tlist.size(); i++) {
			pdrtTask = new ProjectDetailReceiveTaskDto();
			if (tlist.get(i).getProjectinuser().getProjectId() == projectId) {
				pdrtTask.setTask(tlist.get(i));
				pdrtList.add(pdrtTask);
			}
		}
		return pdrtList;
	}

	// 해당 프로젝트 안의 보낸 할일 확인하기
	@Override
	@Transactional
	public List<RequestTaskDto> requestTask(Long projectId, Long userid) {
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
			dto = new RequestTaskDto();
			if (!plist.get(i).getTasks().isEmpty()) {
				// requester와 proejctinuserid가 같으면 저장
				// TODO: 닉네임이 겹칠 경우 큰일남......
				for (Task task : plist.get(i).getTasks()) {
					if (task.getTaskRequester().equals(user.getUserNickname())) {
						dto.setReqTask(task);
						list.add(dto);
					}
				}
			}
		}
		return list;
	}

	// 해당 프로젝트 안의 할일 요청하기
	@Override
	@Transactional
	public boolean sendTask(ProjectDetailSendTaskDto dto) {
		Task task = new Task();
		dateTime datetime = new dateTime();
		task.setTaskId(null);
		task.setTaskOwner(userRepository.findById(dto.getUserId()).get().getUserNickname()); // 받는 사람
		task.setTaskRequester(
				projectinUserRepository.findById(dto.getProjectinuserId()).get().getUser().getUserNickname()); // 보낸 사람
		task.setProjectinuserId(dto.getProjectinuserId());
		task.setTaskContent(dto.getTaskContent());
		task.setTaskCreatetime(datetime.dateTime());
		task.setTaskRequesttime(datetime.dateTime());
		task.setTaskDeadline(dto.getTaskDeadline());
		task.setTaskAccept(0);
		task.setTaskComplete(0);

		if (taskRepository.save(task) != null) {
			return true;
		} else {
			// TODO: 에러 핸들러 만들기
			throw new CustomException(ErrorCode.TASK_NOT_FOUND);
		}
	}

	// sendtask 유저 막기
	@Override
	@Transactional
	public List<UserDto> gUsers(Long projectId) {
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
		return users;
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
	public boolean recevieTask(Long taskId, Integer selected) {
		Task task = taskRepository.getById(taskId);
		if (selected == -1 && taskRepository.save(task) != null) {
			task.setTaskAccept(-1);
			taskRepository.save(task);
			return true;
		} else if (selected == 1 && taskRepository.save(task) != null) {
			task.setTaskAccept(1);
			taskRepository.save(task);
			return true;
		} else {
			return false;
		}
	}
}
