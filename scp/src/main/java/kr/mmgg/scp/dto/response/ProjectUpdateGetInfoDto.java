package kr.mmgg.scp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ProjectUpdateGetInfoDto {
    private List<ProjectUpdateGetInfoMemberDto> users;
    private String ProjectName;
}
