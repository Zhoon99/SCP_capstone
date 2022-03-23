package kr.mmgg.scp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectinUserService {
	
    private ProjectinUserRepository projectinUserRepository;
    private TaskRepository taskRepository;

    @Transactional
    public void test1(Long id) {
        ProjectInUser user = projectinUserRepository.findByProjectinuserId(id);
        System.out.println(user.getUser().getUserNickname());
        System.out.println(user.getProject().getProjectName());
    }

    @Transactional
    public void test2(Long projectinuser_id) {
        System.out.println(taskRepository.findTop3ByProjectinuserId(projectinuser_id));
    }
}
