package kr.mmgg.scp.dto;

import lombok.Data;

@Data
public class CreateProjectMemberDto {
    private Long userId;
    private Integer projectinuserMaker;
    private String projectinuserCommoncode;
}
