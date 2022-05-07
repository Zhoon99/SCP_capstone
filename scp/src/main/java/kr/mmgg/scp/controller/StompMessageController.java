package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.response.StompMessageDto;
import kr.mmgg.scp.entity.ChatinUser;
import kr.mmgg.scp.entity.Message;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.ChatinuserRepository;
import kr.mmgg.scp.repository.MessageRepository;
import kr.mmgg.scp.service.StompService;
import kr.mmgg.scp.service.UserService;
import kr.mmgg.scp.util.dateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompMessageController {

  private final SimpMessagingTemplate simpMessagingTemplate;
  private final StompService stompService;
  
  @MessageMapping("/{chatroomId}/{userId}")
  public void chat(@DestinationVariable("chatroomId") Long chatroomId, @DestinationVariable("userId") Long userId , String content) throws Exception {
    simpMessagingTemplate.convertAndSend("/topic/" + chatroomId ,stompService.chatService(chatroomId, userId, content));
  }
}
