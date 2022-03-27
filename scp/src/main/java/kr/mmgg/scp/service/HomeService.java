package kr.mmgg.scp.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import kr.mmgg.scp.dto.CreateProjectDto;
import kr.mmgg.scp.dto.HomeViewDto;
import kr.mmgg.scp.entity.ProjectInUser;

public interface HomeService {
    public List<HomeViewDto> homeView(Long userId);

    public List<ProjectInUser> projectCreate(CreateProjectDto dto);
}
