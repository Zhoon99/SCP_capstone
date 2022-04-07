package kr.mmgg.scp.dto.response;

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
    private List<TeamDetailDto> myTeams;
    private List<TeamDetailDto> sharedTeams;
}
