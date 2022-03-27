package kr.mmgg.scp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.mmgg.scp.repository.ProjectinUserRepository;

@SpringBootTest
public class homeServiceTest {
    @Autowired
    private HomeServicelmpl homeService;

    @Autowired
    private ProjectDetailImpl impl;

    @Test
    void testallproject() {
        impl.allTask(1L);
    }

}
