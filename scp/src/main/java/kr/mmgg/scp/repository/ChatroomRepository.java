package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.Chatroom;

import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    public Chatroom findByChatroomId(Long chatroomId);

}
