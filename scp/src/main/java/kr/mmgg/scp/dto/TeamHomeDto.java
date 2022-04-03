package kr.mmgg.scp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamHomeDto {

    private Long teamId;
    private String teamName;
    private List<TeamMembersDto> teamMembers;
}
