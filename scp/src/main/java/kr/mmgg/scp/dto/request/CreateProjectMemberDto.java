package kr.mmgg.scp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProjectMemberDto {
    private Long userId;
    private Integer projectinuserMaker;
    private String projectinuserCommoncode;
}
