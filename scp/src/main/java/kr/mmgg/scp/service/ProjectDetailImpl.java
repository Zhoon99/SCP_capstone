package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.ProjectDetailRequestTaskDto;
import kr.mmgg.scp.dto.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.RequestTaskDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import kr.mmgg.scp.repository.UserRepository;
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
		ArrayList<ProjectDetailAllTaskDto> list = new ArrayList<ProjectDetailAllTaskDto>();
		ProjectDetailAllTaskDto dto;
		for (int i = 0; i < plist.size(); i++) {
			dto = new ProjectDetailAllTaskDto();
			if (!plist.get(i).getTasks().isEmpty()) {
				dto.setTasklist(plist.get(i).getTasks());
				list.add(dto);
				// System.out.println(dto.toString());
			}
		}
		return list;
	}

	// 프로젝트의 자신의 할일 가져오기
	@Override
	@Transactional
	public ProjectDetailMyTaskDto myTask(Long userId, Long projectId) {
		ProjectInUser piuUserIdAndProjectId = projectinUserRepository.findByUserIdAndProjectId(userId, projectId);
		ProjectDetailMyTaskDto pdMyTask = new ProjectDetailMyTaskDto();
		pdMyTask.setTaskList(piuUserIdAndProjectId.getTasks());
		// System.out.println(piuUserIdAndProjectId);
		return pdMyTask;
	}

	// 해당 프로젝트안에서 받은 할일 확인하기
	@Override
	public List<ProjectDetailReceiveTaskDto> receiveTask(Long projectId, Long projectinuserId) {
		List<Task> tlist = taskRepository.findByProjectinuserIdAndTaskAccept(projectinuserId, 0);
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
		User user = userRepository.findByUserId(userid);
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
			return false;
		}
	}

	// sendtask 유저 막기
	@Override
	@Transactional
	public List<UserDto> gUsers(Long projectId) {
		List<ProjectInUser> projectInUsers = projectinUserRepository.findByProjectId(projectId);
		List<UserDto> users = new ArrayList<>();
		UserDto user;
		for (ProjectInUser projectInUser : projectInUsers) {
			user = new UserDto(projectInUser.getUser());
			users.add(user);
		}
		return users;
	}

<<<<<<< HEAD
	// 할일 완료여부 체크
	@Override
	@Transactional
	public void whetherTask(Long userId, Long taskId) {
		Task task = taskRepository.findByTaskId(taskId);
		if (userId == task.getProjectinuser().getUserId()) {
			if (task.getTaskComplete() == 0) {
				task.setTaskComplete(1);
			} else {
				task.setTaskComplete(0);
			}
			taskRepository.save(task);
		}
		log.info(task.toString());
=======
	//해당 프로젝트 안의 할일 수락 및 거절 하기
	@Override
	public boolean recevieTask(Long taskId, Integer selected) {
		Task task = taskRepository.getById(taskId);
		if(selected == -1 && taskRepository.save(task) != null) {
			task.setTaskAccept(-1);
			taskRepository.save(task);
			return true;
		} else if(selected == 1 && taskRepository.save(task) != null){
			task.setTaskAccept(1);
			taskRepository.save(task);
			return true;
		} else {
			return false;
		}
>>>>>>> origin/dustjd
	}
}
