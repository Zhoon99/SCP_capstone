package kr.mmgg.scp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectDetailImplTest {
    @Autowired
    private ProjectDetailImpl projectDetailImpl;

    @Test
    void testMyTask() {
        projectDetailImpl.myTask(3L, 1L);
    }
}
