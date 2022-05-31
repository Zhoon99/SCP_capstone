package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.response.StompMessageDto;
import kr.mmgg.scp.entity.ChatinUser;
import kr.mmgg.scp.entity.Message;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.ChatinuserRepository;
import kr.mmgg.scp.repository.MessageRepository;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.security.TokenProvider;
import kr.mmgg.scp.service.StompService;
import kr.mmgg.scp.service.UserService;
import kr.mmgg.scp.util.dateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompMessageController {

  private final SimpMessagingTemplate simpMessagingTemplate;
  private final StompService stompService;
  private final TokenProvider tokenProvider;
  private final UserRepository userRepository;
  @MessageMapping("/{chatroomId}/{userId}")
  public void chat(@DestinationVariable("chatroomId") Long chatroomId, @DestinationVariable("userId") Long userId, String content, @Header("Authentication") String jwt) throws Exception {
	Long userid = tokenProvider.getUserIdFromToken(jwt);
	User user = userRepository.getById(userid);
	System.out.println(user.getUserNickname()+"님이 입장하셨습니다.");
    simpMessagingTemplate.convertAndSend("/topic/" + chatroomId ,stompService.chatService(chatroomId, userId, content));
  }
}
