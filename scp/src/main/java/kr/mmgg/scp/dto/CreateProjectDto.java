package kr.mmgg.scp.dto;

import java.util.List;

import lombok.Data;

@Data
public class CreateProjectDto {
    private String title;
    private List<CreateProjectMemberDto> member;
}
