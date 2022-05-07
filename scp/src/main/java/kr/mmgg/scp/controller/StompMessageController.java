package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.response.StompMessageDto;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompMessageController {

  private final SimpMessagingTemplate simpMessagingTemplate;
  private final UserService userService;

  @MessageMapping("/{room}/{userId}")
  public void chat(@DestinationVariable("room") String room, @DestinationVariable("userId") Long userId , String content) throws Exception {
    User user = userService.findByUserId(userId);
    simpMessagingTemplate.convertAndSend("/topic/" + room , new StompMessageDto(user.getUserId(), user.getUserNickname(), content));
  }
}
