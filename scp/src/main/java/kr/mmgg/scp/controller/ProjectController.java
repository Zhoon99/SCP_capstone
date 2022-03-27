package kr.mmgg.scp.controller;

import org.springframework.web.bind.annotation.RestController;

import kr.mmgg.scp.dto.CreateProjectDto;
import kr.mmgg.scp.entity.Project;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.repository.ProjectRepository;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class ProjectController {
    private ProjectinUserRepository projectinUserRepository;
    private ProjectRepository projectRepository;

    @Transactional
    @PostMapping(value = "/createproject")
    public void CreateProject(@RequestBody CreateProjectDto dto) {
        List<ProjectInUser> piuList = new ArrayList<>();
        Project project = new Project();
        // System.out.println(dto.toString());
        project.setProjectName(dto.getTitle());
        Project newProject = projectRepository.save(project);
        for (int i = 0; i < dto.getMember().size(); i++) {
            ProjectInUser projectInUser = new ProjectInUser();
            projectInUser.setProjectId(newProject.getProjectId());
            projectInUser.setUserId(dto.getMember().get(i).getUserId());
            projectInUser.setProjectinuserMaker(dto.getMember().get(i).getProjectinuserMaker());
            projectInUser.setProjectinuserCommoncode(dto.getMember().get(i).getProjectinuserCommoncode());
            piuList.add(projectInUser);
        }
        projectinUserRepository.saveAll(piuList);

    }
}
