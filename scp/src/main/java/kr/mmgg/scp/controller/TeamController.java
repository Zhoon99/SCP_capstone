package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.TeaminuserDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.response.*;
import kr.mmgg.scp.service.TeamServiceImpl;
import kr.mmgg.scp.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamServiceImpl teamService;
    private final UserServiceImpl userService;

    //SCP-200 팀 홈
    @Transactional
    @GetMapping(value = "/home/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<TeamHomeDto> teamHome(@PathVariable Long userId) {
        return teamService.TeamHome(userId);
    }

    //SCP-201 AddTeam 팀 목록 가져오기
    @Transactional
    @GetMapping(value = "/getUserTeamList/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<TeamToAddDto>> getUserTeamList(@PathVariable Long userId) {
        return teamService.getUserTeamList(userId);
    }

    //SCP-201 AddTeam 팀 맴버 목록 가져오기
    @Transactional
    @GetMapping(value = "/getTeamMembers/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<TeamMembersDto>> getTeamMembers(@PathVariable Long teamId) {
        return teamService.teamToAddMembers(teamId);
    }

    //SCP-201 AddTeamMember 이메일 검색 기능
    @Transactional
    @GetMapping(value = "/getUsersByEmail/{search}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<UserToAddDto>> getUsersByEmail(@PathVariable String search) {
        return teamService.getUsersByEmail(search);
    }

    //SCP-201 팀 맴버 삭제
    @Transactional
    @PostMapping(value = "/deleteTeamMember", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<?> deleteTeamMember(@RequestBody TeaminuserDto teaminuserDto) {
        return teamService.deleteTeamMember(teaminuserDto.getTeamId(), teaminuserDto.getUserId());
    }

    //SCP-201 팀 등록
    @Transactional
    @PostMapping(value ="/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<?> register(@RequestBody TeamDetailDto teamDetailDto){
        return teamService.insertTeam(teamDetailDto);
    }

    //SCP-201 팀 수정(팀 정보 불러오기)
    @Transactional
    @GetMapping(value = "/getTeamModifyInfo/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<TeamDetailDto> getTeamModifyInfo(@PathVariable Long teamId) {
        return teamService.getTeamInfo(teamId);
    }

    //SCP-201 팀 수정
    @Transactional
    @PutMapping(value ="/modify", produces = MediaType.APPLICATION_JSON_VALUE) //구분을 위해 Post 대신 Put 사용 (Response json 에 teamId 존재)
    public ResultDto<?> modify(@RequestBody TeamDetailDto teamDetailDto){
        return teamService.modifyTeam(teamDetailDto);
    }

    //SCP-201 팀 삭제
    @Transactional
    @DeleteMapping("/delete/{teamId}")
    public ResultDto<?> remove(@PathVariable Long teamId) {
        return teamService.remove(teamId);
    }

    //유저 정보 보내기 -> 나중에 세션으로 바꾸기?
    @Transactional
    @GetMapping(value = "/getUserInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<UserDto> getUserInfo(@PathVariable Long userId) {
        return userService.getUserByUserId(userId);
    }
}

