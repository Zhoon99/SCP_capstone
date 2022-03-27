package kr.mmgg.scp.service;

import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;
import kr.mmgg.scp.repository.TeamRepository;
import kr.mmgg.scp.repository.TeaminuserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
public class TeamServiceTest {

    @Autowired
    TeamService teamService;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserService userService;
    @Autowired
    TeaminuserRepository teaminuserRepository;

    @Test
    public void 테스트() throws  Exception {

    }

    @Test
    public void 팀_생성() throws  Exception {
        ArrayList<Long> userIds = new ArrayList<>();

        userIds.add(userService.getUserIdByEmail("aaa@aaa"));
        userIds.add(userService.getUserIdByEmail("ccc@ccc"));
        userIds.add(userService.getUserIdByEmail("ddd@ddd"));

        teamService.insertTeam("e팀", 3L, userIds);

        List<Teaminuser> teaminusers = teaminuserRepository.findAll();
        if(teaminusers.size() > 0) {
            for(Teaminuser i : teaminusers) {
                log.info(i.getTeamId() +", "+ i.getUserId() +", "+ i.getTeaminuserCommoncode() +", "+ i.getTeaminuserMaker());
            }
        }
    }

    @Test
    public void 팀_목록() throws Exception {
        List<Team> teamIds = teamService.getMyTeams(3L);
        //List<Team> teamIds = teamService.getSharedTeams(3L);

        if(teamIds.size() > 0) {
            for(Team i : teamIds) {
                List<Teaminuser> teamMembers = teamService.getTeamMembers(i.getTeamId());
                log.info("----------------"+ i.getTeamName() +"----------------");
                if(teamMembers.size() > 0) {
                    for(Teaminuser j : teamMembers) {
                        log.info(j.getUser().getUserNickname());
                        log.info(j.getTeaminuserCommoncode());
                        log.info("-------------------------------------");
                    }
                } else {
                    log.info("팀 맴버가 없습니다.");
                }
            }
        } else {
            log.info("내 팀 데이터가 없습니다.");
        }
    }
}
