package kr.mmgg.scp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {
//	@MessageMapping("/{room}/{name}")
//    public void chat(@DestinationVariable("room") String room, @DestinationVariable("name") String name , String content) throws Exception {
//    	simpMessagingTemplate.convertAndSend("/topic/" + room , new Chat(name, content));
//    }
}
