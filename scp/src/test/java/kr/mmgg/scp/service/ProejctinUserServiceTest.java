package kr.mmgg.scp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProejctinUserServiceTest {
    @Autowired
    private ProejctinUserService proejctinUserService;

    @Test
    void testTest1() {
        proejctinUserService.test1(1L);
    }
}
