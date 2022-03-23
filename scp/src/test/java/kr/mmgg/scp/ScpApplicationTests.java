package kr.mmgg.scp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.mmgg.scp.entity.ProjectInUser;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.service.ProjectinUserService;
import lombok.AllArgsConstructor;

@SpringBootTest
class ScpApplicationTests {
	@Autowired
	private ProjectinUserRepository projectinUserRepository;
	@Autowired
    private ProjectinUserService projectinUserService;
    
	@Test
	void test() {
//		projectinUserService.test1(1L);
		projectinUserService.test2(4L);
	}
}
