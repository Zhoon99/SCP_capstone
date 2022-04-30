package kr.mmgg.scp.dto.response;

import kr.mmgg.scp.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatroomDto {
    private String chatroomName;
    private Long chatroomLeaderId;
    private List<MessageDto> messageList;
}
