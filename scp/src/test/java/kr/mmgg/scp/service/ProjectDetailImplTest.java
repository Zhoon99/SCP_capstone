package kr.mmgg.scp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.mmgg.scp.dto.response.ProjectDetailAllTaskDto;
import kr.mmgg.scp.dto.response.ProjectDetailMyTaskDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ProjectDetailImplTest {
    @Autowired
    private ProjectDetailImpl projectDetailImpl;

    // @Test
    // @Transactional
    // void 전체할일가져오기() {
    // List<ProjectDetailAllTaskDto> dtoList = projectDetailImpl.allTask(1L);
    // log.info(dtoList.toString());
    // List<ProjectDetailAllTaskDto> pdatList = projectDetailImpl.allTask(1L);
    // for (int i = 0; i < pdatList.size(); i++) {
    // System.out.println(pdatList.get(i).getTasklist());
    // }
    // }
}
