package kr.mmgg.scp.controller;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateChatRoomDto;
import kr.mmgg.scp.dto.request.ModifyChatRoomDto;
import kr.mmgg.scp.dto.response.UserToAddDto;
import kr.mmgg.scp.dto.response.lookupRoomDto;
import kr.mmgg.scp.dto.TeaminuserDto;
import kr.mmgg.scp.dto.response.TeamToAddDto;
import kr.mmgg.scp.service.StompService;
import kr.mmgg.scp.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StompController {
    private final TeamService teamService;
    private final StompService stompService;

    @Transactional
    @GetMapping(value = "/chat/{chatroomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<MessageDto>> lookupChatroomMessages(@PathVariable Long chatroomId) {
        return stompService.lookupChatroomMessages(chatroomId);
    }

    // 채팅방 조회 -- > 가진 유저번호로 조회
    @GetMapping(value = "/lookupRoom/{userId}")
    public ResultDto<List<lookupRoomDto>> lookupRoom(@PathVariable Long userId) {
        return stompService.lookupRoom(userId);
    }

    // 채팅방 삭제
    @GetMapping(value = "/deleteRoom/{chatroomId}")
    public ResultDto<?> deleteRoom(@PathVariable Long chatroomId) {
        return stompService.deleteRoom(chatroomId);
    }

    // 채팅방 수정
    @PostMapping(value = "/modifyRoom/{chatroomId}")
    public ResultDto<?> modifyRoom(@PathVariable Long chatroomId, @RequestBody ModifyChatRoomDto mcrDto) {
        return stompService.modifyRoom(chatroomId, mcrDto);
    }

    // 채팅방 생성
    @PostMapping(value = "/createRoom")
    public ResultDto<?> createRoom(@RequestBody CreateChatRoomDto ccrDto) {
        return stompService.createRoom(ccrDto);
    }

    // 채팅방 생성 및 수정 -> 이메일로 멤버검색
    // TODO: 내자신 이메일도 보임 수정해야됨 즉 userId를 받아서 email검증과정을 거치고, 비교한뒤 내꺼는 제외시키기
    @GetMapping(value = "/lookupMember/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<List<UserToAddDto>> lookupMember(@PathVariable String email) {
        return teamService.getUsersByEmail(email);
    }

    @Transactional
    @GetMapping(value = "/exitChatroom/{chatroomId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto<?> exitChatroom(@PathVariable Long chatroomId, @PathVariable Long userId) {
        return stompService.exitChatroom(chatroomId, userId);
    }
}
