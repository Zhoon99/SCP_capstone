package kr.mmgg.scp.service;

import java.util.List;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.response.HomeViewDto;
import kr.mmgg.scp.entity.ProjectInUser;

public interface HomeService {
	public ResultDto<List<HomeViewDto>> homeView(Long userId);

    public ResultDto<List<ProjectInUser>> projectCreate(CreateProjectDto dto);
}
