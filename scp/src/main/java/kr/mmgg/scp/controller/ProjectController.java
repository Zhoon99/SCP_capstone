package kr.mmgg.scp.controller;

import kr.mmgg.scp.service.ProjectDetailImpl;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.ErrorResponse;

import org.springframework.web.bind.annotation.*;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.resultDto;
import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskSelectDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;
import kr.mmgg.scp.dto.response.tasklist;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.service.HomeServicelmpl;
import kr.mmgg.scp.service.ProjectDetailImpl;
import lombok.AllArgsConstructor;

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
    @Transactional
    @RequestMapping(value = "/alltask/{projectId}", method = RequestMethod.GET)
    public resultDto allTask(@PathVariable Long projectId) {
    	tasklist<List<ProjectDetailAllTaskDto>> pdatList = projectDetailImpl.allTask(projectId);
        resultDto rDto = new resultDto(200,"Success",pdatList);
        return rDto;
    }

    // SCP-302 프로젝트 자신 할일
    @Transactional // 영속성 컨텐츠로인해 안해주면 no session 에러남 (서비스뿐만아니라 컨트롤러단에서도 관리해줘야됨)
    @GetMapping(value = "/mytask/{userId}/{projectId}")
    public ResponseEntity<ProjectDetailMyTaskDto> myTask(@PathVariable Long userId, @PathVariable Long projectId) {
        ProjectDetailMyTaskDto pdmtList = projectDetailImpl.myTask(userId, projectId);
        return (pdmtList != null) ? ResponseEntity.status(HttpStatus.OK).body(pdmtList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping(value = "/whethertask/{userId}/{taskId}")
    public ResponseEntity<?> whetherTask(@PathVariable Long userId, @PathVariable Long taskId) {
        projectDetailImpl.whetherTask(userId, taskId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // SCP-303 받은 요청 확인
    @Transactional
    @RequestMapping(value = "/receivetask/{projectId}/{projectinuserID}", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDetailReceiveTaskDto>> receivetask(@PathVariable Long projectId,
            @PathVariable Long projectinuserID) {
        List<ProjectDetailReceiveTaskDto> pdrtList = projectDetailImpl.receiveTask(projectId, projectinuserID);
        return (!pdrtList.isEmpty() || pdrtList != null) ? ResponseEntity.status(HttpStatus.OK).body(pdrtList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/receivetask/{taskId}/{selected}", method = RequestMethod.PATCH)
    public ResponseEntity<List<ProjectDetailReceiveTaskSelectDto>> receivetask(@PathVariable Long taskId,
            @PathVariable Integer selected) {
        if (projectDetailImpl.recevieTask(taskId, selected)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // SCP-304 보낸 요청 확인
    // TODO: 프로젝트와 유저가 없으면 오류
    @GetMapping(value = "/requestask/{projectId}/{userid}")
    public ResponseEntity<List<RequestTaskDto>> requesttask(@PathVariable Long projectId,
            @PathVariable Long userid) {
        List<RequestTaskDto> list = projectDetailImpl.requestTask(projectId, userid);
        return (!list.isEmpty() || list != null) ? ResponseEntity.status(HttpStatus.OK).body(list)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // SCP-305 프로젝트 할일 요청시 프로젝트 안 사람들 불러오기
    @Transactional
    @GetMapping(value = "/sendtask/{projectId}")
    public ResponseEntity<List<UserDto>> sendTask(@PathVariable Long projectId) {
        List<UserDto> users = projectDetailImpl.gUsers(projectId);
        return (!users.isEmpty() || users != null) ? ResponseEntity.status(HttpStatus.OK).body(users)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // SCP-305 프로젝트 할일 보내는 작업
    @Transactional
    @RequestMapping(value = "/sendtask", method = RequestMethod.POST)
    public ResponseEntity<ProjectDetailSendTaskDto> sendTask(@RequestBody ProjectDetailSendTaskDto dto) {
        if (projectDetailImpl.sendTask(dto)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
