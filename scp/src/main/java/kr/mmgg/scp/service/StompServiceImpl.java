package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.MessageDto;
import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.request.CreateChatRoomDto;
import kr.mmgg.scp.dto.request.ModifyChatRoomDto;
import kr.mmgg.scp.dto.response.lookupRoomDto;
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
		Collections.sort(messageDtos, new MessageComparator()); // 날짜 순 정렬

		ResultDto<List<MessageDto>> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, messageDtos, "messages");
		return rDto;
	}

	@Override
	public ResultDto<List<lookupRoomDto>> lookupRoom(Long userId) {
		List<ChatinUser> chatinuserList = chatinuserRepository.findByUserIdAndChatinuserExit(userId, 0);
		lookupRoomDto lDto;
		ArrayList<lookupRoomDto> lrdList = new ArrayList<lookupRoomDto>();
		for (int i = 0; i < chatinuserList.size(); i++) {
			lDto = new lookupRoomDto();
			Chatroom chatroom = chatroomRepository.getById(chatinuserList.get(i).getChatroomId());
			lDto.setChatroomId(chatinuserList.get(i).getChatroomId());
			lDto.setChatroomName(chatroom.getChatroomName());
			lDto.setChatroomCommoncode(chatroom.getChatroomCommoncode());
			lDto.setHeadCount(chatinuserRepository.findByChatroomId(chatroom.getChatroomId()).size());
			lrdList.add(lDto);
		}
		ResultDto<List<lookupRoomDto>> rDto = new ResultDto<>();
		return rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, lrdList, "room");
	}

	@Override
	public ResultDto<?> deleteRoom(Long chatroomId) {
		List<ChatinUser> clist = chatinuserRepository.findByChatroomId(chatroomId);
		chatroomRepository.deleteById(chatroomId);
		chatinuserRepository.deleteAll(clist);
		return new ResultDto<>().makeResult(CustomStatusCode.DELETE_SUCCESS);
	}

	public ResultDto<?> modifyRoom(Long chatroomId, ModifyChatRoomDto mcrDto) {
		Chatroom chatroom = chatroomRepository.findByChatroomId(chatroomId);
		chatroom.setChatroomName(mcrDto.getChatroomName());
		List<ChatinUser> chatinuserList = new ArrayList<ChatinUser>();
		ChatinUser chatinuser;
		for (int i = 0; i < mcrDto.getChatroomMember().size(); i++) {
			chatinuser = new ChatinUser();
			chatinuser.setUserId(mcrDto.getChatroomMember().get(i).getUserId());
			chatinuser.setChatroomId(chatroomId);
			chatinuser.setChatinuserExit(0);
			chatinuser.setChatinuserCommoncode("c-member");
			chatinuserList.add(chatinuser);
		}
		chatinuserRepository.saveAll(chatinuserList); // 인원을 변경시키고 commoncode 를 확인한다음에 c-group 또는 c-personal 로 변경시켜야되서
														// 먼저 chatinuser.save
		if (chatinuserRepository.findByChatroomId(chatroomId).size() > 2) {
			chatroom.setChatroomCommoncode("c-group");
		} else {
			chatroom.setChatroomCommoncode("c-personal");
		}
		chatroomRepository.save(chatroom); // 변경된데이터를 확인하고 commoncode 수정
		return new ResultDto<>().makeResult(CustomStatusCode.MODIFY_SUCCESS);
	}

	@Override
	public ResultDto<?> createRoom(CreateChatRoomDto ccrDto) {
		Chatroom chatroom = new Chatroom();
		chatroom.setChatroomName(ccrDto.getChatroomName());
		chatroom.setChatroomCommoncode("c-personal");
		Chatroom save = chatroomRepository.save(chatroom);
		List<ChatinUser> ciuList = new ArrayList<ChatinUser>();
		Long chatroomId = save.getChatroomId();
		ChatinUser chatinuser = new ChatinUser();
		chatinuser.setUserId(ccrDto.getUserId());
		chatinuser.setChatroomId(chatroomId);
		chatinuser.setChatinuserCommoncode("c-leader");
		chatinuser.setChatinuserExit(0);
		ciuList.add(chatinuser);
		for (int i = 0; i < ccrDto.getChatroomMember().size(); i++) {
			chatinuser = new ChatinUser();
			chatinuser.setUserId(ccrDto.getChatroomMember().get(i).getUserId());
			chatinuser.setChatroomId(chatroomId);
			chatinuser.setChatinuserExit(0);
			chatinuser.setChatinuserCommoncode("c-member");
			ciuList.add(chatinuser);
		}
		if (ccrDto.getChatroomMember().size() > 1) {
			save = chatroomRepository.findByChatroomId(chatroomId);
			save.setChatroomCommoncode("c-group");
		}
		chatinuserRepository.saveAll(ciuList);
		return new ResultDto<>().makeResult(CustomStatusCode.CREATE_SUCCESS);
	}

	@Override
	public ResultDto<?> exitChatroom(Long chatroomId, Long userId) {
		ChatinUser chatinuser = chatinuserRepository.findByChatroomIdAndUserId(chatroomId, userId)
				.orElseThrow(() -> new IllegalStateException(chatroomId + "채팅방이나 " + userId + "유저에 해당하는 정보가 없습니다."));
		chatinuserRepository.delete(chatinuser);

		if (chatinuserRepository.findByChatroomId(chatroomId).size() < 3) {
            Chatroom chatroom = chatroomRepository.findByChatroomId(chatroomId);
            chatroom.setChatroomCommoncode("c-personal");
            chatroomRepository.save(chatroom);
        }

		ResultDto<?> rDto = new ResultDto<>();
		rDto.makeResult(CustomStatusCode.DELETE_SUCCESS);
		return rDto;
	}
}
