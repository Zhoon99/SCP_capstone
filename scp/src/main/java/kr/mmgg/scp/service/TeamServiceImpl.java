package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.TeamDto;
import kr.mmgg.scp.dto.response.*;
import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.TeamRepository;
import kr.mmgg.scp.repository.TeaminuserRepository;
import kr.mmgg.scp.repository.UserRepository;
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
    private final UserRepository userRepository;

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
     * 맴버를 추가할 팀 목록 전달
     */
    @Override
    @Transactional
    public ResultDto<List<TeamToAddDto>> getUserTeamList(Long userId) {
        List<Teaminuser> teaminusers = teaminuserRepository.findByUserId(userId);

        if (teaminusers.isEmpty()) {
            throw new IllegalStateException(userId + "해당 유저의 팀 정보가 없습니다.");
        }

        List<TeamToAddDto> teamToAddDtoList = new ArrayList<>();
        for (Teaminuser i : teaminusers) {
            TeamToAddDto teamToAddDto = TeamToAddDto.builder()
                    .teamId(i.getTeamId())
                    .teamName(i.getTeam().getTeamName())
                    .build();
            teamToAddDtoList.add(teamToAddDto);
        }
        ResultDto<List<TeamToAddDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, teamToAddDtoList, "teams");
        return rDto;
    }

    /**
     * 팀의 맴버 정보 반환
     */
    @Override
    @Transactional
    public ResultDto<List<TeamMembersDto>> teamToAddMembers(Long teamId) {
        List<Teaminuser> teaminusers = teaminuserRepository.findByTeamId(teamId);

        if (teaminusers.isEmpty()) {
            throw new CustomException(ErrorCode.TEAM_NOT_FOUND);
        }

        List<TeamMembersDto> teamMembersDtos = new ArrayList<>();
        for (Teaminuser i : teaminusers) {
            TeamMembersDto teamMembersDto = TeamMembersDto.builder()
                    .userId(i.getUserId())
                    .userNickname(i.getUser().getUserNickname())
                    .teaminuserCommoncode("s_member")
                    .teaminuserMaker(0)
                    .build();
            teamMembersDtos.add(teamMembersDto);
        }
        ResultDto<List<TeamMembersDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, teamMembersDtos, "members");
        return rDto;
    }

    /**
     * 검색어로 시작하는 유저정보 반환
     */
    @Override
    @Transactional
    public ResultDto<List<UserToAddDto>> getUsersByEmail(String search) {
        List<User> usersIncludingSearch = userRepository.findByUserEmailStartingWith(search);

        if (usersIncludingSearch.isEmpty()) {
            throw new IllegalStateException(search + "검색어에 해당하는 유저가 없습니다.");
        }

        List<UserToAddDto> userToAddDtoList = new ArrayList<>();
        for(User i : usersIncludingSearch) {
            UserToAddDto userToAddDto = UserToAddDto.builder()
                    .userId(i.getUserId())
                    .userNickname(i.getUserNickname())
                    .userEmail(i.getUserEmail())
                    .build();
            userToAddDtoList.add(userToAddDto);
        }
        ResultDto<List<UserToAddDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, userToAddDtoList, "emailUser");
        return rDto;
    }

    @Override
    @Transactional
    public ResultDto<?> deleteTeamMember(Long teamId, Long userId) {
        Teaminuser teaminuser = teaminuserRepository.findByTeamIdAndAndUserId(teamId, userId)
                .orElseThrow(() -> new IllegalStateException(teamId + "팀이나 " + userId + "유저에 해당하는 정보가 없습니다."));
        teaminuserRepository.delete(teaminuser);

        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.DELETE_SUCCESS);
        return rDto;
    }

    /**
     * 팀 생성
     */
    @Override
    @Transactional
    public ResultDto<?> insertTeam(TeamDetailDto teamDetailDto) {
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

        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.CREATE_SUCCESS);
        return rDto;
    }

    /**
     * 팀 수정
     */
    @Override
    @Transactional
    public ResultDto<?> modifyTeam(TeamDetailDto teamDetailDto) {
        if (StringUtils.isEmpty(teamDetailDto.getTeamId()) || StringUtils.isEmpty(teamDetailDto.getTeamName())
                || StringUtils.isEmpty(teamDetailDto.getTeamMembers())) {
            throw new IllegalStateException("수정할 팀 정보를 가져오지 못했습니다.");
        }

        Team team = Team.builder()
                .teamId(teamDetailDto.getTeamId())
                .teamName(teamDetailDto.getTeamName())
                .build();
        teamRepository.save(team);

        List<TeamMembersDto> newTeams = teamDetailDto.getTeamMembers();
        List<Teaminuser> existTeams = teaminuserRepository.findByTeamId(teamDetailDto.getTeamId());

        if (existTeams.isEmpty()) {
            throw new CustomException(ErrorCode.TEAM_NOT_FOUND);
        }

        List<TeamMembersDto> newMembers = new ArrayList<>();

        for(TeamMembersDto i : newTeams) {
            for(Teaminuser j : existTeams) {
                if(i.getUserId() == j.getUserId()) { //업데이트
                    Teaminuser teaminuser = Teaminuser.builder()
                            .teaminuserId(j.getTeaminuserId())
                            .userId(i.getUserId())
                            .teamId(teamDetailDto.getTeamId())
                            .teaminuserCommoncode(i.getTeaminuserCommoncode())
                            .teaminuserMaker(i.getTeaminuserMaker())
                            .build();
                    teaminuserRepository.save(teaminuser);
                    newMembers.add(i);
                }
            }
            if(!newMembers.contains(i)) { //추가
                Teaminuser teaminuser = Teaminuser.builder()
                        .userId(i.getUserId())
                        .teamId(teamDetailDto.getTeamId())
                        .teaminuserCommoncode(i.getTeaminuserCommoncode())
                        .teaminuserMaker(i.getTeaminuserMaker())
                        .build();
                teaminuserRepository.save(teaminuser);
            }
        }
        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS);
        return rDto;
    }

    @Override
    public ResultDto<?> remove(Long teamId) {
        teaminuserRepository.deleteByTeamId(teamId);
        teamRepository.deleteById(teamId);

        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.DELETE_SUCCESS);
        return rDto;
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
            throw new IllegalStateException(teamId + "해당 팀의 맴버 정보가 없습니다.");
        }
        TeamDetailDto teamDetailDto = TeamDetailDto.builder()
                .teamId(teamId)
                .teamName(team.getTeamName())
                .teamMembers(teamMembers)
                .build();

        ResultDto<TeamDetailDto> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, teamDetailDto, "modifyTeamInfo");
        return rDto;
    }
}
