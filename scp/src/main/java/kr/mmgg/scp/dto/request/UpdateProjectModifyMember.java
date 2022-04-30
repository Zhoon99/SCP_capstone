package kr.mmgg.scp.dto.request;

import lombok.Data;

@Data
public class UpdateProjectModifyMember {
    private Long userId;
    private String userName;
    private String commonCode;
    private Integer maker;
}
