package kr.mmgg.scp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectinUserServiceTest {
    @Autowired
    private ProjectinUserService projectinUserService;

    @Test
    void testTest1() {
        projectinUserService.test1(1L);
    }

    @Test
    void test2() {
        projectinUserService.test2(1L);
    }
}
