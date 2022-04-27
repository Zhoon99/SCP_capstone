package kr.mmgg.scp.service;

import java.util.List;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.request.CommentModifyDto;
import kr.mmgg.scp.dto.request.CommentWriteDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.ProjectUpdateGetInfoDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;

public interface ProjectDetailService {

	public ResultDto<List<ProjectDetailMyTaskDto>> myTask(Long userId, Long projectId);

	public ResultDto<List<ProjectDetailAllTaskDto>> allTask(Long userId);

	public ResultDto<?> receiveTask(Long projectId, Long projectinuserID);

	public ResultDto<?> receiveTask(Long taskId, Integer selected);

	public ResultDto<List<RequestTaskDto>> requestTask(Long projectinuserID, Long userId);

	public ResultDto<?> sendTask(ProjectDetailSendTaskDto dto);

	public ResultDto<List<UserDto>> gUsers(Long projectId);

	public ResultDto<?> whetherTask(Long userId, Long taskId);

	public ResultDto<?> updateProjectDeleteMember(Long projectinuserId);

	public ResultDto<ProjectUpdateGetInfoDto> updateProjectGetInfo(Long ProjectId);

	public ResultDto<?> commentWrite(CommentWriteDto dto);

	public ResultDto<?> commentDelete(Long commentId);

	public ResultDto<?> taskDetail(Long taskId);

	public ResultDto<?> commentModify(Long commentId, CommentModifyDto cmDto);
}
