package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import kr.mmgg.scp.service.ProjectDetailImpl;
import org.springframework.web.bind.annotation.*;

import kr.mmgg.scp.dto.CreateProjectDto;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.service.HomeServicelmpl;
import kr.mmgg.scp.service.ProjectDetailService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
public class ProjectController {
    private HomeServicelmpl homeService;
    private final ProjectDetailImpl projectDetail;

    @PostMapping(value = "/createproject")
    public ResponseEntity<List<ProjectInUser>> CreateProject(@RequestBody CreateProjectDto dto) {
        List<ProjectInUser> piuList = homeService.projectCreate(dto);
        return (piuList != null) ? ResponseEntity.status(HttpStatus.OK).body(piuList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
