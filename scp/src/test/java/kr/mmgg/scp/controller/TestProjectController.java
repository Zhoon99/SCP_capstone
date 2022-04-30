package kr.mmgg.scp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import kr.mmgg.scp.dto.request.CreateProjectMemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mmgg.scp.dto.request.CommentModifyDto;
import kr.mmgg.scp.dto.request.CommentWriteDto;
import kr.mmgg.scp.dto.request.CreateProjectDto;
import kr.mmgg.scp.dto.response.ProjectDetailSendTaskDto;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.util.dateTime;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProjectController {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProjectinUserRepository projectinUserRepository;
	void testCreateProject(CreateProjectDto dto) throws Exception{
		
		String param = objectMapper.writeValueAsString(dto);
		mock.perform(post("/createproject").content(param).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	void testUpdateProjectGetInfo(Long projectId) throws Exception {
		mock.perform(get("/updateproject/"+projectId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	void testUpdateProjectDeletemember(Long projectinuserId) throws Exception {
		mock.perform(get("/updateproject/deletemember"+projectinuserId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	
	// SCP-301 프로젝트 모든 할일
	void testAllTask(Long projectId) throws Exception{
		mock.perform(get("/alltask/"+projectId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	// SCP-302 프로젝트 자신 할일
	void testMyTask(Long userId, Long projectId) throws Exception{
		mock.perform(get("/mytask/"+userId+"/"+projectId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	// SCP-302 할일 수락 / 거절
	void testWhetherTask(Long userId, Long taskId) throws Exception{
		mock.perform(patch("/whethertask/"+userId+"/"+taskId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	//SCP-303 받은 요청 확인
	void testReceivetask(Long projectId, Long projectuserId) throws Exception{
		mock.perform(get("/receivetask/"+projectId+"/"+projectuserId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	// SCP-303 받은요청 수락 / 거절
	void testReceivetask(Long taskId, Integer selected) throws Exception{
		mock.perform(patch("/receivetask/"+taskId+"/"+selected).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	// SCP-304 보낸 요청 확인
	void testRequestask(Long projectId, Long userId) throws Exception{
		mock.perform(get("/requestask/"+projectId+"/"+userId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	// SCP-305 프로젝트 할일 요청시 프로젝트 안 사람들 불러오기
	void testSendTask(Long projectId) throws Exception{
		mock.perform(get("/sendtask/"+projectId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	// SCP-305 프로젝트 할일 보내는 작업
	void testSendTask(ProjectDetailSendTaskDto dto) throws Exception {
		String param = objectMapper.writeValueAsString(dto);
		mock.perform(post("/sendtask").content(param).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	// 댓글 작성
	void testCommentWrite(CommentWriteDto dto) throws Exception {
		String param = objectMapper.writeValueAsString(dto);
		mock.perform(post("/commentwrite").content(param).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	// 댓글 수정
	void testCommentModify(Long commentId ,CommentModifyDto dto) throws Exception {
		String param = objectMapper.writeValueAsString(dto);
		mock.perform(patch("/commentmodify/"+commentId).content(param).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	// 댓글 삭제
	void testDeleteComment(Long commentId) throws Exception {
		mock.perform(delete("/commentdelete/"+commentId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	// HomeView -> Detail
	void testTaskDetail(Long taskId) throws Exception {
		mock.perform(get("/taskDetail/"+taskId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	void alltest() throws Exception{
		/*dateTime datetime = new dateTime();
		testAllTask(1L); // 통과
		testMyTask(2L, 1L); // 통과
		testWhetherTask(2L, 1L); // 통과
		testReceivetask(1L, -1); // 통과
		testReceivetask(1L, 2L); // 통과
		testRequestask(1L, 2L); // 통과
		testSendTask(1L);

		ProjectDetailSendTaskDto pdstdto = new ProjectDetailSendTaskDto();
		pdstdto.setProjectId(1L);
		pdstdto.setProjectinuserId(2L);
		pdstdto.setTaskContent("testmockmvc");
		pdstdto.setTaskDeadline(datetime.dateTime());
		pdstdto.setUserId(2L);
		testSendTask(pdstdto); // 통과

	CommentWriteDto cwdto = new CommentWriteDto();
		cwdto.setTaskId(1L);
		cwdto.setUserId(2L);
		cwdto.setCommentContent("HI");
		testCommentWrite(cwdto); // 통과

		CommentModifyDto cmdto = new CommentModifyDto();
		cmdto.setCommentId(1L);
		cmdto.setCommentContent("HI MOCK수정");
		testCommentModify(1L, cmdto); // 통과

		testDeleteComment(1L); // 통과

		List<CreateProjectMemberDto> mlist = new ArrayList<CreateProjectMemberDto>();
		CreateProjectMemberDto mdto = new CreateProjectMemberDto(1L, 1, "p-leader");
		mlist.add(mdto);
		CreateProjectDto dto = new CreateProjectDto("MockMVC 테스트", mlist);
		testCreateProject(dto);*/
	}
}
