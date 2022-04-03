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

    // /**
    // * 내가 만든 팀 ID
    // */
    // @Transactional
    // public List<Team> getMyTeams(Long userId) {
    // if(userId != null) { //존재하는 아이디 체크로 바꾸기
    // List<Team> myTeams = teamRepository.getMyTeams(userId);
    // return myTeams;
    // } else {
    // throw new IllegalStateException("유저 아이디가 없습니다.");
    // }
    // }

    // /**
    // * 다른 사람이 만든 팀 ID
    // */
    // @Transactional
    // public List<Team> getSharedTeams(Long userId) {
    // if(userId != null) {
    // List<Team> sharedTeams = teamRepository.getSharedTeams(userId);
    // return sharedTeams;
    // } else {
    // throw new IllegalStateException("유저 아이디가 없습니다.");
    // }
    // }

    /**
     * Teaminuser 리스트를 TeamMembersDto 리스트로 변환
     */
    default List<TeamMembersDto> toTeamMembersDtoList(List<Teaminuser> teaminusers) {

        List<TeamMembersDto> teamMembersDtoList = new ArrayList<>();
        if (teaminusers.size() > 0) {
            for (Teaminuser i : teaminusers) {
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
