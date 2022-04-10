package kr.mmgg.scp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class UpdateProjectModify {
    private String projectName;
    private Long projectId;
    private List<UpdateProjectModifyMember> users;
}
