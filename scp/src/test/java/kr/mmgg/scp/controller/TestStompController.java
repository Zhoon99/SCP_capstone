package kr.mmgg.scp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mmgg.scp.dto.request.CreateChatRoomDto;
import kr.mmgg.scp.dto.request.CreateChatRoomMemberDto;
import kr.mmgg.scp.dto.request.ModifyChatRoomDto;
import kr.mmgg.scp.dto.request.ModifyChatRoomMemberDto;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStompController {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ObjectMapper objectMapper;
	
	//테스트 통과
	void testCreateRoom(CreateChatRoomDto dto) throws Exception {
		String param = objectMapper.writeValueAsString(dto);
		mock.perform(post("/createRoom").content(param).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	//테스트 통과
	void testModifyRoom(Long chatroomId,ModifyChatRoomDto dto) throws Exception {
		String param = objectMapper.writeValueAsString(dto);
		mock.perform(post("/modifyRoom/"+chatroomId).content(param).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	//테스트 통과
	void testDeleteRoom(Long chatroomId) throws Exception {
		mock.perform(get("/deleteRoom/"+chatroomId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	//테스트 통과
	void testLookupRoom(Long userId) throws Exception {
		mock.perform(get("/lookupRoom/"+userId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	//테스트 통과
	void testExitchatRoom(Long chatroomId, Long userId) throws Exception {
		mock.perform(get("/exitChatroom/"+chatroomId+"/"+userId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	//테스트 통과
	void testLookupMember(String email) throws Exception {
		mock.perform(get("/lookupMember/"+email).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	//테스트 통과
	void testLookupChatroomMessages(Long chatroomId) throws Exception {
		mock.perform(get("/chat/"+chatroomId).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	@Test
	void alltest() throws Exception {
		//GET
//		testLookupChatroomMessages(1L);
//		testLookupRoom(1L);
//		testLookupMember("test");
//		testExitchatRoom(7L, 3L);
//		testDeleteRoom(7L);
		//POST
		//createRoom
//		CreateChatRoomDto ccrDto = new CreateChatRoomDto();
//		ccrDto.setUserId(1L);
//		ccrDto.setChatroomName("4번째");
//		List<CreateChatRoomMemberDto> ccrmDtoList = new ArrayList<CreateChatRoomMemberDto>();
//		CreateChatRoomMemberDto ccrmDto = new CreateChatRoomMemberDto();
//		ccrmDto.setUserId(2L);
//		ccrmDtoList.add(ccrmDto);
//		ccrmDto = new CreateChatRoomMemberDto();
//		ccrmDto.setUserId(3L);
//		ccrmDtoList.add(ccrmDto);
//		ccrDto.setChatroomMember(ccrmDtoList);
//		testCreateRoom(ccrDto);
		//modifyRoom
//		ModifyChatRoomDto mcrDto = new ModifyChatRoomDto();
//		mcrDto.setChatroomName("4번째인척하는 4번째였습니다.");
//		List<ModifyChatRoomMemberDto> mcrmDtoList = new ArrayList<ModifyChatRoomMemberDto>();
//		ModifyChatRoomMemberDto mcrmDto = new ModifyChatRoomMemberDto();
//		mcrmDto.setUserId(4L);
//		mcrmDtoList.add(mcrmDto);
//		mcrmDto = new ModifyChatRoomMemberDto();
//		mcrmDto.setUserId(5L);
//		mcrmDtoList.add(mcrmDto);
//		mcrDto.setChatroomMember(mcrmDtoList);
//		testModifyRoom(4L, mcrDto);
	}
}
