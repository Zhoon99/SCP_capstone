package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.TeamMembersDto;
import kr.mmgg.scp.entity.ChatinUser;
import kr.mmgg.scp.entity.Message;
import kr.mmgg.scp.repository.ChatinuserRepository;
import kr.mmgg.scp.repository.MessageRepository;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.MessageComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StompServiceImpl implements StompService{

    private final ChatinuserRepository chatinuserRepository;
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

            for(Message j : allMessages) {
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
}
