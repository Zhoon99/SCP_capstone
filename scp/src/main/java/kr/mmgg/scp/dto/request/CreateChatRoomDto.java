package kr.mmgg.scp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class CreateChatRoomDto {
	private String chatroomName;
	private Long userId;
	private List<CreateChatRoomMemberDto> chatroomMember;
}
