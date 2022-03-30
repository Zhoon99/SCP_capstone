package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.ProjectDetailRequestTaskDto;
import kr.mmgg.scp.dto.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.RequestTask;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import kr.mmgg.scp.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
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
	public List<RequestTask> requestTask(Long projectId, Long userid) {
		List<ProjectInUser> plist = projectinUserRepository.findByProjectId(projectId);
		User user = userRepository.findByUserId(userid);
		List<RequestTask> list = new ArrayList<>();
		RequestTask dto;
		// 해당 프로젝트의 모든 할일 가져오기
		for (int i = 0; i < plist.size(); i++) {
			dto = new RequestTask();
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
	public List<Task> sendTask(ProjectDetailSendTaskDto dto) {
		List<Task> tlist = dto.getTasklist();
		User user = userRepository.findByUserId(dto.getUserId()); // 유저번호는 일단 받아둠
		return taskRepository.saveAll(tlist);
	}
}
