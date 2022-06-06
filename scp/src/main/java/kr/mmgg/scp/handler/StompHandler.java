package kr.mmgg.scp.handler;


import java.util.Optional;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.security.TokenProvider;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor{
	private final TokenProvider tokenProvider;
	private final UserRepository userRepository;
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor acc = StompHeaderAccessor.wrap(message);
		if(StompCommand.CONNECT == acc.getCommand()) {
//			acc.getFirstNativeHeader("Authorization");
			String jwt = acc.getFirstNativeHeader("Authorization");
			if (StringUtils.hasText(jwt) && jwt.startsWith("Bearer")) {
                jwt = jwt.substring(7, jwt.length());
            }
			Long userId = tokenProvider.getUserIdFromToken(jwt);
			if(userId != null) {
				Optional<User> user = userRepository.findById(userId);
				if(user.isPresent()) {
					return message;
				} else {
					throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
				}
			} 
			
		}
		return message;
	}	
}
