package kr.mmgg.scp.controller;

import org.springframework.web.bind.annotation.RestController;

import kr.mmgg.scp.dto.CreateProjectDto;
import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import kr.mmgg.scp.dto.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.dto.ProjectDetailRequestTaskDto;
import kr.mmgg.scp.dto.ProjectDetailSendTaskDto;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.entity.Task;
import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.service.HomeServicelmpl;
import kr.mmgg.scp.service.ProjectDetailImpl;
import kr.mmgg.scp.service.ProjectDetailService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.json.simple.JSONObject;
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

    @PostMapping(value = "/createproject")
    public ResponseEntity<List<ProjectInUser>> CreateProject(@RequestBody CreateProjectDto dto) {
        List<ProjectInUser> piuList = homeServiceImpl.projectCreate(dto);
        return (piuList != null) ? ResponseEntity.status(HttpStatus.OK).body(piuList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @Transactional // 영속성 컨텐츠로인해 안해주면 no session 에러남 (서비스뿐만아니라 컨트롤러단에서도 관리해줘야됨)
    @RequestMapping(value = "/mytask/{userId}/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDetailMyTaskDto> myTask(@PathVariable Long userId, @PathVariable Long projectId) {
        ProjectDetailMyTaskDto pdmtList = projectDetailImpl.myTask(userId, projectId);
        System.out.println(pdmtList);
        return (pdmtList != null) ? ResponseEntity.status(HttpStatus.OK).body(pdmtList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @Transactional
    @RequestMapping(value = "/alltask/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDetailAllTaskDto>> allTask(@PathVariable Long projectId) {
    	List<ProjectDetailAllTaskDto> pdatList = projectDetailImpl.allTask(projectId);
		return (pdatList != null) ? ResponseEntity.status(HttpStatus.OK).body(pdatList)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @Transactional
    @RequestMapping(value = "/sendtask", method = RequestMethod.POST)
    public ResponseEntity<List<Task>> sendTask(@RequestBody ProjectDetailSendTaskDto dto) {
    	 List<Task> tList =  projectDetailImpl.sendTask(dto);
		 return (tList != null) ? ResponseEntity.status(HttpStatus.OK).body(tList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @Transactional
    @RequestMapping(value = "/receivetask/{projectId}/{projectinuserID}", method = RequestMethod.POST)
    public ResponseEntity<List<ProjectDetailReceiveTaskDto>> receivetask(@PathVariable Long projectId, @PathVariable Long projectinuserID){
    	List<ProjectDetailReceiveTaskDto> pdrtList = projectDetailImpl.receiveTask(projectId, projectinuserID);
    	return (pdrtList != null) ? ResponseEntity.status(HttpStatus.OK).body(pdrtList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
