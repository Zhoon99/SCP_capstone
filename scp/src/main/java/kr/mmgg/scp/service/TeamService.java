package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.TeamDto;
import kr.mmgg.scp.dto.response.TeamHomeDto;
import kr.mmgg.scp.dto.response.TeamMembersDto;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TeamService {

    public Map<String, Object> TeamHome(Long userId);

    public List<TeamHomeDto> getTeamMembers(Long userId, List<TeamDto> teams);

    public Long insertTeam(TeamHomeDto teamHomeDto);

    public void remove(Long teamId);

    public TeamHomeDto getTeamInfo(Long teamId);

    public void modifyTeam(TeamHomeDto teamHomeDto);

    default List<TeamDto> toTeamList(List<Team> team) {
        List<TeamDto> teamDtoList = new ArrayList<>();
        if(team.size() > 0) {
            for(Team i : team) {
                TeamDto teamDto = TeamDto.builder()
                        .teamId(i.getTeamId())
                        .teamName(i.getTeamName())
                        .build();
                teamDtoList.add(teamDto);
            }
        }
        return teamDtoList;
    }

    /**
     * Teaminuser 리스트를 TeamMembersDto 리스트로 변환
     */
    default List<TeamMembersDto> toTeamMembersDtoList(List<Teaminuser> teaminusers) {

        List<TeamMembersDto> teamMembersDtoList = new ArrayList<>();
        if(teaminusers.size() > 0) {
            for(Teaminuser i : teaminusers) {
                TeamMembersDto teamMembersDto = TeamMembersDto.builder()
                        .userId(i.getUserId())
                        .userNickname(i.getUser().getUserNickname())
                        .teaminuserCommoncode(i.getTeaminuserCommoncode())
                        .teaminuserMaker(i.getTeaminuserMaker())
                        .build();
                teamMembersDtoList.add(teamMembersDto);
            }
        }
        return teamMembersDtoList;
    }
}
