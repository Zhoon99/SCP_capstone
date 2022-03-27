package kr.mmgg.scp.controller;

import org.springframework.web.bind.annotation.RestController;

import kr.mmgg.scp.dto.CreateProjectDto;
import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.service.HomeServicelmpl;
import kr.mmgg.scp.service.ProjectDetailImpl;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class ProjectController {
    private HomeServicelmpl homeService;
    private ProjectDetailImpl projectDetailImpl;

    @PostMapping(value = "/createproject")
    public ResponseEntity<List<ProjectInUser>> createProject(@RequestBody CreateProjectDto dto) {
        List<ProjectInUser> piuList = homeService.projectCreate(dto);
        return (piuList != null) ? ResponseEntity.status(HttpStatus.OK).body(piuList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(value = "/mytask/{userId}/{projectId}")
    public ResponseEntity<ProjectDetailMyTaskDto> myTask(@PathVariable Long userId, @PathVariable Long projectId) {
        ProjectDetailMyTaskDto test = projectDetailImpl.myTask(userId, projectId);
        return (test != null) ? ResponseEntity.status(HttpStatus.OK).body(test)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
