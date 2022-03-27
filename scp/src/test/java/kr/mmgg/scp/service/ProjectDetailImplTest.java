package kr.mmgg.scp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;

@SpringBootTest
public class ProjectDetailImplTest {
    @Autowired
    private ProjectDetailImpl projectDetailImpl;

    @Test
    @Transactional
    void 전체할일가져오기() {
    	List<ProjectDetailAllTaskDto> pdatList = projectDetailImpl.allTask(1L);
    	for (int i = 0; i < pdatList.size(); i++) {
			System.out.println(pdatList.get(i).getTasklist());
		}
    }
    
    @Test
    @Transactional
    void 내할일가져오기() {
       System.out.println(projectDetailImpl.myTask(1L, 1L));
    }
}
