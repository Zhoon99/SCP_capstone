package kr.mmgg.scp.dto.request;

import lombok.Data;

@Data
public class UpdateProjectModify {
    private String projectName;
    private Long userId;
    private Long projectId;
    private String CommonCode;
}
