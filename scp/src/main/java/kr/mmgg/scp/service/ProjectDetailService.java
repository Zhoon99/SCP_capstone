package kr.mmgg.scp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailRequestTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;

public interface ProjectDetailService {

	public List<ProjectDetailMyTaskDto> myTask(Long userId, Long projectId);

	public List<ProjectDetailAllTaskDto> allTask(Long userId);

	public List<ProjectDetailReceiveTaskDto> receiveTask(Long projectId, Long projectinuserID);

	public boolean recevieTask(Long taskId, Integer selected);

	public List<RequestTaskDto> requestTask(Long projectinuserID, Long userId);

	public boolean sendTask(ProjectDetailSendTaskDto dto);

	public List<UserDto> gUsers(Long projectId);

	public void whetherTask(Long userId, Long taskId);
}
