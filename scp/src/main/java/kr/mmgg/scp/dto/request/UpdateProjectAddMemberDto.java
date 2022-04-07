package kr.mmgg.scp.dto.request;

import lombok.Data;

@Data
public class UpdateProjectAddMemberDto {
    private Long userId;
    private Long projectId;
    private String commonCode;
    private Integer projectinuserMaker;
}
