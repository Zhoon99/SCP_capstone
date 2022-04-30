package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.TeamToAddDto;
import kr.mmgg.scp.service.StompService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StompController {

    private final StompService stompService;

//	@MessageMapping("/{room}/{name}")
//    public void chat(@DestinationVariable("room") String room, @DestinationVariable("name") String name , String content) throws Exception {
//    	simpMessagingTemplate.convertAndSend("/topic/" + room , new Chat(name, content));
//    }

    @Transactional
    @GetMapping(value = "/chat/{chatroomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<MessageDto>> lookupChatroomMessages(@PathVariable Long chatroomId) {
        return stompService.lookupChatroomMessages(chatroomId);
    }
}
