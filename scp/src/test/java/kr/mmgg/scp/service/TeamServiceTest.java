
package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.TeamDetailDto;
import kr.mmgg.scp.dto.response.TeamMembersDto;
import kr.mmgg.scp.dto.response.TeamToAddDto;
import kr.mmgg.scp.entity.Teaminuser;
import kr.mmgg.scp.repository.*;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.MessageComparator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
public class TeamServiceTest {

    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserService userService;
    @Autowired
    TeaminuserRepository teaminuserRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired MessageRepository messageRepository;
    @Autowired ChatinuserRepository chatinuserRepository;
    @Autowired StompService stompService;


    @Test
    public void 테스트3() throws Exception {
        log.info(stompService.lookupChatroomMessages(1L).toString());
    }

    @Test
    @Commit
    public void 테스트() throws Exception {
        List<TeamMembersDto> teamMembersDtoList = new ArrayList<>();

        TeamMembersDto teamMembersDto1 = TeamMembersDto.builder()
                .userId(1L)
                .userNickname("공연성(김기태교수님소속)")
                .teaminuserCommoncode("s_leader")
                .teaminuserMaker(1)
                .build();
        teamMembersDtoList.add(teamMembersDto1);

        TeamMembersDto teamMembersDto2 = TeamMembersDto.builder()
                .userId(3L)
                .userNickname("권태웅(김기태교수님소속)")
                .teaminuserCommoncode("s_member")
                .teaminuserMaker(0)
                .build();
        teamMembersDtoList.add(teamMembersDto2);

        TeamMembersDto teamMembersDto3 = TeamMembersDto.builder()
                .userId(4L)
                .userNickname("최지훈(김기태교수님소속)")
                .teaminuserCommoncode("s_member")
                .teaminuserMaker(0)
                .build();
        teamMembersDtoList.add(teamMembersDto3);

        TeamMembersDto teamMembersDto4 = TeamMembersDto.builder()
                .userId(5L)
                .userNickname("김기태교수님")
                .teaminuserCommoncode("s_member")
                .teaminuserMaker(0)
                .build();
        teamMembersDtoList.add(teamMembersDto4);

        TeamDetailDto teamDetailDto = TeamDetailDto.builder()
                .teamId(3L)
                .teamName("cc팀")
                .teamMembers(teamMembersDtoList)
                .build();

        teamService.modifyTeam(teamDetailDto);
    }

    @Test
    public void 테스트1() throws Exception {
        /*
         * List<Teaminuser> userTeams = teaminuserRepository.findByUserId(3L);
         * 
         * if (userTeams.isEmpty()) {
         * throw new IllegalStateException("해당 유저의 팀 정보가 없습니다.");
         * }
         * 
         * List<TeamToAddDto> teamToAddDtoList = new ArrayList<>();
         * for(Teaminuser i : userTeams) {
         * log.info(i.toString());
         * TeamToAddDto teamToAddDto = TeamToAddDto.builder()
         * .teamId(i.getTeamId())
         * .teamName(i.getTeam().getTeamName())
         * .build();
         * teamToAddDtoList.add(teamToAddDto);
         * }
         * log.info(teamToAddDtoList.toString());
         */

        List<Teaminuser> teaminusers = teaminuserRepository.findByTeamId(1L);

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
        log.info(teamMembersDtos.toString());
    }
}
