package kr.mmgg.scp.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import kr.mmgg.scp.service.StompService;
import lombok.RequiredArgsConstructor;

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
