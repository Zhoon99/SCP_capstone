package kr.mmgg.scp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.mmgg.scp.dto.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.ProjectDetailMyTaskDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ProjectDetailImplTest {
    @Autowired
    private ProjectDetailImpl projectDetailImpl;

    @Test
    @Transactional
    void 전체할일가져오기() {
        List<ProjectDetailAllTaskDto> dtoList = projectDetailImpl.allTask(1L);
        log.info(dtoList.toString());
    }

    @Test
    @Transactional
    void 내할일가져오기() {
        ProjectDetailMyTaskDto test = projectDetailImpl.myTask(1L, 1L);
        log.info(test.toString());
    }

}
