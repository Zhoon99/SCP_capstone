package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.TeamMembersDto;
import kr.mmgg.scp.entity.ChatinUser;
import kr.mmgg.scp.entity.Chatroom;
import kr.mmgg.scp.entity.Message;
import kr.mmgg.scp.entity.Teaminuser;
import kr.mmgg.scp.repository.ChatinuserRepository;
import kr.mmgg.scp.repository.ChatroomRepository;
import kr.mmgg.scp.repository.MessageRepository;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.MessageComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StompServiceImpl implements StompService {

    private final ChatinuserRepository chatinuserRepository;
    private final ChatroomRepository chatroomRepository;
    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public ResultDto<List<MessageDto>> lookupChatroomMessages(Long chatroomId) {
        List<MessageDto> messageDtos = new ArrayList<>();

        List<ChatinUser> chatinuserList = chatinuserRepository.findByChatroomId(chatroomId);
        if (chatinuserList.isEmpty()) {
            throw new IllegalStateException(chatroomId + "해당 채팅방 정보가 없습니다.");
        }

        for (ChatinUser i : chatinuserList) {
            List<Message> allMessages = messageRepository.findByChatinuserId(i.getChatinuserId());
            if (chatinuserList.isEmpty()) {
                throw new IllegalStateException(i.getChatinuserId() + "해당 chatinuser 정보가 없습니다.");
            }

            for (Message j : allMessages) {
                MessageDto messageDto = MessageDto.builder()
                        .userNickname(i.getUser().getUserNickname())
                        .messageContent(j.getMessageContent())
                        .messageTime(j.getMessageTime())
                        .build();
                messageDtos.add(messageDto);
            }
        }
        Collections.sort(messageDtos, new MessageComparator()); //날짜 순 정렬

        ResultDto<List<MessageDto>> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, messageDtos, "messages");
        return rDto;
    }

    @Override
    public ResultDto<?> exitChatroom(Long chatroomId, Long userId) {

        ChatinUser chatinuser = chatinuserRepository.findByChatroomIdAndAndUserId(chatroomId, userId)
                .orElseThrow(() -> new IllegalStateException(chatroomId + "채팅방이나 " + userId + "유저에 해당하는 정보가 없습니다."));
        chatinuserRepository.delete(chatinuser);

        if(chatinuserRepository.findByChatroomId(chatroomId).size() < 3) {
            Chatroom chatroom = new Chatroom();
            chatroom.setChatroomId(chatroomId);
            chatroom.setChatroomName(chatinuser.getChatroom().getChatroomName());
            chatroom.setChatroomCommoncode("c-personal");
            chatroomRepository.save(chatroom);
        }

        ResultDto<?> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.DELETE_SUCCESS);
        return rDto;
    }
}
