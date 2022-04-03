package kr.mmgg.scp.service;

import java.util.List;

import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.response.HomeViewDto;
import kr.mmgg.scp.entity.ProjectInUser;

public interface HomeService {
    public List<HomeViewDto> homeView(Long userId);

    public List<ProjectInUser> projectCreate(CreateProjectDto dto);
}
