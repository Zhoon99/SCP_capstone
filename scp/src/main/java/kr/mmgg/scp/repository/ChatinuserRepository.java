package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ChatinUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatinuserRepository extends JpaRepository<ChatinUser, Long> {

    public List<ChatinUser> findByChatroomId(Long chatroomId);

    public List<ChatinUser> findByUserIdAndChatinuserExit(Long userId, Integer flag);

    public Optional<ChatinUser> findByChatroomIdAndUserId(Long chatroomId,Long userId);
    
    public ChatinUser findByUserIdAndChatroomId(Long userId, Long chatroomId);
}
