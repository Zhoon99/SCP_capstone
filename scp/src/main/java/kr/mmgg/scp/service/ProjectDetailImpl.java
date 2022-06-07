package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.request.CommentModifyDto;
import kr.mmgg.scp.dto.request.CommentWriteDto;
import kr.mmgg.scp.dto.request.ModifyProjectDto;
import kr.mmgg.scp.dto.response.HomeViewProjectDetailCommentListDto;
import kr.mmgg.scp.dto.response.HomeViewProjectDetailDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.ProjectUpdateGetInfoDto;
import kr.mmgg.scp.dto.response.ProjectUpdateGetInfoMemberDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;
import kr.mmgg.scp.dto.response.ScpTaskFileListDto;
import kr.mmgg.scp.entity.ChatinUser;
import kr.mmgg.scp.entity.Comment;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.ScpFile;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.ChatinuserRepository;
import kr.mmgg.scp.repository.CommentRepository;
import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.ScpFileRepository;
import kr.mmgg.scp.repository.TaskRepository;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.Mailutils;
import kr.mmgg.scp.util.dateTime;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectDetailImpl implements ProjectDetailService {

	private ProjectinUserRepository projectinUserRepository;
	private TaskRepository taskRepository;
	private UserRepository userRepository;
	private CommentRepository commentRepository;
	private JavaMailSender javaMailSender;
	private ScpFileRepository scpFileRepository;
	private ProjectRepository projectRepository;
	private ChatinuserRepository chatinuserRepository;

	// SCP-301 프로젝트 모든 할일
	// 프로젝트안의 전체 할일 가져오기
	// ResultDto 완성
	// @param projectId
	@Transactional
	@Override
	public ResultDto<List<ProjectDetailAllTaskDto>> allTask(Long projectId) {
		List<ProjectInUser> plist = projectinUserRepository.findByProjectId(projectId);
		if (plist.isEmpty()) {
			throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
		}

		List<ProjectDetailAllTaskDto> list = new ArrayList<ProjectDetailAllTaskDto>();
		ProjectDetailAllTaskDto dto = null;
		for (int i = 0; i < plist.size(); i++) {
			if (!plist.get(i).getTasks().isEmpty()) {
				for (int j = 0; j < plist.get(i).getTasks().size(); j++) {
					if (plist.get(i).getTasks().get(j).getTaskAccept().equals(1)) {
						dto = new ProjectDetailAllTaskDto();
						dto.setProjectinuserId(plist.get(i).getProjectinuserId());
						dto.setTaskId(plist.get(i).getTasks().get(j).getTaskId());
						dto.setTaskContent(plist.get(i).getTasks().get(j).getTaskContent());
						dto.setTaskOwner_string(projectinUserRepository.findById(plist.get(i).getProjectinuserId())
								.get().getUser().getUserNickname());
						dto.setTaskRequester_string(
								projectinUserRepository.findById(plist.get(i).getTasks().get(j).getTaskRequester())
										.get().getUser().getUserNickname());
						;
						// dto.setTaskOwner(plist.get(i).getTasks().get(j).getTaskOwner());
						dto.setTaskRequester(plist.get(i).getTasks().get(j).getTaskRequester());
						dto.setTaskComplete(plist.get(i).getTasks().get(j).getTaskComplete());
						dto.setTaskAccept(plist.get(i).getTasks().get(j).getTaskAccept());
						dto.setTaskRequesttime(plist.get(i).getTasks().get(j).getTaskRequesttime());
						dto.setTaskDeadline(plist.get(i).getTasks().get(j).getTaskDeadline());
						dto.setTaskCreatetime(plist.get(i).getTasks().get(j).getTaskCreatetime());
						list.add(dto);
					}
				}
			}
		}
		ResultDto<List<ProjectDetailAllTaskDto>> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, list, "tasklist");
		return rDto;
	}

	// SCP-302 프로젝트 자신 할일
	// 프로젝트의 자신의 할일 가져오기
	// ResultDto 완성
	@Override
	@Transactional
	public ResultDto<List<ProjectDetailMyTaskDto>> myTask(Long userId, Long projectId) {
		ProjectInUser piuUserIdAndProjectId = projectinUserRepository.findByUserIdAndProjectId(userId, projectId)
				.orElseThrow(() -> new CustomException(ErrorCode.PROJECT_OR_USER_NOT_FOUND));
		List<ProjectDetailMyTaskDto> list = new ArrayList<ProjectDetailMyTaskDto>();
		ProjectDetailMyTaskDto dto = null;
		for (int i = 0; i < piuUserIdAndProjectId.getTasks().size(); i++) {
			dto = new ProjectDetailMyTaskDto();
			dto.setProjectinuserId(piuUserIdAndProjectId.getTasks().get(i).getProjectinuserId());
			dto.setTaskOwner_string(
					projectinUserRepository.findById(piuUserIdAndProjectId.getTasks().get(i).getProjectinuserId()).get()
							.getUser().getUserNickname());
			dto.setTaskId(piuUserIdAndProjectId.getTasks().get(i).getTaskId());
			dto.setTaskContent(piuUserIdAndProjectId.getTasks().get(i).getTaskContent());
			dto.setTaskRequester(piuUserIdAndProjectId.getTasks().get(i).getTaskRequester());
			dto.setTaskRequester_string(
					projectinUserRepository.findById(piuUserIdAndProjectId.getTasks().get(i).getTaskRequester()).get()
							.getUser().getUserNickname());
			dto.setTaskComplete(piuUserIdAndProjectId.getTasks().get(i).getTaskComplete());
			dto.setTaskAccept(piuUserIdAndProjectId.getTasks().get(i).getTaskAccept());
			dto.setTaskRequesttime(piuUserIdAndProjectId.getTasks().get(i).getTaskRequesttime());
			dto.setTaskDeadline(piuUserIdAndProjectId.getTasks().get(i).getTaskDeadline());
			dto.setTaskCreatetime(piuUserIdAndProjectId.getTasks().get(i).getTaskCreatetime());
			list.add(dto);
		}
		ResultDto<List<ProjectDetailMyTaskDto>> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, list, "tasklist");
		return rDto;
	}

	// SCP-303 받은 요청 확인
	// 해당 프로젝트안에서 받은 할일 확인하기
	// ResultDto 완성
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
				pdrtTask.setTaskOwner_string(projectinUserRepository.findById(tlist.get(i).getProjectinuserId()).get()
						.getUser().getUserNickname());
				pdrtTask.setTaskId(tlist.get(i).getTaskId());
				pdrtTask.setTaskContent(tlist.get(i).getTaskContent());
				pdrtTask.setTaskRequester(tlist.get(i).getTaskRequester());
				pdrtTask.setTaskRequester_string(projectinUserRepository.findById(tlist.get(i).getTaskRequester()).get()
						.getUser().getUserNickname());
				pdrtTask.setTaskComplete(tlist.get(i).getTaskComplete());
				pdrtTask.setTaskAccept(tlist.get(i).getTaskAccept());
				pdrtTask.setTaskRequesttime(tlist.get(i).getTaskRequesttime());
				pdrtTask.setTaskDeadline(tlist.get(i).getTaskDeadline());
				pdrtTask.setTaskCreatetime(tlist.get(i).getTaskCreatetime());
				pdrtList.add(pdrtTask);
			}
		}
		ResultDto<List<ProjectDetailReceiveTaskDto>> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, pdrtList, "tasklist");
		return rDto;
	}

	// SCP-304 보낸 요청 확인
	// 해당 프로젝트 안의 보낸 할일 확인하기
	// ResultDto 완성
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
				for (Task task : plist.get(i).getTasks()) {
					if (task.getTaskRequester() == projectinUserRepository
							.findByUserIdAndProjectId(user.getUserId(), projectId).get().getProjectinuserId()) {
						dto = new RequestTaskDto();
						dto.setTaskId(task.getTaskId());
						dto.setProjectinuserId(task.getProjectinuserId());
						dto.setTaskOwner_string(projectinUserRepository.findById(task.getProjectinuserId()).get()
								.getUser().getUserNickname());
						dto.setTaskRequester(task.getTaskRequester());
						dto.setTaskRequester_string(projectinUserRepository.findById(task.getTaskRequester()).get()
								.getUser().getUserNickname());
						dto.setTaskContent(task.getTaskContent());
						dto.setTaskComplete(task.getTaskComplete());
						dto.setTaskAccept(task.getTaskAccept());
						dto.setTaskRequesttime(task.getTaskRequesttime());
						dto.setTaskDeadline(task.getTaskDeadline());
						dto.setTaskCreatetime(task.getTaskCreatetime());
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
	// ResultDto 완성
	@Override
	@Transactional
	public ResultDto<?> sendTask(ProjectDetailSendTaskDto dto) {
		try {
			Mailutils mailutils = new Mailutils(javaMailSender);
			Task task = new Task();
			ProjectInUser projectinuser = new ProjectInUser();
			projectinuser = projectinUserRepository.findByUserIdAndProjectId(dto.getUserId(), dto.getProjectId())
					.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
			dateTime datetime = new dateTime();
			task.setTaskId(null);
			// task.setTaskOwner(userRepository.findById(dto.getUserId()).get().getUserNickname());
			// // 받는 사람
			task.setTaskRequester(projectinuser.getProjectinuserId()); // 보낸 사람
			task.setProjectinuserId(dto.getProjectinuserId());
			task.setTaskContent(dto.getTaskContent());
			task.setTaskCreatetime(datetime.dateTime());
			task.setTaskRequesttime(datetime.dateTime());
			task.setTaskDeadline(dto.getTaskDeadline());
			task.setTaskAccept(0);
			task.setTaskComplete(0);

			String toEmail = projectinUserRepository.findById(dto.getProjectinuserId()).get().getUser().getUserEmail();
			mailutils.setFrom("chjh827@gmail.com");
			mailutils.setTo(toEmail);
			mailutils.setSubject("할일 요청 드립니다.");
			mailutils.setText(dto.getTaskContent(), true);

			mailutils.send();
			if (taskRepository.save(task) == null) {
				throw new CustomException(ErrorCode.TASK_NOT_FOUND);
			} else {
				ResultDto<Task> rDto = new ResultDto<>();
				return rDto.makeResult(CustomStatusCode.CREATE_SUCCESS, task, "createdTask");
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 파일 에러 만들기
		throw new CustomException(ErrorCode.PAGE_NOT_FOUND);
	}

	// SCP-305 프로젝트 할일 요청시 프로젝트 안 사람들 불러오기
	// ResultDto 완성
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

	// SCP-302 할일 수락 / 거절
	// ResultDto 완성
	@Override
	@Transactional
	public ResultDto<?> whetherTask(Long userId, Long taskId) {
		Task task = taskRepository.findByTaskId(taskId)
				.orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
		if (userId == task.getProjectinuser().getUserId()) {
			if (task.getTaskComplete() == 0) {
				task.setTaskComplete(1);
			} else {
				task.setTaskComplete(0);
			}
			taskRepository.save(task);
			ResultDto<?> rDto = new ResultDto<>();
			rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS);
			return rDto;
		} else {
			throw new CustomException(ErrorCode.TASK_NOT_MATCH);
		}
	}

	// SCP-303 받은요청 수락 / 거절
	// ResultDto 완성
	@Override
	@Transactional
	public ResultDto<?> receiveTask(Long taskId, Integer selected) {
		if (selected == -1) {
			ResultDto<?> rDto = new ResultDto<>();
			Task task = taskRepository.findByTaskId(taskId)
					.orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
			task.setTaskAccept(selected);
			taskRepository.save(task);
			rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS);
			return rDto;
		} else if (selected == 1) {
			ResultDto<?> rDto = new ResultDto<>();
			Task task = taskRepository.findByTaskId(taskId)
					.orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
			task.setTaskAccept(selected);
			taskRepository.save(task);
			rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS);
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

		pInUsers.stream().forEach(p -> {
			ProjectUpdateGetInfoMemberDto user = new ProjectUpdateGetInfoMemberDto();
			user.setUserId(p.getUserId());
			user.setProjectinuserId(p.getProjectinuserId());
			user.setNickName(p.getUser().getUserNickname());
			user.setProjectinuserCommoncode(p.getProjectinuserCommoncode());
			users.add(user);
		});

		pUpdateGetDto.setProjectName(pInUsers.get(0).getProject().getProjectName());
		pUpdateGetDto.setUsers(users);
		ResultDto<ProjectUpdateGetInfoDto> rDto = new ResultDto<ProjectUpdateGetInfoDto>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, pUpdateGetDto,
				"projectInfo");
		return rDto;
	}

	@Override
	@Transactional
	public ResultDto<?> updateProjectDeleteMember(Long ProjectinuserId) {
		ProjectInUser pInUser = projectinUserRepository.findById(ProjectinuserId)
				.orElseThrow(() -> new CustomException(ErrorCode.PROJECT_IN_USER_NOT_FOUND));
		projectinUserRepository.delete(pInUser);
		return new ResultDto<>().makeResult(CustomStatusCode.MODIFY_SUCCESS, null,
				null);
	}

	// 홈뷰 -> 자세히 -> 할일 확인 및 코멘트 확인 -> 코멘트 작성
	@Override
	public ResultDto<?> commentWrite(CommentWriteDto dto) {
		Comment comment = new Comment();
		dateTime datetime = new dateTime();
		comment.setTaskId(dto.getTaskId());
		comment.setUserId(dto.getUserId());
		comment.setCommentContent(dto.getCommentContent());
		comment.setCommentTime(datetime.dateTime());
		commentRepository.save(comment);
		// TODO Auto-generated method stub
		return new ResultDto<>().makeResult(CustomStatusCode.CREATE_SUCCESS);
	}

	// 홈뷰 -> 자세히 -> 할일 확인 및 코멘트 확인 -> 코멘트 삭제
	@Override
	public ResultDto<?> commentDelete(Long commentId) {
		commentRepository.deleteById(commentId);
		return new ResultDto<>().makeResult(CustomStatusCode.DELETE_SUCCESS);
	}

	// 홈뷰 -> 자세히 -> 할일 확인 및 코멘트 확인
	@Override
	public ResultDto<?> taskDetail(Long taskId) {
		Task task = taskRepository.findByTaskId(taskId)
				.orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
		List<ScpFile> fScpFile = scpFileRepository.findByTaskId(taskId);
		ProjectInUser projectinuser = new ProjectInUser();
		List<Comment> comment = null;
		List<HomeViewProjectDetailCommentListDto> hvpdclList = new ArrayList<HomeViewProjectDetailCommentListDto>(); // 코멘트dto
		HomeViewProjectDetailCommentListDto hvpdclDto;
		if (!commentRepository.findByTaskId(taskId).isEmpty()) { // 에러 잡는곳
			comment = commentRepository.findByTaskId(taskId);
			for (int i = 0; i < comment.size(); i++) {
				hvpdclDto = new HomeViewProjectDetailCommentListDto();
				hvpdclDto.setTaskId(comment.get(i).getTaskId());
				hvpdclDto.setCommentNickname(comment.get(i).getUser().getUserNickname());
				hvpdclDto.setCommentId(comment.get(i).getCommentId());
				hvpdclDto.setCommentuserId(comment.get(i).getUserId());
				hvpdclDto.setCommentTime(comment.get(i).getCommentTime());
				hvpdclDto.setCommentContent(comment.get(i).getCommentContent());
				hvpdclList.add(hvpdclDto);
			}
		} else {
			new CustomException(ErrorCode.COMMENT_NOT_FOUND); // 에러발생시
		}

		List<ScpTaskFileListDto> stflList = new ArrayList<>();
		for (ScpFile file : fScpFile) {
			ScpTaskFileListDto stfl = new ScpTaskFileListDto();
			stfl.setScpFilePath(file.getFilePath());
			stfl.setScpFilename(file.getFileName());
			stflList.add(stfl);
		}
		HomeViewProjectDetailDto hvpdDto = new HomeViewProjectDetailDto();
		projectinuser =  projectinUserRepository.findById(task.getTaskRequester()).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
		hvpdDto.setTaskId(task.getTaskId());
		hvpdDto.setTaskContent(task.getTaskContent());
		hvpdDto.setTaskOwnerId(task.getProjectinuserId());
		hvpdDto.setTaskOwner_string(task.getProjectinuser().getUser().getUserNickname()); // 조인해서 데이터 가져올것
		hvpdDto.setTaskRequester_string(projectinuser.getUser().getUserNickname()); // ##
		hvpdDto.setTaskDeadline(task.getTaskDeadline());
		hvpdDto.setCommentList(hvpdclList);
		return new ResultDto<>().makeResult(CustomStatusCode.LOOKUP_SUCCESS, hvpdDto, "taskDetail"); // 새로작성한
	}

	// 홈뷰 -> 자세히 -> 할일 확인 및 코멘트 확인 -> 코멘트 수정
	@Override
	public ResultDto<?> commentModify(Long commentId, CommentModifyDto cmDto) {
		Comment comment = commentRepository.findByCommentId(commentId)
				.orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
		dateTime datetime = new dateTime();
		comment.setCommentContent(cmDto.getCommentContent());
		comment.setCommentTime(datetime.dateTime());
		commentRepository.save(comment);
		return new ResultDto<>().makeResult(CustomStatusCode.MODIFY_SUCCESS);
	}

	@Override
	public ResultDto<?> modifyProject(Long projectId, ModifyProjectDto modifyProjectDto) {
		Project project = projectRepository.getById(projectId);
		project.setProjectName(modifyProjectDto.getTitle());

		List<ProjectInUser> piulist = new ArrayList<ProjectInUser>();
		ProjectInUser piu;
		List<ChatinUser> ciulist = new ArrayList<ChatinUser>();
		ChatinUser ciu;
		for (int i = 0; i < modifyProjectDto.getMember().size(); i++) {
			if (projectinUserRepository
					.findByUserIdAndProjectId(modifyProjectDto.getMember().get(i).getUserId(), projectId).isEmpty()) {
				piu = new ProjectInUser();
				piu.setProjectId(project.getProjectId());
				piu.setProjectinuserCommoncode(modifyProjectDto.getMember().get(i).getProjectinuserCommoncode());
				piu.setProjectinuserMaker(modifyProjectDto.getMember().get(i).getProjectinuserMaker());
				piu.setUserId(modifyProjectDto.getMember().get(i).getUserId());
				piulist.add(piu);
				ciu = new ChatinUser();
				if (modifyProjectDto.getMember().get(i).getProjectinuserCommoncode().equals("p-leader")) {
					ciu.setChatinuserCommoncode("c-leader");
				} else {
					ciu.setChatinuserCommoncode("c-member");
				}
				ciu.setChatinuserExit(0);
				ciu.setChatroomId(project.getChatroomId());
				ciu.setUserId(modifyProjectDto.getMember().get(i).getUserId());
				ciulist.add(ciu);
			}
		}
		chatinuserRepository.saveAll(ciulist);
		projectinUserRepository.saveAll(piulist);
		projectRepository.save(project); // 프로젝트 수정
		return new ResultDto<>().makeResult(CustomStatusCode.MODIFY_SUCCESS);
	}
}
