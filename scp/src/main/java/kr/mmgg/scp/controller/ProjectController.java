package kr.mmgg.scp.controller;

import kr.mmgg.scp.service.ProjectDetailImpl;
import kr.mmgg.scp.util.CustomStatusCode;

import org.springframework.web.bind.annotation.*;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailReceiveTaskSelectDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.dto.response.ProjectUpdateGetInfoDto;
import kr.mmgg.scp.dto.response.RequestTaskDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.service.HomeServicelmpl;
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

    @GetMapping(value = "/updateproject/{projectId}")
    public ResultDto<ProjectUpdateGetInfoDto> updateProjectGetInfo(@PathVariable Long projectId) {
        ResultDto<ProjectUpdateGetInfoDto> rDto = projectDetailImpl.updateProjectGetInfo(projectId);
        return rDto;
    }

    @PatchMapping(value = "/updateproject/deletemember/{projectinuserId}")
    public ResultDto<?> updateProjectDeletemember(@PathVariable Long projectinuserId) {
        ResultDto<?> rDto = projectDetailImpl.updateProjectDeleteMember(projectinuserId);
        return rDto;
    }

    @PatchMapping(value = "")
    public ResultDto<?> updateProjectMember() {

        return null;
    }

    // @PatchMapping(value = "/updateproject/deleteuser/{projectinuserId}")
    // public ResultDto<?> updateProject(@PathVariable Long projectinuserId){
    // projectDetailImpl.updateProject(projectinuserId);
    // return null;
    // }

    // @PatchMapping(value = "/updateproject/adduser")
    // public ResultDto<?> updateProject(@RequestBody UpdateProjectAddMemberDto
    // uAddMember){

    // return null;
    // }

    // SCP-301 프로젝트 모든 할일
    // ResultDto 완성
    @Transactional
    @RequestMapping(value = "/alltask/{projectId}", method = RequestMethod.GET)
    public ResultDto<List<ProjectDetailAllTaskDto>> allTask(@PathVariable Long projectId) {
        return projectDetailImpl.allTask(projectId);
    }

    // SCP-302 프로젝트 자신 할일
    // ResultDto 완성
    @Transactional // 영속성 컨텐츠로인해 안해주면 no session 에러남 (서비스뿐만아니라 컨트롤러단에서도 관리해줘야됨)
    @GetMapping(value = "/mytask/{userId}/{projectId}")
    public ResultDto<?> myTask(@PathVariable Long userId, @PathVariable Long projectId) {
        return projectDetailImpl.myTask(userId, projectId);
    }
    
    // SCP-302 할일 수락 / 거절
    // ResultDto 완성
    @PatchMapping(value = "/whethertask/{userId}/{taskId}")
    public ResultDto<?> whetherTask(@PathVariable Long userId, @PathVariable Long taskId) {
        return projectDetailImpl.whetherTask(userId, taskId);
    }

    // SCP-303 받은 요청 확인
    // ResultDto 완성
    @Transactional
    @RequestMapping(value = "/receivetask/{projectId}/{projectinuserID}", method = RequestMethod.GET)
    public ResultDto<?> receivetask(@PathVariable Long projectId, @PathVariable Long projectinuserID) {
        return projectDetailImpl.receiveTask(projectId, projectinuserID);
    }

    // SCP-303 받은요청 수락 / 거절
    // TODO: 서비스 부분에서 resultDto 만들것 **
    // ResultDto 완성
    @RequestMapping(value = "/receivetask/{taskId}/{selected}", method = RequestMethod.PATCH)
    public ResultDto<?> receivetask(@PathVariable Long taskId, @PathVariable Integer selected) {
        return projectDetailImpl.receiveTask(taskId, selected);
    }

    // SCP-304 보낸 요청 확인
    // TODO: 프로젝트와 유저가 없으면 오류
    // ResultDto 완성
    @GetMapping(value = "/requestask/{projectId}/{userid}")
    public ResultDto<?> requestask(@PathVariable Long projectId, @PathVariable Long userid) {
        return projectDetailImpl.requestTask(projectId, userid);
    }

    // SCP-305 프로젝트 할일 요청시 프로젝트 안 사람들 불러오기
    // ResultDto 완성
    @Transactional
    @GetMapping(value = "/sendtask/{projectId}")
    public ResultDto<List<UserDto>> sendTask(@PathVariable Long projectId) {
        return projectDetailImpl.gUsers(projectId);
    }

    // SCP-305 프로젝트 할일 보내는 작업
    // ResultDto 완성
    @Transactional
    @RequestMapping(value = "/sendtask", method = RequestMethod.POST)
    public ResultDto<?> sendTask(@RequestBody ProjectDetailSendTaskDto dto) {
        return projectDetailImpl.sendTask(dto);
    }
}
