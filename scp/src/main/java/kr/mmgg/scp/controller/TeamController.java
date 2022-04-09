package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.response.*;
import kr.mmgg.scp.service.TeamServiceImpl;
import kr.mmgg.scp.service.UserServiceImpl;
import kr.mmgg.scp.util.CustomStatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
@Slf4j
public class TeamController {

    private final TeamServiceImpl teamService;
    private final UserServiceImpl userService;

    //SCP-200 팀 홈
    @Transactional
    @GetMapping(value = "/home/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<TeamHomeDto> teamHome(@PathVariable Long userId) {
        return teamService.TeamHome(userId);
    }

    @Transactional
    @GetMapping(value = "/getUserTeamList/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<TeamToAddDto>> getUserTeamList(@PathVariable Long userId) {
        return teamService.getUserTeamList(userId);
    }

    @Transactional
    @GetMapping(value = "/getTeamMembers/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<TeamMembersDto>> getTeamMembers(@PathVariable Long teamId) {
        return teamService.teamToAddMembers(teamId);
    }

    @Transactional
    @PostMapping(value = "/getUsersByEmail/{search}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<UserToAddDto>> getUsersByEmail(@PathVariable String search) {
        return teamService.getUsersByEmail(search);
    }

    //SCP-201 팀 등록
    @Transactional
    @PostMapping(value ="/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<?> register(@RequestBody TeamDetailDto teamDetailDto){
        teamService.insertTeam(teamDetailDto);

        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.CREATE_SUCCESS, null, null);
        return rDto;
    }

    //SCP-201 팀 수정
    @Transactional
    @PutMapping(value ="/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<?> modify(@RequestBody TeamDetailDto teamDetailDto){
        teamService.modifyTeam(teamDetailDto);

        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.MODIFY_SUCCESS, null, null);
        return rDto;
    }

    //SCP-201 팀 수정(팀 정보 불러오기)
    @Transactional
    @GetMapping(value = "/getTeamModifyInfo/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<TeamDetailDto> getTeamModifyInfo(@PathVariable Long teamId) {
        return teamService.getTeamInfo(teamId);
    }

    //SCP-201 팀 삭제
    @Transactional
    @DeleteMapping("/home/{teamId}")
    public ResponseEntity<String> remove(@PathVariable Long teamId) {
        teamService.remove(teamId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    //유저 정보 보내기 -> 나중에 세션으로 바꾸기?
    @Transactional
    @GetMapping(value = "/getUserInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<UserDto> getUserInfo(@PathVariable Long userId) {
        return userService.getUserByUserId(userId);
    }
}

