package kr.mmgg.scp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.mmgg.scp.repository.ProjectinUserRepository;

@Service
public class ProejctinUserService {
    @Autowired
    private ProjectinUserRepository projectinUserRepository;

    public void test1(Long id) {
        System.out.println(projectinUserRepository.findById(id));
    }
}
