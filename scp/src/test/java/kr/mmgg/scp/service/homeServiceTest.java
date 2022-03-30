package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.mmgg.scp.dto.CreateProjectDto;
import kr.mmgg.scp.dto.CreateProjectMemberDto;
import kr.mmgg.scp.dto.HomeViewDto;
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

    @Test
    @Transactional
    void testHomeView() {
        List<HomeViewDto> dto = homeService.homeView(1L);
        log.info(dto.toString());
    }

    @Transactional
    @Test
    void testCreateProject() {
        CreateProjectMemberDto memberDto = new CreateProjectMemberDto(1L, 0,
                "p_member");
        List<CreateProjectMemberDto> memberDtoList = new ArrayList<>();
        memberDtoList.add(memberDto);
        CreateProjectDto projectDto = new CreateProjectDto("테스트 타이틀", memberDtoList);
        List<ProjectInUser> dto = homeService.projectCreate(projectDto);
        log.info(dto.toString());
    }
}
