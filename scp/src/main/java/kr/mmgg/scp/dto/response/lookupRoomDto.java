package kr.mmgg.scp.dto.response;

import lombok.Data;

@Data
public class lookupRoomDto {
	private Long chatroomId;
	private String chatroomName;
	private String chatroomCommoncode;
	private Integer headCount;
}
