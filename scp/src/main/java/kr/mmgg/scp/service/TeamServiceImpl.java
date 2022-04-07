package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.TeamDto;
import kr.mmgg.scp.dto.response.TeamDetailDto;
import kr.mmgg.scp.dto.response.TeamHomeDto;
import kr.mmgg.scp.dto.response.TeamMembersDto;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;
import kr.mmgg.scp.repository.TeamRepository;
import kr.mmgg.scp.repository.TeaminuserRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeaminuserRepository teaminuserRepository;

    /**
     * 팀 홈 정보 정리해서 반환
     */
    @Override
    @Transactional
    public ResultDto<TeamHomeDto> TeamHome(Long userId) {
        List<TeamDetailDto> myTeams = getTeamMembers(userId, toTeamList(teamRepository.getMyTeams(userId)));
        List<TeamDetailDto> sharedTeams = getTeamMembers(userId, toTeamList(teamRepository.getSharedTeams(userId)));

        TeamHomeDto teamHomeDto = TeamHomeDto.builder().myTeams(myTeams).sharedTeams(sharedTeams).build();

        ResultDto<TeamHomeDto> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, teamHomeDto, "teamHome");
        return rDto;
    }

    /**
     * 팀 맴버목록 정리해서 "TeamHome" 으로 반환
     */
    @Override
    @Transactional
    public List<TeamDetailDto> getTeamMembers(Long userId, List<TeamDto> teams) {
        List<TeamDetailDto> teamAndMembers = new ArrayList<>();

        if (teams.size() > 0) {
            for (TeamDto i : teams) {
                List<TeamMembersDto> teamMembers = toTeamMembersDtoList(
                        teaminuserRepository.findTop3ByTeamIdOrderByTeaminuserCommoncodeAsc(i.getTeamId()));
                if (teamMembers.size() > 0) {
                    TeamDetailDto responseTeamDetailDto = TeamDetailDto.builder()
                            .teamId(i.getTeamId())
                            .teamName(i.getTeamName())
                            .teamMembers(teamMembers)
                            .build();
                    teamAndMembers.add(responseTeamDetailDto);
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
    public ResultDto<Long> insertTeam(TeamDetailDto teamDetailDto) {
        if (StringUtils.isEmpty(teamDetailDto.getTeamName()) || StringUtils.isEmpty(teamDetailDto.getTeamMembers())) {
            throw new IllegalStateException("생성할 팀 정보를 가져오지 못했습니다.");
        }

        Team team = Team.builder()
                .teamName(teamDetailDto.getTeamName())
                .build();
        Team newTeam = teamRepository.save(team);

        List<Teaminuser> teaminuserList = new ArrayList<>();
        List<TeamMembersDto> teamMembersDtoList = teamDetailDto.getTeamMembers();
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

        ResultDto<Long> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.CREATE_SUCCESS, null, null);
        return rDto;
    }

    @Override
    @Transactional
    public void modifyTeam(TeamDetailDto teamDetailDto) {
        if (StringUtils.isEmpty(teamDetailDto.getTeamId()) || StringUtils.isEmpty(teamDetailDto.getTeamName())
                || StringUtils.isEmpty(teamDetailDto.getTeamMembers())) {
            throw new IllegalStateException("수정할 팀 정보를 가져오지 못했습니다.");
        }

        Team team = Team.builder()
                .teamId(teamDetailDto.getTeamId())
                .teamName(teamDetailDto.getTeamName())
                .build();
        teamRepository.save(team);

        teaminuserRepository.deleteByTeamId(teamDetailDto.getTeamId());

        List<Teaminuser> teaminuserList = new ArrayList<>();
        List<TeamMembersDto> teamMembersDtoList = teamDetailDto.getTeamMembers();
        for (TeamMembersDto i : teamMembersDtoList) {
            Teaminuser teaminuser = Teaminuser.builder()
                    .teamId(teamDetailDto.getTeamId())
                    .userId(i.getUserId())
                    .teaminuserCommoncode(i.getTeaminuserCommoncode())
                    .teaminuserMaker(i.getTeaminuserMaker())
                    .build();

            teaminuserList.add(teaminuser);
        }
        teaminuserRepository.saveAll(teaminuserList);
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
    public ResultDto<TeamDetailDto> getTeamInfo(Long teamId) {
        Team team = teamRepository.findByTeamId(teamId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        List<TeamMembersDto> teamMembers = toTeamMembersDtoList(
                teaminuserRepository.findByTeamIdOrderByTeaminuserCommoncodeAsc(teamId));
        if (teamMembers.isEmpty()) {
            throw new IllegalStateException("해당 팀의 맴버 정보가 없습니다.");
        }
        TeamDetailDto teamDetailDto = TeamDetailDto.builder()
                .teamId(teamId)
                .teamName(team.getTeamName())
                .teamMembers(teamMembers)
                .build();

        ResultDto<TeamDetailDto> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, teamDetailDto, "TeamDetail");
        return rDto;
    }
}
