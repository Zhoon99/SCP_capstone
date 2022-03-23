package kr.mmgg.scp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;

@Service
public class ProjectinUserService {
    @Autowired
    private ProjectinUserRepository projectinUserRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public void test1(Long id) {
        ProjectInUser user = projectinUserRepository.findByProjectinuserId(id);
        System.out.println(user.getUser().getUserNickname());
        System.out.println(user.getProject().getProjectName());
    }

    @Transactional
    public void test2(Long Id) {
        System.out.println(taskRepository.findByProjectinuserId(Id));
    }
}
