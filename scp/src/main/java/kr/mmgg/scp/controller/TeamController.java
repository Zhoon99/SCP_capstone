package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.dto.response.TeamDetailDto;
import kr.mmgg.scp.dto.response.TeamHomeDto;
import kr.mmgg.scp.service.TeamServiceImpl;
import kr.mmgg.scp.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

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

    //SCP-201 팀 등록
    @Transactional
    @PostMapping(value ="/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<Long> register(@RequestBody TeamDetailDto teamDetailDto){
        return teamService.insertTeam(teamDetailDto);
    }

    //SCP-201 이메일로 팀원 추가
    @Transactional
    @GetMapping(value = "/getUserByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<UserDto> getUserByEmail(@PathVariable String email) {
        return userService.getUserIdByEmail(email);
    }

    //SCP-201 팀 수정
    @Transactional
    @PutMapping(value ="/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modify(@RequestBody TeamDetailDto teamDetailDto){
        log.info(teamDetailDto.toString());
        teamService.modifyTeam(teamDetailDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
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

