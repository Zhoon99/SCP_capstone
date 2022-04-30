package kr.mmgg.scp.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompMessageController {
    /*@MessageMapping("/{room}/{name}")
    public void chat(@DestinationVariable("room") String room, @DestinationVariable("name") String name, String content) throws Exception {
        SimpMessagingTemplate.convertAndSend("/topic/" + room, new Chat(name, content));
    }*/
}
