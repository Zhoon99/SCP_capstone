package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.TeamHomeDto;
import kr.mmgg.scp.dto.TeamMembersDto;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.repository.TeamRepository;
import kr.mmgg.scp.repository.TeaminuserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeaminuserRepository teaminuserRepository;

    /**
     * 팀 홈 정보 정리해서 가져오기
     */
    @Override
    @Transactional
    public Map<String, Object> TeamHome(Long userId) {
        Map<String, Object> teamHome = new HashMap<>();
        List<TeamHomeDto> myTeams = getTeamMembers(userId, teamRepository.getMyTeams(userId));
        List<TeamHomeDto> sharedTeams = getTeamMembers(userId, teamRepository.getSharedTeams(userId));
        teamHome.put("myTeams", myTeams);
        teamHome.put("sharedTeams", sharedTeams);

        return teamHome;
    }

    /**
     * 팀 맴버목록 정리해서 가져오기
     */
    @Override
    @Transactional
    public List<TeamHomeDto> getTeamMembers(Long userId, List<Team> teams) {
        List<TeamHomeDto> teamAndMembers = new ArrayList<>();

        if (teams.size() > 0) {
            for (Team i : teams) {
                List<TeamMembersDto> teamMembers = toTeamMembersDtoList(teaminuserRepository.findTop3ByTeamId(i.getTeamId()));
                if (teamMembers.size() > 0) {
                    TeamHomeDto responseTeamHomeDto = TeamHomeDto.builder()
                            .teamId(i.getTeamId())
                            .teamName(i.getTeamName())
                            .teamMembers(teamMembers)
                            .build();
                    teamAndMembers.add(responseTeamHomeDto);
                }
            }
        }
        return teamAndMembers;
    }


    /*
    @Transactional
    public List<ResponseTeamHomeDto> getMyTeamMembers(Long userId) {
        List<TeamUserDto> teamUserDtos = new ArrayList<>();
        List<List<TeamUserDto>> teamUserDtosList = new ArrayList<>();

        List<ResponseTeamHomeDto> responseTeamHomeDtos = new ArrayList<>();

        List<Team> myTeams = teamRepository.getMyTeams(userId);
        if (myTeams.size() > 0) {
            int index = 0;
            for (Team i : myTeams) {
                List<Teaminuser> teamMembers = teaminuserRepository.findTop3ByTeamId(i.getTeamId());
                if (teamMembers.size() > 0) {
                    for (Teaminuser j : teamMembers) {
                        TeamUserDto teamUserDto = TeamUserDto.builder()
                                .userId(j.getUserId())
                                .userNickname(j.getUser().getUserNickname())
                                .teaminuserCommoncode(j.getTeaminuserCommoncode())
                                .teaminuserMaker(j.getTeaminuserMaker())
                                .build();
                        teamUserDtos.add(teamUserDto); //내가 만든 팀의 회원목록 저장
                    }
                    teamUserDtosList.add(index, teamUserDtos); //팀별 회원목록 저장

                    ResponseTeamHomeDto responseTeamHomeDto = ResponseTeamHomeDto.builder()
                            .teamId(i.getTeamId())
                            .teamName(i.getTeamName())
                            .teamUserDtos(teamUserDtosList.get(index))
                            .build();
                    responseTeamHomeDtos.add(responseTeamHomeDto);

                }
                index ++;
            }
        }
        return responseTeamHomeDtos;
    }
    */

    /**
     * 팀 생성
     */
    /*@Transactional
    public void insertTeam(String teamName, Long userId, ArrayList<Long> userIds) {
        TeamDto teamDTO = new TeamDto(teamName);
        Team newTeam = teamRepository.save(teamDTO.teamDtoToEntity(teamDTO));

        List<Teaminuser> teaminuserList = new ArrayList<>();
        TeaminuserDto teaminuserDTO = new TeaminuserDto(userId ,newTeam.getTeamId(), "s_leader", 1);
        teaminuserList.add(teaminuserDTO.teamDtoToEntity(teaminuserDTO));

        if(userIds != null && userIds.size() > 0) {
            for(Long i : userIds) {
                teaminuserDTO = new TeaminuserDto(i, newTeam.getTeamId(), "s_member", 0);
                teaminuserList.add(teaminuserDTO.teamDtoToEntity(teaminuserDTO));
            }
        }
        List<Teaminuser> newTeaminusers = teaminuserRepository.saveAll(teaminuserList);
    }*/
}
