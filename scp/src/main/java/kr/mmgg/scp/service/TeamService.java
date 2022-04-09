package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.TeamDto;
import kr.mmgg.scp.dto.response.*;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;

import java.util.ArrayList;
import java.util.List;

public interface TeamService {

    public ResultDto<TeamHomeDto> TeamHome(Long userId);

    public List<TeamDetailDto> getTeamMembers(Long userId, List<TeamDto> teams);

    public ResultDto<?> insertTeam(TeamDetailDto teamDetailDto);

    public ResultDto<?> remove(Long teamId);

    public ResultDto<TeamDetailDto> getTeamInfo(Long teamId);

    public ResultDto<?> modifyTeam(TeamDetailDto teamDetailDto);

    public ResultDto<List<TeamToAddDto>> getUserTeamList(Long userId);

    public ResultDto<List<TeamMembersDto>> teamToAddMembers(Long teamId);

    public ResultDto<List<UserToAddDto>> getUsersByEmail(String search);

    public ResultDto<?> deleteTeamMember(Long teamId, Long userId);

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
