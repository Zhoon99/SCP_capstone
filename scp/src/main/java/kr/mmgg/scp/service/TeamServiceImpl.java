package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.TeamDto;
import kr.mmgg.scp.dto.response.TeamHomeDto;
import kr.mmgg.scp.dto.response.TeamMembersDto;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;
import kr.mmgg.scp.repository.TeamRepository;
import kr.mmgg.scp.repository.TeaminuserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeaminuserRepository teaminuserRepository;
    private final UserServiceImpl userService;

    /**
     * 팀 홈 정보 정리해서 반환
     */
    @Override
    @Transactional
    public Map<String, Object> TeamHome(Long userId) {
        Map<String, Object> teamHome = new HashMap<>();
        List<TeamHomeDto> myTeams = getTeamMembers(userId, toTeamList(teamRepository.getMyTeams(userId)));
        List<TeamHomeDto> sharedTeams = getTeamMembers(userId, toTeamList(teamRepository.getSharedTeams(userId)));
        teamHome.put("myTeams", myTeams);
        teamHome.put("sharedTeams", sharedTeams);
        return teamHome;
    }

    /**
     * 팀 맴버목록 정리해서 반환
     */
    @Override
    @Transactional
    public List<TeamHomeDto> getTeamMembers(Long userId, List<TeamDto> teams) {
        List<TeamHomeDto> teamAndMembers = new ArrayList<>();

        if (teams.size() > 0) {
            for (TeamDto i : teams) {
                List<TeamMembersDto> teamMembers = toTeamMembersDtoList(teaminuserRepository.findTop3ByTeamIdOrderByTeaminuserCommoncodeAsc(i.getTeamId()));
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


    /**
     * 팀 생성
     */
    @Override
    @Transactional
    public Long insertTeam(TeamHomeDto teamHomeDto) {
        Team team = Team.builder()
                .teamName(teamHomeDto.getTeamName())
                .build();
        Team newTeam = teamRepository.save(team);

        List<Teaminuser> teaminuserList = new ArrayList<>();
        List<TeamMembersDto> teamMembersDtoList = teamHomeDto.getTeamMembers();
        if (teamMembersDtoList.size() > 0) {
            for (TeamMembersDto i : teamMembersDtoList) {
                Teaminuser teaminuser = Teaminuser.builder()
                        .teamId(newTeam.getTeamId())
                        .userId(i.getUserId())
                        .teaminuserCommoncode(i.getTeaminuserCommoncode())
                        .teaminuserMaker(i.getTeaminuserMaker())
                        .build();

                teaminuserList.add(teaminuser);
            }
            teaminuserRepository.saveAll(teaminuserList);
        } else throw new IllegalArgumentException("팀원 목록 불러오기 오류");
        return newTeam.getTeamId();
    }

    @Override
    @Transactional
    public void modifyTeam(TeamHomeDto teamHomeDto) {
        Team team = Team.builder()
                .teamId(teamHomeDto.getTeamId())
                .teamName(teamHomeDto.getTeamName())
                .build();
        teamRepository.save(team);

        teaminuserRepository.deleteByTeamId(teamHomeDto.getTeamId());
        List<Teaminuser> teaminuserList = new ArrayList<>();
        List<TeamMembersDto> teamMembersDtoList = teamHomeDto.getTeamMembers();
        if (teamMembersDtoList.size() > 0) {
            for (TeamMembersDto i : teamMembersDtoList) {
                Teaminuser teaminuser = Teaminuser.builder()
                        .teamId(teamHomeDto.getTeamId())
                        .userId(i.getUserId())
                        .teaminuserCommoncode(i.getTeaminuserCommoncode())
                        .teaminuserMaker(i.getTeaminuserMaker())
                        .build();

                teaminuserList.add(teaminuser);
            }
            teaminuserRepository.saveAll(teaminuserList);
        } else throw new IllegalArgumentException("팀원 목록 불러오기 오류");
    }

    @Override
    public void remove(Long teamId) {
        teaminuserRepository.deleteByTeamId(teamId);
        teamRepository.deleteById(teamId);
    }

    /**
     * 해당 팀 정보 정리해서 반환
     */
    @Override
    @Transactional
    public TeamHomeDto getTeamInfo(Long teamId) {
        Optional<Team> team = teamRepository.findByTeamId(teamId);
        if (team.isPresent()) {
            List<TeamMembersDto> teamMembers = toTeamMembersDtoList(teaminuserRepository.findByTeamIdOrderByTeaminuserCommoncodeAsc(teamId));
            TeamHomeDto teamHomeDto = TeamHomeDto.builder()
                    .teamId(teamId)
                    .teamName(team.get().getTeamName())
                    .teamMembers(teamMembers)
                    .build();
            return teamHomeDto;
        }
        return new TeamHomeDto(); //뭘 보낼까?
    }
}
