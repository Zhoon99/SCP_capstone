package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StompService {

    public ResultDto<List<MessageDto>> lookupChatroomMessages(Long chatroomId);

    public ResultDto<?> exitChatroom(Long chatroomId, Long userId);
}
