package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.ChatinuserDto;
import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.TeaminuserDto;
import kr.mmgg.scp.dto.response.TeamToAddDto;
import kr.mmgg.scp.service.StompService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StompController {

    private final StompService stompService;

    @Transactional
    @GetMapping(value = "/chat/{chatroomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<MessageDto>> lookupChatroomMessages(@PathVariable Long chatroomId) {
        return stompService.lookupChatroomMessages(chatroomId);
    }

    @Transactional
    @PostMapping(value = "/exitChatroom", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<?> exitChatroom(@RequestBody ChatinuserDto chatinuserDto) {
        return stompService.exitChatroom(chatinuserDto.getChatroomId(), chatinuserDto.getUserId());
    }
}
