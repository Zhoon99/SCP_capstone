package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateChatRoomDto;
import kr.mmgg.scp.dto.request.ModifyChatRoomDto;
import kr.mmgg.scp.dto.response.ChatroomDto;
import kr.mmgg.scp.dto.response.StompMessageDto;
import kr.mmgg.scp.dto.response.lookupRoomDto;

import java.util.List;

public interface StompService {

    public StompMessageDto chatService(Long chatroomId, Long userId, String content);

	public ResultDto<ChatroomDto> lookupChatroomMessages(Long chatroomId);

    public ResultDto<List<lookupRoomDto>> lookupRoom(Long userId);

    public ResultDto<?> deleteRoom(Long chatroomId);

    public ResultDto<?> modifyRoom(Long chatroomId, ModifyChatRoomDto mcrDto);

    public ResultDto<?> createRoom(CreateChatRoomDto ccrDto);

    public ResultDto<?> exitChatroom(Long chatroomId, Long userId);
}
