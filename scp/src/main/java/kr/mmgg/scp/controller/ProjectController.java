package kr.mmgg.scp.controller;

import org.springframework.web.bind.annotation.RestController;

import kr.mmgg.scp.dto.CreateProjectDto;
import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
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
    private HomeServicelmpl homeService;
    private ProjectDetailImpl projectDetailImpl;

    @PostMapping(value = "/createproject")
    public ResponseEntity<List<ProjectInUser>> CreateProject(@RequestBody CreateProjectDto dto) {
        List<ProjectInUser> piuList = homeService.projectCreate(dto);
        return (piuList != null) ? ResponseEntity.status(HttpStatus.OK).body(piuList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @RequestMapping(value = "/mytask/{userId}/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDetailMyTaskDto> myTask(@PathVariable Long userId, @PathVariable Long projectId) {
        ProjectDetailMyTaskDto pdmtList = projectDetailImpl.myTask(userId, projectId);
        System.out.println(pdmtList);
        return (pdmtList != null) ? ResponseEntity.status(HttpStatus.OK).body(pdmtList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @RequestMapping(value = "/alltask/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDetailAllTaskDto>> allTask(@PathVariable Long projectId) {
    	List<ProjectDetailAllTaskDto> pdatList = projectDetailImpl.allTask(projectId);
		return (pdatList != null) ? ResponseEntity.status(HttpStatus.OK).body(pdatList)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
