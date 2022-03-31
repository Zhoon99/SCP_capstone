package kr.mmgg.scp.service;

import kr.mmgg.scp.repository.TeamRepository;
import kr.mmgg.scp.repository.TeaminuserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void 테스트() throws  Exception {

    }
}
