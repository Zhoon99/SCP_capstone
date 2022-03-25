package kr.mmgg.scp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.mmgg.scp.repository.ProjectinUserRepository;

@SpringBootTest
public class homeServiceTest {
    @Autowired
    private homeService homeService;

    @Test
    void testHomeView_leader() {
        homeService.homeView_leader(9L);
    }

}
