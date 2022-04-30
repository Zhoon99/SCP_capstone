package kr.mmgg.scp.repository;

import kr.mmgg.scp.entity.Chatroom;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ChatinUser;

import java.util.List;

@Repository
public interface ChatinuserRepository extends JpaRepository<ChatinUser, Long>{

    public List<ChatinUser> findByChatroomId(Long chatroomId);
}
