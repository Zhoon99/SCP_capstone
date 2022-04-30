package kr.mmgg.scp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class ModifyChatRoomDto {
	public String chatroomName;
	private List<ModifyChatRoomMemberDto> chatroomMember;
}
