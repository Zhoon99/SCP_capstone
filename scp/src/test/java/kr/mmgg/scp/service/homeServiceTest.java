package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.request.CreateProjectMemberDto;
import kr.mmgg.scp.dto.response.HomeViewDto;
import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class homeServiceTest {
    @Autowired
    private HomeServicelmpl homeServiceImpl;

    @Autowired
    private ProjectDetailImpl impl;

    private MockMvc mvc;

    @Test
    @Transactional
    void testHomeView() {
        // List<HomeViewDto> dto = homeServiceImpl.homeView(1L);
        // log.info(dto.toString());

    }

    @Transactional
    @Test
    void testCreateProject() {
        // CreateProjectMemberDto memberDto = new CreateProjectMemberDto(1L, 0,
        // "p_member");
        // List<CreateProjectMemberDto> memberDtoList = new ArrayList<>();
        // memberDtoList.add(memberDto);
        // CreateProjectDto projectDto = new CreateProjectDto("테스트 타이틀", memberDtoList);
        // List<ProjectInUser> dto = homeServiceImpl.projectCreate(projectDto);
        // log.info(dto.toString());
    }
}
