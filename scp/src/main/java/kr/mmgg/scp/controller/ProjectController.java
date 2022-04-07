package kr.mmgg.scp.controller;

import kr.mmgg.scp.service.ProjectDetailImpl;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.ErrorResponse;

import org.springframework.web.bind.annotation.*;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskSelectDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.service.HomeServicelmpl;
import kr.mmgg.scp.service.ProjectDetailImpl;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@AllArgsConstructor
public class ProjectController {

    private HomeServicelmpl homeServiceImpl;
    private ProjectDetailImpl projectDetailImpl;

    // SCP-300 프로젝트 추가 
    // TODO: request DTO 작성
    @PostMapping(value = "/createproject")
    public ResponseEntity<List<ProjectInUser>> CreateProject(@RequestBody CreateProjectDto dto) {
        List<ProjectInUser> piuList = homeServiceImpl.projectCreate(dto);
        return (!piuList.isEmpty() || piuList != null) ? ResponseEntity.status(HttpStatus.OK).body(piuList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // SCP-301 프로젝트 모든 할일
    // ResultDto 완성
    @Transactional
    @RequestMapping(value = "/alltask/{projectId}", method = RequestMethod.GET)
    public ResultDto<?> allTask(@PathVariable Long projectId) {
        HashMap<String,List<ProjectDetailAllTaskDto>> map = new HashMap<>();
        List<ProjectDetailAllTaskDto> pdatList = projectDetailImpl.allTask(projectId);
        map.put("tasklist", pdatList);
        ResultDto<List<ProjectDetailAllTaskDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, map);
        return rDto;
    }

    // SCP-302 프로젝트 자신 할일
    // ResultDto 완성
    @Transactional // 영속성 컨텐츠로인해 안해주면 no session 에러남 (서비스뿐만아니라 컨트롤러단에서도 관리해줘야됨)
    @GetMapping(value = "/mytask/{userId}/{projectId}")
    public ResultDto<?> myTask(@PathVariable Long userId, @PathVariable Long projectId) {
    	HashMap<String,List<ProjectDetailMyTaskDto>> map = new HashMap<>();
        List<ProjectDetailMyTaskDto> pdmtList = projectDetailImpl.myTask(userId, projectId);
        map.put("tasklist", pdmtList);
        ResultDto<List<ProjectDetailMyTaskDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, map);
        return rDto;
    }

    @PatchMapping(value = "/whethertask/{userId}/{taskId}")
    public ResponseEntity<?> whetherTask(@PathVariable Long userId, @PathVariable Long taskId) {
        projectDetailImpl.whetherTask(userId, taskId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // SCP-303 받은 요청 확인
    // ResultDto 완성
    @Transactional
    @RequestMapping(value = "/receivetask/{projectId}/{projectinuserID}", method = RequestMethod.GET)
    public ResultDto<?> receivetask(@PathVariable Long projectId, @PathVariable Long projectinuserID) {
    	HashMap<String,List<ProjectDetailReceiveTaskDto>> map = new HashMap<>();
        List<ProjectDetailReceiveTaskDto> pdrtList = projectDetailImpl.receiveTask(projectId, projectinuserID);
        map.put("tasklist", pdrtList);
        ResultDto<List<ProjectDetailReceiveTaskDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, map);
        return rDto;
    }

    //TODO: 서비스 부분에서 resultDto 만들것 **
    // ResultDto 완성
    @RequestMapping(value = "/receivetask/{taskId}/{selected}", method = RequestMethod.PATCH)
    public ResultDto<?> receivetask(@PathVariable Long taskId, @PathVariable Integer selected) {
    	ResultDto<?> rDto = new ResultDto<>();
    	projectDetailImpl.recevieTask(taskId, selected);
    	rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS, null);
		return rDto;
    }

    // SCP-304 보낸 요청 확인
    // TODO: 프로젝트와 유저가 없으면 오류 
    // ResultDto 완성
    @GetMapping(value = "/requestask/{projectId}/{userid}")
    public ResultDto<?> requesttask(@PathVariable Long projectId, @PathVariable Long userid) {
    	HashMap<String,List<RequestTaskDto>> map = new HashMap<>();
        List<RequestTaskDto> list = projectDetailImpl.requestTask(projectId, userid);
        map.put("tasklist", list);
        ResultDto<List<RequestTaskDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, map);
        return rDto;
    }

    // SCP-305 프로젝트 할일 요청시 프로젝트 안 사람들 불러오기
    // ResultDto 완성
    @Transactional
    @GetMapping(value = "/sendtask/{projectId}")
    public ResultDto<List<UserDto>> sendTask(@PathVariable Long projectId) {
    	HashMap<String, List<UserDto>> map = new HashMap<>();
    	List<UserDto> users = projectDetailImpl.gUsers(projectId);
        map.put("userlist", users);
        ResultDto<List<UserDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, map);
        return rDto;
    }

    // SCP-305 프로젝트 할일 보내는 작업
    // ResultDto 완성
    @Transactional
    @RequestMapping(value = "/sendtask", method = RequestMethod.POST)
    public ResultDto<?> sendTask(@RequestBody ProjectDetailSendTaskDto dto) {
        projectDetailImpl.sendTask(dto);
        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.CREATE_SUCCESS, null);
        return rDto;
         
    }
}
