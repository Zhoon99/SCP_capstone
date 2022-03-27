package kr.mmgg.scp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectDetailImplTest {
    @Autowired
    private ProjectDetailImpl projectDetailImpl;

    @Test
    void 전체할일가져오기() {
    	projectDetailImpl.allTask(1L); 
    }
    
    @Test
    void 내할일가져오기() {
        projectDetailImpl.myTask(1L, 1L);
    }
    
}
