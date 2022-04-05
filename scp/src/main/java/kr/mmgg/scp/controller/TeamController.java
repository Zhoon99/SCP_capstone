package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.UserDto;
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

    @Transactional
    @GetMapping(value = "/home/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> teamHome(@PathVariable Long userId) {
        return new ResponseEntity<>(teamService.TeamHome(userId), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/getUserByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserIdByEmail(email), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/getTeamModifyInfo/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamHomeDto> getTeamModifyInfo(@PathVariable Long teamId) {
        return new ResponseEntity<>(teamService.getTeamInfo(teamId), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value ="/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> register(@RequestBody TeamHomeDto teamHomeDto){
        return new ResponseEntity<>(teamService.insertTeam(teamHomeDto), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/home/{teamId}")
    public ResponseEntity<String> remove(@PathVariable Long teamId) {
        teamService.remove(teamId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value ="/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modify(@RequestBody TeamHomeDto teamHomeDto){
        log.info(teamHomeDto.toString());
        teamService.modifyTeam(teamHomeDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 유저 정보 보내기 -> 나중에 세션으로 바꾸기
     */
    @Transactional
    @GetMapping(value = "/getUserInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserInfo(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }
}

