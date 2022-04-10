package kr.mmgg.scp.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProjectDto {
    private String title;
    private List<CreateProjectMemberDto> member;
}
