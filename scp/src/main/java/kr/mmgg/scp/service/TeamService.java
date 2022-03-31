package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.TeamHomeDto;
import kr.mmgg.scp.dto.TeamMembersDto;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TeamService {

    public Map<String, Object> TeamHome(Long userId);

    public List<TeamHomeDto> getTeamMembers(Long userId, List<Team> teams);

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
