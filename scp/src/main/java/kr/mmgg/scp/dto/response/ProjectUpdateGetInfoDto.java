package kr.mmgg.scp.dto.response;

import java.util.List;

import kr.mmgg.scp.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProjectUpdateGetInfoDto {
    private List<ProjectUpdateGetInfoMemberDto> users;
    private String ProjectName;
}
