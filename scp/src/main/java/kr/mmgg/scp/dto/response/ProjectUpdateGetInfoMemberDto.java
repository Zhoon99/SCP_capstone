package kr.mmgg.scp.dto.response;

import lombok.Data;

@Data
public class ProjectUpdateGetInfoMemberDto {
    private Long userId;
    private Long projectinuserId;
    private String nickName;
    private String projectinuserCommoncode;
}
