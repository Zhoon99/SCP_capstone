package kr.mmgg.scp.service;

import java.util.List;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.request.UpdateProjectModify;
import kr.mmgg.scp.dto.response.HomeViewDto;
import kr.mmgg.scp.dto.response.HomeViewRealDto;
import kr.mmgg.scp.entity.ProjectInUser;

public interface HomeService {
    public ResultDto<HomeViewRealDto> homeView(Long userId);

    public ResultDto<List<ProjectInUser>> projectCreate(CreateProjectDto dto);

    public ResultDto<?> modifyProject(UpdateProjectModify updateProjectModify);

    public ResultDto<?> removeProject(Long projectId);
}
