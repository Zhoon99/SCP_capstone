package kr.mmgg.scp.handler;


import java.util.Objects;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import kr.mmgg.scp.security.TokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor{
	private final TokenProvider tokenProvider;
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor acc = StompHeaderAccessor.wrap(message);
		System.out.println(message.getHeaders());
		System.out.println(acc.getFirstNativeHeader("Authorization"));
		if(StompCommand.CONNECT.equals(acc.getCommand())) {
			tokenProvider.validateToken(Objects.requireNonNull(acc.getFirstNativeHeader("Authorization")).substring(7));
		}
		return message;
	}	
}
