package kr.mmgg.scp.dto;

import kr.mmgg.scp.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDto {

    private String teamName;

    public Team teamDtoToEntity(TeamDto teamDTO) {
        return Team.builder()
                .teamName(teamDTO.getTeamName())
                .build();
    }
}