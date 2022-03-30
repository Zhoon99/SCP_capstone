package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.TeamDto;
import kr.mmgg.scp.dto.TeamHomeDto;
import kr.mmgg.scp.dto.TeaminuserDto;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;
import kr.mmgg.scp.repository.TeamRepository;
import kr.mmgg.scp.repository.TeaminuserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeaminuserRepository teaminuserRepository;

    // /**
    //  * 내가 만든 팀 ID
    //  */
    // @Transactional
    // public List<Team> getMyTeams(Long userId) {
    //     if(userId != null) { //존재하는 아이디 체크로 바꾸기
    //         List<Team> myTeams = teamRepository.getMyTeams(userId);
    //         return myTeams;
    //     } else {
    //         throw new IllegalStateException("유저 아이디가 없습니다.");
    //     }
    // }

    // /**
    //  * 다른 사람이 만든 팀 ID
    //  */
    // @Transactional
    // public List<Team> getSharedTeams(Long userId) {
    //     if(userId != null) {
    //         List<Team> sharedTeams = teamRepository.getSharedTeams(userId);
    //         return sharedTeams;
    //     } else {
    //         throw new IllegalStateException("유저 아이디가 없습니다.");
    //     }
    // }

    /**
     * 내가 만든 팀 맴버
     */
    @Transactional
    public TeamHomeDto getMyTeamMembers(Long userId) {
        List<Team> myTeams = teamRepository.getMyTeams(userId);
        TeamHomeDto teamHomeDto = new TeamHomeDto();
        for(Team i : myTeams) {
            List<Teaminuser> teamMembers = teaminuserRepository.findTop3ByTeamId(i.getTeamId());
            teamHomeDto = new TeamHomeDto(i.getTeamName(), teamMembers);
        }
        return teamHomeDto;
    }

    /**
     * 다른 사람이 만든 팀 맴버
     */
    @Transactional
    public TeamHomeDto getSharedTeamMembers(Long userId) {
        List<Team> myTeams = teamRepository.getSharedTeams(userId);
        TeamHomeDto teamHomeDto = new TeamHomeDto();
        for(Team i : myTeams) {
            List<Teaminuser> teamMembers = teaminuserRepository.findTop3ByTeamId(i.getTeamId());
            teamHomeDto = new TeamHomeDto(i.getTeamName(), teamMembers);
        }
        return teamHomeDto;
    }

    /**
     * 팀 생성
     */
    @Transactional
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
        teaminuserRepository.saveAll(teaminuserList);
    }
}

